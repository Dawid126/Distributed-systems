package distributed.systems.restapihomework.controller.weather;


import distributed.systems.restapihomework.exception.BeginningDateAfterEndException;
import distributed.systems.restapihomework.exception.LocationNotFoundException;
import distributed.systems.restapihomework.model.internalmodel.WeatherDataResponse;
import distributed.systems.restapihomework.model.metaweathermodel.LocationResponse;
import distributed.systems.restapihomework.service.metaweather.LocationService;
import distributed.systems.restapihomework.service.metaweather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistics/weather")
public class ApiWeatherDataController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather-data-for-time-period")
    public ResponseEntity<WeatherDataResponse> showWeatherDataForTimePeriod(@RequestParam String locationName,
                                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beginningDate,
                                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        if (locationName.length() == 0) {
            throw new LocationNotFoundException("empty location");
        }

        if (beginningDate.isAfter(endDate)) {
            throw new BeginningDateAfterEndException();
        }

        final LocationResponse locationResponse = locationService.getLocation(locationName);

        return ResponseEntity.ok(weatherService.getWeatherDataForTimePeriod(locationResponse, beginningDate, endDate));

    }
}
