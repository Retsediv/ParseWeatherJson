import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String city = "Dnipro";
        WeatherParser parser = new WeatherParser(city , "5496614f4ca95e6dcc0445c1e7f3916d");

        System.out.println(city);

        System.out.println();

        System.out.println(parser.getWeatherDescription());
        System.out.println(parser.getWeatherTemperatureInKelvins());
        System.out.println(parser.getWeatherTemperatureInCelsius());

        System.out.println();

        System.out.println(parser.getWeather());
    }
}
