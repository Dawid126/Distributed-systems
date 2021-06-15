package distributed.systems.restapihomework.controller.weather;

import distributed.systems.restapihomework.exception.BeginningDateAfterEndException;
import distributed.systems.restapihomework.exception.LocationNotFoundException;
import distributed.systems.restapihomework.model.internalmodel.WeatherDataResponse;
import distributed.systems.restapihomework.model.internalmodel.WeatherForTimePeriodRequest;
import distributed.systems.restapihomework.model.metaweathermodel.LocationResponse;
import distributed.systems.restapihomework.service.metaweather.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import distributed.systems.restapihomework.service.metaweather.WeatherService;

import java.time.LocalDate;
import java.util.Calendar;

@Controller
@RequestMapping("/statistics/weather")
public class WeatherDataController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather-data-for-time-period-request-form")
    public String showWeatherForTimePeriodForm(Model model) {
        model.addAttribute("request", new WeatherForTimePeriodRequest());
        return "weather_archive_data_for_time_period_form";
    }

    @GetMapping("/weather-forecast-for-time-period-request-form")
    public String showWeatherForecastForTimePeriodForm(Model model) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        model.addAttribute("request", new WeatherForTimePeriodRequest());
        model.addAttribute("maxDate", calendar);
        return "weather_forecast_for_time_period_form";
    }

    @GetMapping("/weather-data-for-time-period")
    public String showWeatherDataForTimePeriod(@RequestParam String locationName,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beginningDate,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                              Model model) {

        if(locationName.length() == 0) {
            throw new LocationNotFoundException("empty location");
        }

        if(beginningDate.isAfter(endDate)) {
            throw new BeginningDateAfterEndException();
        }

        final LocationResponse locationResponse = locationService.getLocation(locationName);

        WeatherDataResponse response = weatherService.getWeatherDataForTimePeriod(locationResponse, beginningDate, endDate);
        injectAttributesToModel(response, model);
        return "weather_data_for_time_period";
    }

    private void injectAttributesToModel(WeatherDataResponse response, Model model) {
        model.addAttribute("timePeriod", "from " + response.getBeginningDate() + " to " + response.getEndDate());
        model.addAttribute("city", response.getCityName());

        model.addAttribute("minimumTemperature", response.getTemperature().getMinimum());
        model.addAttribute("averageTemperature", response.getTemperature().getAverage());
        model.addAttribute("maximumTemperature", response.getTemperature().getMaximum());
        model.addAttribute("temperatureUnit", response.getTemperature().getUnit());

        model.addAttribute("minimumAirPressure", response.getAirPressure().getMinimum());
        model.addAttribute("averageAirPressure", response.getAirPressure().getAverage());
        model.addAttribute("maximumAirPressure", response.getAirPressure().getMaximum());
        model.addAttribute("airPressureUnit", response.getAirPressure().getUnit());

        model.addAttribute("minimumWind", response.getWindSpeed().getMinimum());
        model.addAttribute("averageWind", response.getWindSpeed().getAverage());
        model.addAttribute("maximumWind", response.getWindSpeed().getMaximum());
        model.addAttribute("windUnit", response.getWindSpeed().getUnit());

        model.addAttribute("minimumHumidity", response.getHumidity().getMinimum());
        model.addAttribute("averageHumidity", response.getHumidity().getAverage());
        model.addAttribute("maximumHumidity", response.getHumidity().getMaximum());
        model.addAttribute("humidityUnit", response.getHumidity().getUnit());

        model.addAttribute("minimumVisibility", response.getVisibility().getMinimum());
        model.addAttribute("averageVisibility", response.getVisibility().getAverage());
        model.addAttribute("maximumVisibility", response.getVisibility().getMaximum());
        model.addAttribute("visibilityUnit", response.getVisibility().getUnit());
        System.out.println("AFTER INJECTION");
    }

}


