package shorterUrlService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shorterUrlService.entity.UrlEntity;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity, Long> {
//    @Query("")
    Optional<UrlEntity> findByshortUrl (String shortUrl);

//    @Query("")
//    @Modifying
//    void saveCustom(String param);
}
