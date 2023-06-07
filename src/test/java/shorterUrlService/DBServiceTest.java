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
    public void testFindByshortUrlIncorrectParams() {
        assertFalse(urlRepo.findByshortUrl(null).isPresent());
        assertFalse(urlRepo.findByshortUrl("").isPresent());
        assertFalse(urlRepo.findByshortUrl("  ").isPresent());
    }

    @Test
    public void testFindByshortUrl() {
        UrlEntity exceptedEntity = new UrlEntity("vk.com", "a1b2c");
        dbService.save(exceptedEntity);

        Optional<UrlEntity> actualEntity = dbService.findByshortUrl("a1b2c");

        assertTrue(actualEntity.isPresent());
        assertEquals(exceptedEntity.getLongUrl(), actualEntity.get().getLongUrl());
        assertEquals(exceptedEntity.getShortUrl(), actualEntity.get().getShortUrl());
    }
}
