package shorterUrlService.service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RedirectUrlServiceImpl implements RedirectUrlService {
    private final DBService dbService;

    public RedirectUrlServiceImpl(DBService dbService) {
        this.dbService = dbService;
    }
    @Override
    public URI genURI (String shortUrl) { //метод, создающий URI
        try {
            return new URI("http://" + dbService.findByshortUrl(shortUrl).getLongUrl()); //TODO тут проверять isPresent
        } catch (URISyntaxException e) {
            throw new RuntimeException(e); //TODO тут кидать exception
        }
    }
}
