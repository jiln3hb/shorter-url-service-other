package shorterUrlService.service;

import org.springframework.http.ResponseEntity;
import shorterUrlService.repository.MainRepo;

import java.net.URI;

public interface RedirectUrlService {
    URI genURI (String shortUrl);
}
