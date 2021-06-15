package distributed.systems.restapihomework.model.internalmodel;

import java.util.List;

public class AirPressureContainer extends WeatherDataContainer {

    { unit = "hPa"; }

    public AirPressureContainer(List<Double> airPressureDataStream) {
        calculateAverage(airPressureDataStream);
        calculateMaximum(airPressureDataStream);
        calculateMinimum(airPressureDataStream);
    }

}
