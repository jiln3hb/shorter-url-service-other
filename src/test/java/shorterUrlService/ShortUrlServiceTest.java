package shorterUrlService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.exceptions.BadRequestException;
import shorterUrlService.repository.UrlRepo;
import shorterUrlService.service.DBService;
import shorterUrlService.service.ShortUrlServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
public class ShortUrlServiceTest {

    @Autowired
    private UrlRepo urlRepo;
    private DBService dbService;
    private ShortUrlServiceImpl shortUrlService;

    @BeforeEach
    public void setUp() {
        dbService = new DBService(urlRepo);
        shortUrlService = new ShortUrlServiceImpl(dbService);
    }

    @Test
    public void testGenAndCheckIncorrectParams() {
        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck(null));
        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck(""));
        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck("  "));

        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck(",,,"));
        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck("@#faD5"));
        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck("vk.,,com/,"));
        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck("htp:/vk.com"));
    }

    @Test
    public void testGenAndCheck() {
        String longUrl = "vk.com/profile";

        String shortUrl = shortUrlService.genAndCheck(longUrl);

        assertThat(shortUrl).isNotNull().isNotBlank();
        System.out.println("excepted: longUrl: " + longUrl + " shortUrl: " + shortUrl);

        Optional<UrlEntity> urlEntity = dbService.findByshortUrl(shortUrl);

        assertTrue(urlEntity.isPresent());
        assertEquals(longUrl, urlEntity.get().getLongUrl());
        assertEquals(shortUrl, urlEntity.get().getShortUrl());
        System.out.println("actual: longUrl: " + urlEntity.get().getLongUrl() + " shortUrl: " + urlEntity.get().getShortUrl());
    }
}
