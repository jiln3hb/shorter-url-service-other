package shorterUrlService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shorterUrlService.entity.Urll;
import shorterUrlService.repository.MainRepo;

import java.util.List;

@Service
public class DBService {
    private final MainRepo mainRepo;

    @Autowired
    public DBService(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
    }

    public List<Urll> listAll() {
        return mainRepo.findAll();
    } //метод возвращает информацию о всех сущностях из бд в виде списка

    public void deleteAll() {
        mainRepo.deleteAll();
    } //удаляет все данные из бд

    public void save(Urll urll) {
        mainRepo.save(urll);
    } //метод для сохранения сущности в бд

    public Urll findByshortUrl(String shortUrl) {
        return mainRepo.findByshortUrl(shortUrl);
    } //метод поиска информации о сущности по её shortUrl

    public Urll findBylongUrl(String longUrl) {
        return mainRepo.findBylongUrl(longUrl);
    } //метод поиска информации о сущности по её longUrl

}
