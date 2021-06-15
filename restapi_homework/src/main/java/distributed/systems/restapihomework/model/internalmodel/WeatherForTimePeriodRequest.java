package distributed.systems.restapihomework.model.internalmodel;

import java.time.LocalDate;

public class WeatherForTimePeriodRequest {

    private String locationName;
    private LocalDate beginningDate;
    private LocalDate endDate;

    public WeatherForTimePeriodRequest(String locationName, LocalDate beginningDate, LocalDate endDate) {
        this.locationName = locationName;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    public WeatherForTimePeriodRequest() {}

    public String getLocationName() {
        return locationName;
    }

    public LocalDate getBeginningDate() {
        return beginningDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
