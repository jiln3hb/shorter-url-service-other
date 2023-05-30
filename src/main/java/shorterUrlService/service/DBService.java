package shorterUrlService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.repository.UrlRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DBService {
    private final UrlRepo urlRepo;

    @Autowired
    public DBService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public List<UrlEntity> listAll() { //TODO протестить
        return urlRepo.findAll();
    } //метод возвращает информацию о всех сущностях из бд в виде списка

    public void deleteAll() { //TODO протестить
        urlRepo.deleteAll();
    } //удаляет все данные из бд

    public void save(UrlEntity urlEntity) { //TODO протестить
        urlRepo.save(urlEntity);
    } //метод для сохранения сущности в бд

    public Optional<UrlEntity> findByshortUrl(String shortUrl) { //TODO протестить
        return urlRepo.findByshortUrl(shortUrl);
    } //метод поиска информации о сущности по её shortUrl

    public Optional<UrlEntity> findBylongUrl(String longUrl) {
        return urlRepo.findBylongUrl(longUrl);
    } //метод поиска информации о сущности по её longUrl

}
