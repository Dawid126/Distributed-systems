package distributed.systems.restapihomework.service.metaweather;

import distributed.systems.restapihomework.exception.LocationNotFoundException;
import distributed.systems.restapihomework.model.metaweathermodel.LocationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class LocationService {

    public LocationResponse getLocation(String locationName) {
        List<LocationResponse> locationResponseList = List.of(requireNonNull(
                new RestTemplate()
                        .getForEntity("https://www.metaweather.com/api/location/search/?query=" + locationName, LocationResponse[].class)
                        .getBody()));

        if(locationResponseList.size() == 1) {
            return locationResponseList.get(0);
        } else {
            throw new LocationNotFoundException(locationName);
        }
    }
}
