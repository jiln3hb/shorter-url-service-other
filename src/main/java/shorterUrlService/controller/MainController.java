package shorterUrlService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shorterUrlService.entity.Urll;
import shorterUrlService.repository.MainRepo;
import shorterUrlService.service.DBService;
import shorterUrlService.service.RedirectUrlService;
import shorterUrlService.service.ShortUrlService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
public class MainController {

    private ShortUrlService shortUrlService = new ShortUrlService();
    private RedirectUrlService redirectUrlService = new RedirectUrlService();
    private DBService dbService = new DBService();

    private final MainRepo mainRepo;
    @Autowired
    public MainController(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
    }

    @GetMapping("list")
    List<Urll> list() {
        return dbService.listAll(mainRepo);
    }

    @DeleteMapping("deleteAll")
    void delete () {
        dbService.deleteVse(mainRepo);
    }

    @PostMapping("data")
    String generateURL (@RequestBody HashMap url) {
        return shortUrlService.generate(url, mainRepo);
    }

    @GetMapping("{shortUrl}")
    ResponseEntity redirect (@PathVariable String shortUrl) {

        if (shortUrl != null) def();
        return redirectUrlService.redirectTo(shortUrl, mainRepo);
    }

    ModelAndView def() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    } //TODO костыль??
}
