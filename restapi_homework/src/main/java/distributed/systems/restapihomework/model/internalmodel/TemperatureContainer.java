package distributed.systems.restapihomework.model.internalmodel;

import java.util.List;

public class TemperatureContainer extends WeatherDataContainer {

    { unit = "°C"; }

    public TemperatureContainer(List<Double> airPressureDataStream) {
        calculateAverage(airPressureDataStream);
        calculateMaximum(airPressureDataStream);
        calculateMinimum(airPressureDataStream);
    }

}
