package shorterUrlService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shorterUrlService.repository.UrlRepo;
import shorterUrlService.service.RedirectUrlService;
import shorterUrlService.service.RedirectUrlServiceImpl;
import shorterUrlService.service.ShortUrlService;

import java.util.HashMap;

@Controller
public class UrlController {
    private final Logger logger = LoggerFactory.getLogger(UrlController.class);
    private final ShortUrlService shortUrlService;
    private final RedirectUrlService redirectUrlService;

    @Autowired
    public UrlController(UrlRepo urlRepo, ShortUrlService shortUrlService, RedirectUrlServiceImpl redirectUrlService) {
        this.shortUrlService = shortUrlService;
        this.redirectUrlService = redirectUrlService;
    }

    @PostMapping("/generate")
    @ResponseBody
    String generateURL(@RequestBody HashMap<String, String> url) {
        String longUrl, reducedUrl;

        longUrl = url.get("url");

        reducedUrl = shortUrlService.genAndCheck(longUrl);

        logger.info("reduced URL {} was successfully generated for {}", reducedUrl, longUrl);

        return reducedUrl;
    }

    @GetMapping("/")
    String def() {
        logger.info("get mapping def");
        return "index";
    }

    @GetMapping("/{shortUrl}")
    String redirect(@PathVariable String shortUrl) {
        logger.info("get mapping redirect, shortUrl= {}", shortUrl);
        return redirectUrlService.redirect(shortUrl);
    }
}
