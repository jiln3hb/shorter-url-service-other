package shorterUrlService.service;

import java.util.HashMap;
import java.util.Map;

public interface ShortUrlService {
    String genAndCheck(Map<String, String> url);
}
