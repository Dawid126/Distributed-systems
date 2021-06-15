package distributed.systems.restapihomework.model.internalmodel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class WeatherDataContainer {

    protected double minimum;
    protected double maximum;
    protected double average;
    protected String unit;

    public void calculateAverage(List<Double> weatherDataForTimePeriod) {
        this.average = weatherDataForTimePeriod.stream()
            .filter(Objects::nonNull)
            .mapToDouble(v -> v)
            .average()
            .orElse(Double.NaN);
    }

    public void calculateMinimum(List<Double> weatherDataForTimePeriod) {
        this.minimum = weatherDataForTimePeriod.stream()
            .filter(Objects::nonNull)
            .mapToDouble(v -> v)
            .min()
            .orElse(Double.MIN_VALUE);
    }

    public void calculateMaximum(List<Double> weatherDataForTimePeriod) {
        this.maximum = weatherDataForTimePeriod.stream()
            .filter(Objects::nonNull)
            .mapToDouble(v -> v)
            .max()
            .orElse(Double.MAX_VALUE);
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public double getAverage() {
        return average;
    }

    public String getUnit() {
        return unit;
    }
}
