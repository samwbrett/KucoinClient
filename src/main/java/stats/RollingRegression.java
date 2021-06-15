package stats;

import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.LinkedList;

/**
 * Use the past maxRecords to get a simple regression.
 * Removes outliers according to ...
 */
public class RollingRegression {

    public final static int DEFAULT_MAX_RECORDS = 50;

    private final int maxRecords;
    private final LinkedList<double[]> records;
    private final SimpleRegression regression;

    public RollingRegression() {
        this(DEFAULT_MAX_RECORDS);
    }

    public RollingRegression(int maxRecords) {
        this.maxRecords = maxRecords;
        this.records = new LinkedList<>();
        this.regression = new SimpleRegression(true);
    }

    public boolean isFull() {
        return records.size() == maxRecords;
    }

    public int getRecordCount() {
        return records.size();
    }

    public long regressionRecords() {
        return regression.getN();
    }

    public void addDataPoint(double y) {
        if (records.size() == maxRecords) {
            double[] first = records.removeFirst();
            regression.removeData(first[0], first[1]);
        }
        double nextX = getNextX();
        records.addLast(new double[]{nextX, y});
        regression.addData(nextX, y);
    }

    public int getFirstX() {
        return (int)(records.getFirst()[0]);
    }

    public int getLastX() {
        return (int)(records.getLast()[0]);
    }

    public int getNextX() {
        return !records.isEmpty() ? (int)records.getLast()[0] + 1 : 1;
    }

    public SimpleRegression getRegression() {
        return regression;
    }

    public RegressionResults getResults() {
        return regression.regress();
    }

}
