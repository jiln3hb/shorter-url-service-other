package shorterUrlService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shorterUrlService.entity.Urll;
import shorterUrlService.repository.MainRepo;

import java.util.List;

@Service
public class DBService {

    public List<Urll> listAll(MainRepo mainRepo) { //метод возвращает все данные из бд
        return mainRepo.findAll();
    }

    public void deleteVse(MainRepo mainRepo) { //метод удаления всего (убрать потом)
        mainRepo.deleteAll();
    }

}
