package shorterUrlService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.repository.UrlRepo;
import shorterUrlService.service.DBService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
public class DBServiceTest {
    @Autowired
    private UrlRepo urlRepo;

    private DBService dbService;

    @BeforeEach
    public void setUp() {
        dbService = new DBService(urlRepo);
    }

    @Test
    public void testSave() {
        UrlEntity exceptedEntity = new UrlEntity("github.com", "a1b2c");
        dbService.save(exceptedEntity);

        List<UrlEntity> list = urlRepo.findAll();

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());

        assertEquals(exceptedEntity.getLongUrl(), list.get(0).getLongUrl());
        assertEquals(exceptedEntity.getShortUrl(), list.get(0).getShortUrl());
    }

    @Test
    public void testDeleteAll() { //TODO пришлось добавить конструктор с id
        UrlEntity entity1 = new UrlEntity(1L,"github.com", "a1b2c");
        UrlEntity entity2 = new UrlEntity(2L,"vk.com", "a2b3c");
        UrlEntity entity3 = new UrlEntity(3L,"google.com", "a3b4c");
        List<UrlEntity> entitiesList = List.of(entity1, entity2, entity3);
        urlRepo.saveAll(entitiesList);

        dbService.deleteAll();

        assertTrue(urlRepo.findAll().isEmpty());
    }

    @Test
    public void testListAll() { //TODO пришлось добавить конструктор с id
        UrlEntity entity1 = new UrlEntity(1L,"github.com", "a1b2c");
        UrlEntity entity2 = new UrlEntity(2L, "vk.com", "a2b3c");
        UrlEntity entity3 = new UrlEntity(3L, "google.com", "a3b4c");

        List<UrlEntity> entitiesList = List.of(entity1, entity2, entity3);

        urlRepo.saveAll(entitiesList);

        assertEquals(dbService.listAll().get(0).getLongUrl(), entitiesList.get(0).getLongUrl());
        assertEquals(dbService.listAll().get(0).getShortUrl(), entitiesList.get(0).getShortUrl());

        assertEquals(dbService.listAll().get(1).getLongUrl(), entitiesList.get(1).getLongUrl());
        assertEquals(dbService.listAll().get(1).getShortUrl(), entitiesList.get(1).getShortUrl());

        assertEquals(dbService.listAll().get(2).getLongUrl(), entitiesList.get(2).getLongUrl());
        assertEquals(dbService.listAll().get(2).getShortUrl(), entitiesList.get(2).getShortUrl());
    }

    @Test
    public void testFindByshortUrlIncorrectParams() {
        assertFalse(urlRepo.findByshortUrl(null).isPresent());
        assertFalse(urlRepo.findByshortUrl("").isPresent());
        assertFalse(urlRepo.findByshortUrl("  ").isPresent());
    }

    @Test
    public void testFindByshortUrl() {
        UrlEntity exceptedEntity = new UrlEntity("vk.com", "a1b2c");
        dbService.save(exceptedEntity);
        System.out.println("exceptedEntity: " + exceptedEntity.getLongUrl() + ", " + exceptedEntity.getShortUrl());

        Optional<UrlEntity> actualEntity = dbService.findByshortUrl("a1b2c");

        assertTrue(actualEntity.isPresent());
        System.out.println("actualEntity: " + actualEntity.get().getLongUrl() + ", " + actualEntity.get().getShortUrl());
        assertEquals(exceptedEntity.getLongUrl(), actualEntity.get().getLongUrl());
        assertEquals(exceptedEntity.getShortUrl(), actualEntity.get().getShortUrl());
    }
}
