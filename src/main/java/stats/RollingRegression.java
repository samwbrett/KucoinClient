package stats;

import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Use the past maxRecords to get a simple regression.
 * Removes outliers according to ...
 */
public class RollingRegression {

    public final static int DEFAULT_MAX_RECORDS = 50;

    private final int maxRecords;
    private final LinkedList<double[]> records;
    private final SimpleRegression regression;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public RollingRegression() {
        this(DEFAULT_MAX_RECORDS);
    }

    public RollingRegression(int maxRecords) {
        this.maxRecords = maxRecords;
        this.records = new LinkedList<>();
        this.regression = new SimpleRegression(true);
    }

    public boolean isFull() {
        lock.readLock().lock();
        try {
            return records.size() == maxRecords;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getRecordCount() {
        lock.readLock().lock();
        try {
            return records.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    public long regressionRecords() {
        lock.readLock().lock();
        try {
            return regression.getN();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void addDataPoint(double y) {
        lock.writeLock().lock();
        try {
            if (records.size() == maxRecords) {
                double[] first = records.removeFirst();
                regression.removeData(first[0], first[1]);
            }
            double nextX = getNextX();
            records.addLast(new double[]{nextX, y});
            regression.addData(nextX, y);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getFirstX() {
        lock.readLock().lock();
        try {
            return (int)(records.getFirst()[0]);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getLastX() {
        lock.readLock().lock();
        try {
            return (int)(records.getLast()[0]);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getNextX() {
        lock.readLock().lock();
        try {
            return !records.isEmpty() ? (int)records.getLast()[0] + 1 : 1;
        } finally {
            lock.readLock().unlock();
        }
    }

    public SimpleRegression getRegression() {
        lock.readLock().lock();
        try {
            return regression;
        } finally {
            lock.readLock().unlock();
        }
    }

    public RegressionResults getResults() {
        lock.readLock().lock();
        try {
            return regression.regress();
        } finally {
            lock.readLock().unlock();
        }
    }

}
