package distributed.systems.restapihomework.model.metaweathermodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsolidatedWeather {
    @JsonProperty
    private BigInteger id;

    @JsonProperty("applicable_date")
    private LocalDate applicableDate;

    @JsonProperty("weather_state_name")
    private String weatherStateName;

    @JsonProperty("weather_state_abbr")
    private String weatherStateAbbr;

    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("wind_direction")
    private double windDirection;

    @JsonProperty("wind_direction_compass")
    private String windDirectionCompass;

    @JsonProperty
    private LocalDateTime created;

    @JsonProperty("min_temp")
    private double minTemp;

    @JsonProperty("max_temp")
    private double maxTemp;

    @JsonProperty("the_temp")
    private double theTemp;

    @JsonProperty("air_pressure")
    private double airPressure;

    @JsonProperty("humidity")
    private double humidity;

    @JsonProperty("visibility")
    private double visibility;

    @JsonProperty("predictability")
    private int predictability;

    public ConsolidatedWeather(
            BigInteger id,
            LocalDate applicableDate,
            String weatherStateName,
            String weatherStateAbbr,
            double windSpeed,
            double windDirection,
            String windDirectionCompass,
            LocalDateTime created,
            double minTemp,
            double maxTemp,
            double theTemp,
            double airPressure,
            double humidity,
            double visibility,
            int predictability) {

        this.id = id;
        this.applicableDate = applicableDate;
        this.weatherStateName = weatherStateName;
        this.weatherStateAbbr = weatherStateAbbr;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.windDirectionCompass = windDirectionCompass;
        this.created = created;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.theTemp = theTemp;
        this.airPressure = airPressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }

    public ConsolidatedWeather() {}

    public BigInteger getId() {
        return id;
    }

    public LocalDate getApplicableDate() {
        return applicableDate;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getTheTemp() {
        return theTemp;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public int getPredictability() {
        return predictability;
    }
}
