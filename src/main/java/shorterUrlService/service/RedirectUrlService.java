package shorterUrlService.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shorterUrlService.repository.MainRepo;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RedirectUrlService {

    public ResponseEntity redirectTo (String shortUrl, MainRepo mainRepo) { //метод перенаправляющий с короткой ссылки на длинную
        try {
            URI uri = new URI("http://" + mainRepo.findByshortUrl(shortUrl).getLongUrl());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);

            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
