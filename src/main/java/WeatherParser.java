import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WeatherParser {
    private final String APPID;
    private final static String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private JSONObject weatherJsonObj = null;

    /**
     * Create an instance and try to connect to page related to city
     *
     * @param city  city
     * @param APPID token
     * @throws IOException
     */
    WeatherParser(String city, String APPID) throws IOException {
        this.APPID = APPID;

        String url = String.format("%s/?q=%s&APPID=%s", WEATHER_URL, city, this.APPID);
        loadPage(url);
    }

    /**
     * Make request to page, read an answer and make a JsonObject from it
     *
     * @param url URL of page where is a weather related to city
     * @throws IOException
     */
    private void loadPage(String url) throws IOException {
        URL weatherSite = new URL(url);
        JSONParser parser = new JSONParser();

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(weatherSite.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String rawWeatherPageData = null;
        rawWeatherPageData = in.readLine();


        try {
            weatherJsonObj = (JSONObject) parser.parse(rawWeatherPageData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        in.close();
    }

    /**
     * Get weather description from uploaded json object
     *
     * @return String
     */
    public String getWeatherDescription() {
        JSONArray arr = (JSONArray) weatherJsonObj.get("weather");
        JSONObject ob = (JSONObject) arr.get(0);
        return (String) ob.get("description");
    }

    /**
     * Get weather temperature(in kelvins) from uploaded json object
     *
     * @return Double
     */
    public Double getWeatherTemperatureInKelvins() {
        JSONObject main = (JSONObject) weatherJsonObj.get("main");
        return (Double) main.get("temp");
    }

    /**
     * Get weather temperature(in celsius) from uploaded json object
     *
     * @return Double
     */
    public Double getWeatherTemperatureInCelsius() {
        return getWeatherTemperatureInKelvins() - 273.15;
    }

    /**
     * Get full weather description
     *
     * @return String that represents a weather in a city
     */
    public String getWeather() {

        String weatherDesc = getWeatherDescription();
        Double temp_celvins = getWeatherTemperatureInKelvins();
        Double temp_celsius = getWeatherTemperatureInCelsius();

        String result = String.format("Weather: %s%nTemperature in Kelvin: %s%nTemperature in Celsius: %s%n", weatherDesc, temp_celvins, temp_celsius);

        return result;
    }
}
