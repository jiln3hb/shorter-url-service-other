package shorterUrlService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shorterUrlService.entity.Urll;
import shorterUrlService.repository.MainRepo;
import shorterUrlService.service.DBService;

@ExtendWith(MockitoExtension.class)
public class DBServiceTest {

    @Mock
    private MainRepo mainRepo;

    @InjectMocks
    private DBService dbService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void positiveTestFindByshortUrl() {
        Urll urll = new Urll("domain.ru", "a3b4c2");

        dbService.save(urll);

        Assertions.assertEquals(urll, dbService.findByshortUrl("a3b4c2"));
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
