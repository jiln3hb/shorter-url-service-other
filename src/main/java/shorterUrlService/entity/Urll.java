package shorterUrlService.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Urll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String longUrl;
    private String shortUrl;

    public Urll() {}

    public Urll(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
