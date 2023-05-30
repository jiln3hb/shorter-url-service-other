package shorterUrlService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shorterUrlService.entity.UrlEntity;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity, Long> {
//    @Query("")
    UrlEntity findByshortUrl (String shortUrl); //TODO тут сделать Optional
    UrlEntity findBylongUrl (String longUrl);

//    @Query("")
//    @Modifying
//    void saveCustom(String param);
}
