package distributed.systems.restapihomework.model.internalmodel;

import java.util.List;

public class WindSpeedContainer extends WeatherDataContainer {

    { unit = "mph"; }

    public WindSpeedContainer(List<Double> airPressureDataStream) {
        calculateAverage(airPressureDataStream);
        calculateMaximum(airPressureDataStream);
        calculateMinimum(airPressureDataStream);
    }

}
