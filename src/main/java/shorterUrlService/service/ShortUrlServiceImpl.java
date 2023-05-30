package shorterUrlService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import shorterUrlService.entity.UrlEntity;
import shorterUrlService.exceptions.BadRequestException;

import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    private static final String URL_REGEX = "^(http:\\/\\/|https:\\/\\/)*[a-zA-Z0-9-]+\\.[a-zA-Z]{1,6}\\b(?:[a-zA-Z0-9()@:%_\\+.~#?&/=-]*)$";
    private final Logger logger = LoggerFactory.getLogger(ShortUrlServiceImpl.class);
    private final DBService dbService;
    private final Pattern pattern;

    public ShortUrlServiceImpl(DBService dbService) {
        pattern = Pattern.compile(URL_REGEX);
        this.dbService = dbService;
    }

    @Override
    public String genAndCheck(String longUrl) {
        check(longUrl);
        return gen(longUrl);
    }

    private void check(String longUrl) { //метод, проверяющий ввод на валидность
        if (longUrl == null || longUrl.isBlank()) {
            logger.warn("entered url is not valid");
            throw new BadRequestException("entered url is not valid"); //400
        }

        Matcher matcher = pattern.matcher(longUrl);
        if (!matcher.matches()) {
            logger.warn("entered url is not valid");
            throw new BadRequestException("entered url is not valid"); //400
        }
    }

    private String gen(String longUrl) { //метод, генерирующий короткую ссылку
        String reducedURL;

        if (longUrl.contains("://")) longUrl = longUrl.split("://")[1];

        do {
            //генерация рандомной последовательности из 5 символов
            Random rnd = new Random();
            reducedURL = Long.toHexString(rnd.nextLong()).substring(0, 5); //TODO протестить
        } while (dbService.findByshortUrl(reducedURL).isPresent()); //если такая короткая ссылка уже есть, генерация повторяется (маловероятно)

        //сохранение новой сущности в бд
        dbService.save(new UrlEntity(longUrl, reducedURL));

        return reducedURL;
    }
}
