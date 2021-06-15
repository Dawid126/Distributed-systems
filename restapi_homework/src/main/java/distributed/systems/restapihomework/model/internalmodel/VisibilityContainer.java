package distributed.systems.restapihomework.model.internalmodel;

import java.util.List;
import java.util.Objects;

public class VisibilityContainer extends WeatherDataContainer {

    { unit = "miles"; }

    public VisibilityContainer(List<Double> airPressureDataStream) {
        calculateAverage(airPressureDataStream);
        calculateMaximum(airPressureDataStream);
        calculateMinimum(airPressureDataStream);
    }

    @Override
    public void calculateMinimum(List<Double> weatherDataForTimePeriod) {
        this.minimum = weatherDataForTimePeriod.stream()
                .filter(Objects::nonNull)
                .filter(v -> v!=0)
                .mapToDouble(v -> v)
                .min()
                .orElse(Double.MIN_VALUE);
    }

}
