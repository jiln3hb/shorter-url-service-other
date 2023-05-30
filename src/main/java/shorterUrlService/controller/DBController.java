package shorterUrlService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.repository.UrlRepo;
import shorterUrlService.service.DBService;

import java.util.List;

@Controller
public class DBController {
    private final Logger logger = LoggerFactory.getLogger(DBController.class);
    private final DBService dbService;

    @Autowired
    public DBController(UrlRepo urlRepo, DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/listAll")
    @ResponseBody
    List<UrlEntity> listAll() {
        logger.info("listAll request was executed");
        return dbService.listAll();
    }

    @DeleteMapping("/deleteAll")
    @ResponseBody
    void deleteAll() {
        logger.info("deleteAll request was executed");
        dbService.deleteAll();
    }
}
