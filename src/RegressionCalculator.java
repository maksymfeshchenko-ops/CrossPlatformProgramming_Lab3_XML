package Lab3;

import java.util.List;

public class RegressionCalculator {
    public static double[] calculate(List<Lab3.Point> points) {
        int n = points.size();
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (Lab3.Point p : points) {
            sumX += p.getX();
            sumY += p.getY();
            sumXY += p.getX() * p.getY();
            sumX2 += Math.pow(p.getX(), 2);
        }

        double k = (sumXY - (sumX * sumY) / n) / (sumX2 - Math.pow(sumX, 2) / n);
        double b = (sumY - k * sumX) / n;

        return new double[]{k, b}; // Повертаємо масив з двома коефіцієнтами
    }
}
