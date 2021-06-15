package distributed.systems.restapihomework.service.metaweather;

import distributed.systems.restapihomework.model.internalmodel.*;
import distributed.systems.restapihomework.model.metaweathermodel.ConsolidatedWeather;
import distributed.systems.restapihomework.model.metaweathermodel.LocationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

@Service
public class WeatherService {

    private String getWeatherUriForDateAndLocation(LocationResponse location, final LocalDate date) {
        return UriComponentsBuilder
                .fromHttpUrl("https://www.metaweather.com/api/location" + "/{id}/{year}/{month}/{day}")
                .buildAndExpand(
                        location.getWhereOnEarthId(),
                        date.getYear(),
                        date.getMonthValue(),
                        date.getDayOfMonth())
                .toUriString();
    }

    private List<ConsolidatedWeather> getConsolidatedWeatherListForSingleDay(LocationResponse location, final LocalDate date) {
        return Arrays.stream(requireNonNull(new RestTemplate()
                .getForEntity(
                        getWeatherUriForDateAndLocation(location, date),
                        ConsolidatedWeather[].class)
                .getBody()))
                .collect(Collectors.toList());
    }

    private List<List<ConsolidatedWeather>> geConsolidatedWeatherListForTimePeriod(
            LocationResponse location,
            final LocalDate beginningDate,
            final LocalDate endDate) {
        List<List<ConsolidatedWeather>> consolidatedWeatherForTimePeriod = new ArrayList<>();

        for (LocalDate date = beginningDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> consolidatedWeatherList = getConsolidatedWeatherListForSingleDay(location, date);
            consolidatedWeatherForTimePeriod.add(consolidatedWeatherList);
        }

        return consolidatedWeatherForTimePeriod;
    }

    public WeatherDataResponse getWeatherDataForTimePeriod(
            LocationResponse location,
            final LocalDate beginningDate,
            final LocalDate endDate) {

        List<List<ConsolidatedWeather>> consolidatedWeatherListForTimePeriod = geConsolidatedWeatherListForTimePeriod(location, beginningDate, endDate);

        Supplier<Stream<ConsolidatedWeather>> consolidatedWeatherStreamSupplier = () ->
                consolidatedWeatherListForTimePeriod.stream()
                .flatMap(Collection::stream);

        return new WeatherDataResponse(
                location.getTitle(),
                beginningDate,
                endDate,
                new TemperatureContainer(
                        consolidatedWeatherStreamSupplier.get().map(ConsolidatedWeather::getTheTemp).collect(Collectors.toList())),
                new AirPressureContainer(
                        consolidatedWeatherStreamSupplier.get().map(ConsolidatedWeather::getAirPressure).collect(Collectors.toList())),
                new WindSpeedContainer(
                        consolidatedWeatherStreamSupplier.get().map(ConsolidatedWeather::getWindSpeed).collect(Collectors.toList())),
                new HumidityContainer(
                        consolidatedWeatherStreamSupplier.get().map(ConsolidatedWeather::getHumidity).collect(Collectors.toList())),
                new VisibilityContainer(
                        consolidatedWeatherStreamSupplier.get().map(ConsolidatedWeather::getVisibility).collect(Collectors.toList()))
        );
    }
}
