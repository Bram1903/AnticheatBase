package com.deathmotion.anticheatbase.common.util;

import com.deathmotion.anticheatbase.common.util.datastructure.Pair;
import com.github.retrooper.packetevents.protocol.world.Location;
import com.github.retrooper.packetevents.util.Vector3f;
import lombok.experimental.UtilityClass;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;

/**
 * Utility math methods for anticheat calculations such as statistical analysis,
 * angle/direction calculations and small formatting helpers.
 */
@UtilityClass
public class MathUtil {

    /**
     * Commonly used expander constant (2^24) for fixed-point style expansions.
     */
    public final double EXPANDER = Math.pow(2, 24);

    /**
     * Applies a mapping function to all numbers in the collection and returns the sum.
     *
     * @param function function mapping a number to a double
     * @param dataSet  source numbers
     * @return summed mapped values
     */
    public double sum(Function<Number, Double> function, Collection<? extends Number> dataSet) {
        return dataSet.stream()
                .map(function)
                .mapToDouble(i -> i)
                .sum();
    }

    /**
     * Finds low and high outliers using the IQR (interquartile range) method.
     * The input is sorted internally; if there is not enough data, two empty lists are returned.
     *
     * @param collection numeric input values
     * @return pair of (lowOutliers, highOutliers)
     */
    public Pair<List<Double>, List<Double>> getOutliers(final Collection<? extends Number> collection) {
        final List<Double> values = new ArrayList<>(collection.size());
        for (final Number number : collection) {
            values.add(number.doubleValue());
        }

        if (values.size() < 4) {
            return new Pair<>(Collections.emptyList(), Collections.emptyList());
        }

        Collections.sort(values);

        final int mid = values.size() / 2;
        final double q1 = getMedian(values.subList(0, mid));
        final double q3 = getMedian(values.subList(values.size() % 2 == 0 ? mid : mid + 1, values.size()));

        final double iqr = q3 - q1;
        final double lowThreshold = q1 - 1.5 * iqr;
        final double highThreshold = q3 + 1.5 * iqr;

        final Pair<List<Double>, List<Double>> tuple = new Pair<>(new ArrayList<>(), new ArrayList<>());

        for (final Double value : values) {
            if (value < lowThreshold) {
                tuple.getX().add(value);
            } else if (value > highThreshold) {
                tuple.getY().add(value);
            }
        }

        return tuple;
    }

    /**
     * Computes the sample variance of the given numbers.
     * Returns 0.0 if there are fewer than two values.
     *
     * @param data input numbers
     * @return sample variance
     */
    public double getVariance(final Collection<? extends Number> data) {
        final int n = data.size();
        if (n <= 1) {
            return 0.0;
        }

        final double mean = getMean(data);
        final double squaredSum = sum(i -> {
            final double d = i.doubleValue() - mean;
            return d * d;
        }, data);

        return squaredSum / (n - 1);
    }

    /**
     * Computes the standard deviation (sqrt of the variance).
     *
     * @param data input numbers
     * @return standard deviation or 0.0 if variance is non-positive
     */
    public double getStandardDeviation(final Collection<? extends Number> data) {
        final double variance = getVariance(data);
        if (variance <= 0) {
            return 0.0;
        }
        return Math.sqrt(variance);
    }

    /**
     * Returns the median of a sorted list of doubles.
     * If the list is empty, it returns 0.0.
     *
     * @param data sorted list of doubles
     * @return median value
     */
    public double getMedian(final List<Double> data) {
        if (data.isEmpty()) {
            return 0.0;
        }

        final int size = data.size();
        if (size % 2 == 0) {
            return (data.get(size / 2) + data.get(size / 2 - 1)) / 2.0;
        } else {
            return data.get(size / 2);
        }
    }

    /**
     * Computes the arithmetic mean of the given numbers.
     * Returns 0.0 on empty input.
     *
     * @param iterable input numbers
     * @return mean value
     */
    public double getMean(Collection<? extends Number> iterable) {
        if (iterable.isEmpty()) {
            return 0.0;
        }
        return sum(Number::doubleValue, iterable) / iterable.size();
    }

    /**
     * Computes kurtosis based on the 4th central moment divided by variance squared.
     * Returns 0.0 if there is not enough data or variance is zero.
     *
     * @param data input numbers
     * @return kurtosis value
     */
    public double getKurtosis(final Collection<? extends Number> data) {
        final int n = data.size();
        if (n < 4) {
            return 0.0;
        }

        final double mean = getMean(data);
        final double variance = getVariance(data);
        if (variance == 0.0) {
            return 0.0;
        }

        final double m4 = sum(i -> {
            final double d = i.doubleValue() - mean;
            return d * d * d * d;
        }, data) / n;

        return m4 / (variance * variance);
    }

    /**
     * Computes excess kurtosis (kurtosis - 3).
     *
     * @param data input numbers
     * @return excess kurtosis value
     */
    public double getExcessKurtosis(final Collection<? extends Number> data) {
        final double k = getKurtosis(data);
        return k - 3.0;
    }

    /**
     * Computes skewness of the given data.
     * Returns 0.0 if there is not enough data or std-dev is zero.
     *
     * @param data input numbers
     * @return skewness value
     */
    public double getSkewness(final Collection<? extends Number> data) {
        final int n = data.size();
        if (n < 3) {
            return 0.0;
        }

        final double mean = getMean(data);
        final double sd = getStandardDeviation(data);
        if (sd == 0.0) {
            return 0.0;
        }

        final double m3 = sum(i -> {
            final double d = i.doubleValue() - mean;
            return d * d * d;
        }, data) / n;

        return m3 / (sd * sd * sd);
    }

