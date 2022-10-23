import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    @Test
    void testSendMessageLanguageIpCountry() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp(null))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));


        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        String send = messageSender.send(headers);

        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected, send);

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String send1 = messageSender.send(headers);

        String expected1 = "Welcome";

        Assertions.assertEquals(expected1, send1);

        headers.put("x", null);
        String send2 = messageSender.send(headers);
        String expected2 = "Welcome";

        Assertions.assertEquals(expected2, send2);


    }

}
