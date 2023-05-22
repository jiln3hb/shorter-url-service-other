package shorterUrlService.service;

import java.net.URI;

public interface RedirectUrlService {
    URI genURI (String shortUrl);
}
