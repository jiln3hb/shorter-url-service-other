package shorterUrlService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shorterUrlService.repository.MainRepo;
import shorterUrlService.service.DBService;
import shorterUrlService.service.RedirectUrlServiceImpl;
import shorterUrlService.service.ShortUrlService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@Controller
public class MainController {
    private final ShortUrlService shortUrlService;
    private final RedirectUrlServiceImpl redirectUrlServiceImpl;

    @Autowired
    public MainController(MainRepo mainRepo, ShortUrlService shortUrlService, RedirectUrlServiceImpl redirectUrlServiceImpl) {
        this.shortUrlService = shortUrlService;
        this.redirectUrlServiceImpl = redirectUrlServiceImpl;
    }

    //TODO маппинги переделать
    @PostMapping("data")
    String generateURL (@RequestBody HashMap<String, String> url) {
        return shortUrlService.genAndCheck(url);
    }

    @GetMapping("/{shortUrl}")
    ResponseEntity<?> redirect (@PathVariable String shortUrl) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUrlServiceImpl.genURI(shortUrl));

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/")
    ModelAndView def() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
