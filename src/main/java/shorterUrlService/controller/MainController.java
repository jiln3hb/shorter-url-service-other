package shorterUrlService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shorterUrlService.entity.Urll;
import shorterUrlService.repository.MainRepo;
import shorterUrlService.service.DBService;
import shorterUrlService.service.RedirectUrlService;
import shorterUrlService.service.ShortUrlService;

import java.util.HashMap;
import java.util.List;

@RestController
public class MainController {

    private final ShortUrlService shortUrlService; //TODO переписать
    private final RedirectUrlService redirectUrlService;
    private final DBService dbService;

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

    @GetMapping("/{shortUrl}")
    ResponseEntity<?> redirect (@PathVariable String shortUrl) {

        if (shortUrl != null) def();
        return redirectUrlService.redirectTo(shortUrl, mainRepo);
    }

    @GetMapping("/")
    ModelAndView def() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    } //TODO костыль??
}
