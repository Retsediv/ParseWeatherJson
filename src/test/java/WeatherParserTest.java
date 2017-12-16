import junit.framework.TestCase;
import org.junit.Test;
import java.io.IOException;

public class WeatherParserTest extends TestCase {
    @Test
    public void testGetFullDescription() throws IOException {
        String city = "Dnipro";
        WeatherParser parser = new WeatherParser(city , "5496614f4ca95e6dcc0445c1e7f3916d");

        assertTrue(parser.getWeather() instanceof String);

        assertTrue(-100 < parser.getWeatherTemperatureInCelsius());
        assertTrue(parser.getWeatherTemperatureInCelsius() < 100);

    }
}