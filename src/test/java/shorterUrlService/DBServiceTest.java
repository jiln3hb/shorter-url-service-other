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
    public void testListAll() {
        UrlEntity entity1 = new UrlEntity("github.com", "a1b2c3");
        UrlEntity entity2 = new UrlEntity("vk.com", "a2b3c4");
        UrlEntity entity3 = new UrlEntity("google.com", "a3b4c5");

        List<UrlEntity> entitiesList = List.of(entity1, entity2, entity3);

        urlRepo.saveAll(entitiesList);

        assertEquals(dbService.listAll(), entitiesList);
    }

    @Test
    public void testDeleteAll() {
        UrlEntity entity1 = new UrlEntity("github.com", "a1b2c3");
        UrlEntity entity2 = new UrlEntity("vk.com", "a2b3c4");
        UrlEntity entity3 = new UrlEntity("google.com", "a3b4c5");
        List<UrlEntity> entitiesList = List.of(entity1, entity2, entity3);
        urlRepo.saveAll(entitiesList);

        dbService.deleteAll();

        assertTrue(urlRepo.findAll().isEmpty());
    }

    @Test
    public void testSave() {
        UrlEntity exceptedEntity = new UrlEntity("github.com", "a1b2c3");
        dbService.save(exceptedEntity);

        List<UrlEntity> list = urlRepo.findAll();

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(exceptedEntity, list.get(0));
    }

    @Test
    public void testFindByshortUrlIncorrectParams() {
        assertFalse(urlRepo.findByshortUrl(null).isPresent());
        assertFalse(urlRepo.findByshortUrl("").isPresent());
        assertFalse(urlRepo.findByshortUrl("  ").isPresent());
    }

    @Test
    public void testFindByshortUrl() {
        UrlEntity exceptedEntity = new UrlEntity("vk.com", "a1b2c3");
        urlRepo.save(exceptedEntity);
        System.out.println("exceptedEntity: " + exceptedEntity.getLongUrl() + ", " + exceptedEntity.getShortUrl());

        Optional<UrlEntity> actualEntity = dbService.findByshortUrl("a1b2c3");

        assertTrue(actualEntity.isPresent());
        System.out.println("actualEntity: " + actualEntity.get().getLongUrl() + ", " + actualEntity.get().getShortUrl());
        assertEquals(exceptedEntity, actualEntity.get());
    }
}
