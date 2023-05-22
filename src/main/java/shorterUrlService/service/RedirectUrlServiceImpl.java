package shorterUrlService.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shorterUrlService.repository.MainRepo;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RedirectUrlServiceImpl implements RedirectUrlService {
    private final DBService dbService;

    public RedirectUrlServiceImpl(DBService dbService) {
        this.dbService = dbService;
    }
    @Override
    public URI genURI (String shortUrl) { //метод перенаправляющий с короткой ссылки на длинную
        //сделал так, чтобы метод возвращал чисто uri а весь остальной функционал в слое контроллера
        try {
            return new URI("http://" + dbService.findByshortUrl(shortUrl).getLongUrl()); //TODO написать тест (JUnit 5, Mockito, assertJ)
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
