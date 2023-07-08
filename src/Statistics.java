import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {

    private BigInteger totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        totalTraffic = BigInteger.valueOf(0);
        minTime = LocalDateTime.MIN;
        maxTime = LocalDateTime.MAX;
    }

    public BigInteger getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }

    BigInteger getTrafficRate() {
        long hour = Duration.between(minTime, maxTime).getSeconds() / 3600;
        if (hour < 1) hour = 1;
        return totalTraffic.divide(BigInteger.valueOf(hour));
    }

    public void addEntry(LogEntry logEntry) {
        BigInteger bi = BigInteger.valueOf(logEntry.getResponseSize());
        totalTraffic = totalTraffic.add(bi);
        LocalDateTime time = logEntry.getTime();

        if (minTime.equals(LocalDateTime.MIN)) minTime = time;
        if (time.isBefore(minTime)) {
            maxTime = minTime;
            minTime = time;
        } else if (maxTime.equals(LocalDateTime.MAX) || time.isAfter(maxTime)) {
            maxTime = time;
        }
    }
}

