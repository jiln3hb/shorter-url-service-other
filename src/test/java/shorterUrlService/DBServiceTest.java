package shorterUrlService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.repository.UrlRepo;
import shorterUrlService.service.DBService;

@DataJpaTest
public class DBServiceTest {

    @Mock
    private UrlRepo urlRepo;

    @InjectMocks
    private DBService dbService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void positiveTestFindByshortUrl() {
        UrlEntity urlEntity = new UrlEntity("domain.ru", "a3b4c2");

        dbService.save(urlEntity);

        Assertions.assertEquals(urlEntity, dbService.findByshortUrl("a3b4c2"));
    }

    @Test
    public void negativeTestFindByshortUrl() {

    }

    @Test
    public void negativeTestFindBylongUrl() {

    }

    @Test
    public void positiveTestFindBylongUrl() {
    }
}
