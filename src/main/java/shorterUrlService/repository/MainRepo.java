package shorterUrlService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shorterUrlService.entity.Urll;

@Repository
public interface MainRepo extends JpaRepository<Urll, Long> {
    Urll findByshortUrl (String shortUrl);
    Urll findBylongUrl (String longUrl);
}
