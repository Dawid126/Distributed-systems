package distributed.systems.restapihomework.model.internalmodel;

import java.util.List;

public class HumidityContainer extends WeatherDataContainer {

    { unit = "%"; }

    public HumidityContainer(List<Double> airPressureDataStream) {
        calculateAverage(airPressureDataStream);
        calculateMaximum(airPressureDataStream);
        calculateMinimum(airPressureDataStream);
    }

}
