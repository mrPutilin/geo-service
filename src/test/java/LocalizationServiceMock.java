import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;

public class LocalizationServiceMock implements LocalizationService {
    public String value;

    @Override
    public String locale(Country country) {
        return null ;
    }


}
