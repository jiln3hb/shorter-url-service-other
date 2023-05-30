package shorterUrlService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.exceptions.BadRequestException;
import shorterUrlService.service.DBService;
import shorterUrlService.service.ShortUrlService;
import shorterUrlService.service.ShortUrlServiceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShortUrlServiceTest {

    @Mock
    private DBService dbService;

    @InjectMocks
    private ShortUrlServiceImpl shortUrlService;

    @Test
    public void testGenAndCheckIncorrectParams() {
        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck(Collections.emptyMap()));
        assertThrows(BadRequestException.class, () -> shortUrlService.genAndCheck(Map.of("url", "")));
    }

    @Test
    public void testGenAndCheck() {
        Map<String, String> paramUrl = Map.of("url", "vk.com/profile");

        String url = shortUrlService.genAndCheck(paramUrl);
        assertThat(url).isNotNull().isNotBlank();
        verify(dbService, times(1)).findByshortUrl(anyString());
        verify(dbService, times(1)).save(any(UrlEntity.class));
    }
}
