import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {
    GeoServiceImpl sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new GeoServiceImpl();
    }

    @AfterEach
    public void end() {
        System.out.println("Test completed");


    }

    @ParameterizedTest
    @MethodSource("source")
    public void testReturnRightLocation(String ip, String myIp, Location loc) {

        Location result = sut.byIp(myIp);

        //способ номер 1
        Assertions.assertNotNull(result);


    }

    private static Stream<Arguments> source() {

        return Stream.of(
                Arguments.of("127.0.0.1", "127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", "172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", "96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", "172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", "96.", new Location("New York", Country.USA, null, 0))

        );
    }

    @Test
    public void shouldThrowRuntimeException() {
        Assertions.assertThrows(Exception.class, () -> sut.byCoordinates(1, 1));

    }


}
