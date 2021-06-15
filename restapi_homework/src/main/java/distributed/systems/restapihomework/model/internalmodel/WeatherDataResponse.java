package distributed.systems.restapihomework.model.internalmodel;

import java.time.LocalDate;

public class WeatherDataResponse {

    private final String cityName;
    private final LocalDate beginningDate;
    private final LocalDate endDate;
    private final TemperatureContainer temperatureContainer;
    private final AirPressureContainer airPressureContainer;
    private final WindSpeedContainer windSpeedContainer;
    private final HumidityContainer humidityContainer;
    private final VisibilityContainer visibilityContainer;

    public WeatherDataResponse(
            String cityName,
            LocalDate beginningDate,
            LocalDate endDate,
            TemperatureContainer temperatureContainer,
            AirPressureContainer airPressureContainer,
            WindSpeedContainer windSpeedContainer,
            HumidityContainer humidityContainer,
            VisibilityContainer visibilityContainer) {

        this.cityName = cityName;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
        this.temperatureContainer = temperatureContainer;
        this.airPressureContainer = airPressureContainer;
        this.windSpeedContainer = windSpeedContainer;
        this.humidityContainer = humidityContainer;
        this.visibilityContainer = visibilityContainer;
    }

    public String getCityName() { return cityName; }

    public TemperatureContainer getTemperature() {
        return temperatureContainer;
    }

    public AirPressureContainer getAirPressure() {
        return airPressureContainer;
    }

    public WindSpeedContainer getWindSpeed() {
        return windSpeedContainer;
    }

    public HumidityContainer getHumidity() {
        return humidityContainer;
    }

    public VisibilityContainer getVisibility() {
        return visibilityContainer;
    }

    public String getBeginningDate() { return beginningDate.toString(); }

    public String getEndDate() { return endDate.toString(); }





}
