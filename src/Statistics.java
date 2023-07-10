import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {

    private BigInteger totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> pageSet = new HashSet<>();
    HashMap<String, Integer> mapOsCnt = new HashMap<>();

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

    public HashSet<String> getPageSet() {
        return pageSet;
    }

    public HashMap<String, Integer> getMapOsCnt() {
        return mapOsCnt;
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
        if (logEntry.getResponseCode() == 200 && logEntry.getReferer().length() > 1) {
            pageSet.add(logEntry.getReferer());
        }
        String os = logEntry.getUserAgent().getTypeOS();
        if (os.length() > 1) {
            int cnt = 1;
            if (!mapOsCnt.isEmpty()) {
                for (Map.Entry<String, Integer> entry : mapOsCnt.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (os.equalsIgnoreCase(key)) {
                        cnt += value;
                    }
                }
            }
            mapOsCnt.put(os, cnt);
        }
    }

    public HashMap<String, Double> getPartOsFromAll() {
        HashMap<String, Double> result = new HashMap<>();
        int allOscnt = 0;
        for (Integer i : mapOsCnt.values()) {
            allOscnt += i;
        }
        System.out.println("Общее кол-во ОС:" + allOscnt);
        if (!mapOsCnt.isEmpty()) {
            for (Map.Entry<String, Integer> entry : mapOsCnt.entrySet()) {
                String key = entry.getKey();
                double value = entry.getValue();
                result.put(key, value / allOscnt);
            }
        }
        return result;
    }
}


