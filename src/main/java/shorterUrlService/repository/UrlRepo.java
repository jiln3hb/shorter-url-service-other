package shorterUrlService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shorterUrlService.entity.UrlEntity;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity, Long> {

    @Modifying
    @Query(value = "INSERT INTO url (long_url, short_url) VALUES (:longUrl, :shortUrl)", nativeQuery = true)
    void saveCustom(@Param("longUrl") String longUrl, @Param("shortUrl") String shortUrl);

    @Query(value = "SELECT id, long_url, short_url FROM url WHERE url.short_Url = :shortUrl", nativeQuery = true)
    Optional<UrlEntity> findByshortUrl (@Param("shortUrl") String shortUrl);
}
