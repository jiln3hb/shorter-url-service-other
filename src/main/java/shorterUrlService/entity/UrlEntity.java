package shorterUrlService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "url")
public class UrlEntity {
    @Id
    private Long id;
    private String longUrl;
    private String shortUrl;

    public UrlEntity() {}

    public UrlEntity(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public UrlEntity(Long id, String longUrl, String shortUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public Long getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