    /**
     * Computes the mode (the most frequent value) of the given collection.
     * For ties, one of the most frequent values is returned.
     *
     * @param array input numbers
     * @return mode value or {@code null} if the collection is empty
     */
    public Number getMode(Collection<? extends Number> array) {
        if (array.isEmpty()) {
            return null;
        }

        Map<Number, Integer> counts = new HashMap<>();
        for (Number n : array) {
            counts.merge(n, 1, Integer::sum);
        }

        Number mode = null;
        int max = 0;
        for (Map.Entry<Number, Integer> e : counts.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                mode = e.getKey();
            }
        }
        return mode;
    }

    /**
     * Trims/rounds a double to the given number of decimal places using a locale-independent format.
     *
     * @param degree number of decimal places
     * @param d      value to trim
     * @return trimmed double
     */
    public double trim(int degree, double d) {
        StringBuilder format = new StringBuilder("#.");
        for (int i = 0; i < degree; ++i) {
            format.append("#");
        }
        DecimalFormat df = new DecimalFormat(format.toString());
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(d).replace(',', '.'));
    }

    /**
     * Computes the greatest common divisor of two numbers using the Euclidean algorithm.
     *
     * @param a first number
     * @param b second number
     * @return greatest common divisor
     */
    public long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    /**
     * Returns whether the number lies strictly between the two bounds.
     *
     * @param number value to test
     * @param d1     lower bound
     * @param d2     upper bound
     * @return true if {@code d1 < number < d2}
     */
    public boolean isBetween(final double number, final double d1, final double d2) {
        return number > d1 && number < d2;
    }

    /**
     * Checks if two values differ at most by the given amount.
     *
     * @param amount max allowed difference
     * @param one    first value
     * @param two    second value
     * @return true if values are roughly equal
     */
    public boolean areRoughlyEqual(double amount, double one, double two) {
        return Math.abs(one - two) <= amount;
    }

    /**
     * Returns the percentage of {@code one} over {@code two}.
     * If {@code two} is zero, returns 0.0.
     *
     * @param one numerator
     * @param two denominator
     * @return percentage value
     */
    public double getPercentage(double one, double two) {
        if (two == 0.0) {
            return 0.0;
        }
        return (one / two) * 100.0;
    }

    /**
     * Returns the absolute difference between two values.
     *
     * @param g first value
     * @param q second value
     * @return absolute difference
     */
    public double differenceBetween(final double g, final double q) {
        return Math.abs(g - q);
    }

    /**
     * Returns whichever of {@code a} or {@code b} is closer to {@code closeTest}.
     *
     * @param a         first candidate
     * @param b         second candidate
     * @param closeTest reference value
     * @return closest candidate
     */
    public double getClosest(double a, double b, double closeTest) {
        return Math.abs(closeTest - a) < Math.abs(closeTest - b) ? a : b;
    }

    /**
     * Computes the yaw and pitch from location {@code one} to location {@code two}.
     *
     * @param one start location
     * @param two target location
     * @return vector with yaw (x), pitch (y), roll (z) = 0
     */
    public Vector3f getRotation(Location one, Location two) {
        double dx = two.getX() - one.getX();
        double dy = two.getY() - one.getY();
        double dz = two.getZ() - one.getZ();
        double distanceXZ = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float) (Math.toDegrees(Math.atan2(dz, dx)) - 90.0F);
        float pitch = (float) -Math.toDegrees(Math.atan2(dy, distanceXZ));
        return new Vector3f(yaw, pitch, 0.0F);
    }

    /**
     * Clamps an angle to the range [-180, 180).
     *
     * @param theta angle in degrees
     * @return clamped angle
     */
    public double clamp180(double theta) {
        theta %= 360.0D;
        if (theta >= 180.0D) {
            theta -= 360.0D;
        }
        if (theta < -180.0D) {
            theta += 360.0D;
        }
        return theta;
    }

    /**
     * Returns true if the string representation of the number is in scientific notation.
     *
     * @param d value to test
     * @return true if scientific notation
     */
    public boolean isScientificNotation(double d) {
        return String.valueOf(d).contains("E");
    }

    /**
     * Computes a direction vector from yaw and pitch angles (degrees).
     *
     * @param yaw   yaw in degrees
     * @param pitch pitch in degrees
     * @return direction vector
     */
    public Vector3f getDirection(float yaw, float pitch) {
        float rotX = (float) Math.toRadians(yaw);
        float rotY = (float) Math.toRadians(pitch);
        float y = (float) -Math.sin(rotY);
        float xz = (float) Math.cos(rotY);
        float x = (float) (-xz * Math.sin(rotX));
        float z = (float) (xz * Math.cos(rotX));
        return new Vector3f(x, y, z);
    }

    /**
     * Computes the angle between two vectors in radians.
     *
     * @param a first vector
     * @param b second vector
     * @return angle in radians
     */
    public double angle(Vector3f a, Vector3f b) {
        double dot = Math.min(Math.max(a.dot(b) / (length(a) * length(b)), -1.0), 1.0);
        return Math.acos(dot);
    }

    /**
     * Returns the Euclidean length of a vector.
     *
     * @param v vector
     * @return length
     */
    private double length(Vector3f v) {
        return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }
}
