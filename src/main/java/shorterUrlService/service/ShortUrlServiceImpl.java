package shorterUrlService.service;

import org.springframework.stereotype.Service;
import shorterUrlService.entity.Urll;
import shorterUrlService.exceptions.BadRequestException;

import java.util.HashMap;
import java.util.Random;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    private static final String URL_REGEX = "^[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$";
    private final DBService dbService;
    public ShortUrlServiceImpl(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public String genAndCheck (HashMap<String, String> url) {

        String longUrl = url.get("url");

        check(longUrl);

        return gen(longUrl);
    }

    private void check (String longUrl) {
        if (longUrl.isBlank() || !longUrl.matches(URL_REGEX)) { //проверка ввода на валидность
            throw new BadRequestException(); //400
        }
    }

    private String gen (String longUrl) {
        String reducedURL;

        do {
            //генерация рандомной последовательности из 5 символов
            Random rnd = new Random();
            reducedURL = Long.toHexString(rnd.nextLong()).substring(0,5); //TODO протестить
        } while(dbService.findByshortUrl(reducedURL) != null); //если такая короткая ссылка уже есть, генерация повторяется (маловероятно)

        //сохранение новой сущности в бд
        dbService.save(new Urll(longUrl, reducedURL));

        return reducedURL;
    }
}
