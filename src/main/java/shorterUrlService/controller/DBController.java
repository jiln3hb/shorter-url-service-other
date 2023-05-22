package shorterUrlService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import shorterUrlService.entity.Urll;
import shorterUrlService.repository.MainRepo;
import shorterUrlService.service.DBService;

import java.util.List;

@Controller
public class DBController {
    private final DBService dbService;

    @Autowired
    public DBController(MainRepo mainRepo, DBService dbService) {
        this.dbService = dbService;
    }

    //TODO маппинги переделать
    @GetMapping("list")
    List<Urll> list() {
        return dbService.listAll();
    }

    @DeleteMapping("deleteAll")
    void delete () {
        dbService.deleteAll();
    }
}
