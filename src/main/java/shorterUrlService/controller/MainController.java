package shorterUrlService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shorterUrlService.repository.MainRepo;
import shorterUrlService.service.RedirectUrlServiceImpl;
import shorterUrlService.service.ShortUrlService;

import java.net.URI;
import java.util.HashMap;

@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ShortUrlService shortUrlService;
    private final RedirectUrlServiceImpl redirectUrlServiceImpl;

    @Autowired
    public MainController(MainRepo mainRepo, ShortUrlService shortUrlService, RedirectUrlServiceImpl redirectUrlServiceImpl) {
        this.shortUrlService = shortUrlService;
        this.redirectUrlServiceImpl = redirectUrlServiceImpl;
    }

    @PostMapping("/generate")
    @ResponseBody
    String generateURL(@RequestBody HashMap<String, String> url) {
        String longUrl, reducedUrl;

        longUrl = url.get("url");
        reducedUrl = shortUrlService.genAndCheck(url);

        logger.info("reduced URL {} was successfully generated for {}", reducedUrl, longUrl);

        return reducedUrl;
    }

    @GetMapping("/") //TODO все ещё вызываются оба метода надо фиксить
    String def() {
        return "index";
    }


    @GetMapping("/{shortUrl}")
    ResponseEntity<?> redirect(@PathVariable String shortUrl) {
        URI longUrl = redirectUrlServiceImpl.genURI(shortUrl);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(longUrl);

        logger.info("successfully redirected from {} to {}", shortUrl, longUrl);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
