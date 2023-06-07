package shorterUrlService.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shorterUrlService.entity.UrlEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity, Long> {

    @Query(value = "SELECT nextval('url_seq')", nativeQuery = true) //TODO  сделать serial
    Long getNextUrlEntityId();

    @Modifying
    @Transactional //TODO переместить в слой сервиса
    @Query(value = "INSERT INTO url (id, long_Url, short_Url) VALUES (:id, :longUrl, :shortUrl)", nativeQuery = true)
    void saveCustom(@Param("id") long id, @Param("longUrl") String longUrl, @Param("shortUrl") String shortUrl);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM url", nativeQuery = true)
    void deleteAllCustom();

    @Query(value = "SELECT * FROM url", nativeQuery = true) //TODO звёздочки убрать
    List<UrlEntity> findAllCustom();

    @Query(value = "SELECT * FROM url WHERE url.short_Url = :shortUrl", nativeQuery = true)
    Optional<UrlEntity> findByshortUrl (@Param("shortUrl") String shortUrl);
}
