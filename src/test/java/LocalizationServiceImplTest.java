import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    LocalizationServiceImpl sut;

    @Test
    void shouldReturnRussiaOrEnglishMessageByIp() {
        sut = new LocalizationServiceImpl();
        String rusMessage = "Добро пожаловать";
        String engMessage = "Welcome";

        String result = sut.locale(Country.RUSSIA);

        Assertions.assertEquals(rusMessage, result);

        String result1 = sut.locale(Country.USA);

        Assertions.assertEquals(engMessage, result1);
    }


}
