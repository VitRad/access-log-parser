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
    private HashMap<String, Integer> mapOsCnt = new HashMap<>();
    private HashSet<String> page404 = new HashSet<>();
    private HashMap<String, Integer> browserCnt = new HashMap<>();

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

    public HashSet<String> getPage404() {
        return page404;
    }

    public HashMap<String, Integer> getBrowserCnt() {
        return browserCnt;
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
        writeHashMapFromStr(mapOsCnt, os);

        if (logEntry.getResponseCode() == 404 && logEntry.getReferer().length() > 1) {
            page404.add(logEntry.getReferer());
        }
        String browser = logEntry.getUserAgent().getBrowser();
        writeHashMapFromStr(browserCnt, browser);
    }

    public HashMap<String, Double> getPartOsFromAll() {
        return getPartFromAll(mapOsCnt);
    }

    public HashMap<String, Double> getPartBrowserFromAll() {
        return getPartFromAll(browserCnt);
    }

    public HashMap<String, Double> getPartFromAll(HashMap<String, Integer> map) {
        HashMap<String, Double> result = new HashMap<>();
        int allOscnt = 0;
        for (Integer i : map.values()) {
            allOscnt += i;
        }
        if (!map.isEmpty()) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                double value = entry.getValue();
                result.put(key, value / allOscnt);
            }
        }
        return result;
    }
    private void writeHashMapFromStr(HashMap<String, Integer> map, String str){
        if (str.length() > 1) {
            int cnt = 1;
            if (!map.isEmpty()) {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (str.equalsIgnoreCase(key)) {
                        cnt += value;
                    }
                }
            }
            map.put(str, cnt);
        }
    }
}


