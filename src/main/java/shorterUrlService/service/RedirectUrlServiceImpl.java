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
    @Override //TODO не совсем понятно зачем остальной функционал метода нужно было переносить в слой контроллера
    public URI genURI (String shortUrl) { //метод, создающий URI
        try {
            return new URI("http://" + dbService.findByshortUrl(shortUrl).getLongUrl());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
