package shorterUrlService.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.repository.UrlRepo;

import java.util.Optional;

@Service
public class DBService {
    private final UrlRepo urlRepo;

    @Autowired
    public DBService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    @Transactional
    public void save(UrlEntity urlEntity) {
        urlRepo.saveCustom(urlEntity.getLongUrl(), urlEntity.getShortUrl());
    } //метод для сохранения сущности в бд

    public Optional<UrlEntity> findByshortUrl(String shortUrl) {
        return urlRepo.findByshortUrl(shortUrl);
    } //метод поиска информации о сущности по её shortUrl
}
