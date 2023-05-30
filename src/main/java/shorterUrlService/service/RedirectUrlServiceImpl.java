package shorterUrlService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.exceptions.NotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
public class RedirectUrlServiceImpl implements RedirectUrlService {
    private final DBService dbService;
    private final Logger logger = LoggerFactory.getLogger(RedirectUrlServiceImpl.class);

    public RedirectUrlServiceImpl(DBService dbService) {
        this.dbService = dbService;
    }
    @Override
    public String redirect (String shortUrl) throws NotFoundException { //метод, создающий URI
        Optional<UrlEntity> urlEntity = dbService.findByshortUrl(shortUrl);

        if (urlEntity.isPresent()) {
            String longUrl = urlEntity.get().getLongUrl();
            return "redirect://" + longUrl;
        } else {
            logger.warn("this url is not found");
            throw  new NotFoundException("this url is not found");
        }
    }
}
