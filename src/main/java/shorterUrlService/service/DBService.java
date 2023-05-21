package shorterUrlService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shorterUrlService.entity.Urll;
import shorterUrlService.repository.MainRepo;

import java.util.List;

@Service
public class DBService {
    private final MainRepo mainRepo; //TODO доступ к репозиторию должен иметь только этот сервис

    public DBService(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
    }

    public List<Urll> listAll() { //метод возвращает все данные из бд
        return mainRepo.findAll();
    }

    public void deleteVse() { //метод удаления всего (убрать потом)
        mainRepo.deleteAll();
    }

}
