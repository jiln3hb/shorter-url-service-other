package shorterUrlService.service;

import org.springframework.stereotype.Service;
import shorterUrlService.entity.Urll;
import shorterUrlService.exceptions.BadRequestException;

import java.util.HashMap;
import java.util.Random;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    private final DBService dbService;


    private static final String URL_REGEX = "^[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$";


    @Override
    public String generate (HashMap url) { //метод генерирующий короткую ссылку

        String longUrl = url.get("url").toString();
        String res;

        if (longUrl.isBlank() || !longUrl.matches(URL_REGEX)) { //проверка ввода на валидность
            throw new BadRequestException(); //400
        }

        do {
            //генерация рандомной последовательности из 5 символов
            Random rnd = new Random();
            res = Long.toHexString(rnd.nextLong()).substring(0,5); //TODO протестить
        } while(mainRepo.findByshortUrl(res) != null); //если такая короткая ссылка уже есть, генерация повторяется (маловероятно)

        //сохранение новой сущности в бд
        mainRepo.save(new Urll(url.get("url").toString(), res));

        return res; //TODO разделить метод на проверку и генерацию, и вызывать их отсюда (private)
    }
}
