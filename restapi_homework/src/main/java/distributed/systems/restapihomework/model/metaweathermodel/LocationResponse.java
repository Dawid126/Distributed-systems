package distributed.systems.restapihomework.model.metaweathermodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationResponse {

    public LocationResponse(String title, String locationType, String latitudeLongitude, int whereOnEarthId) {
        this.title = title;
        this.locationType = locationType;
        this.latitudeLongitude = latitudeLongitude;
        this.whereOnEarthId = whereOnEarthId;
    }

    public LocationResponse() {}

    @JsonProperty
    private String title;

    @JsonProperty("location_type")
    private String locationType;

    @JsonProperty("latt_long")
    private String latitudeLongitude;

    @JsonProperty("woeid")
    private int whereOnEarthId;


    public String getTitle() {
        return title;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getLatitudeLongitude() {
        return latitudeLongitude;
    }

    public int getWhereOnEarthId() {
        return whereOnEarthId;
    }
}
