import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class LogEntry {
    private final String ipAddr;
    private final LocalDateTime time;
    private final HttpMethod httpMethod;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String inputString) {
        String[] lines = inputString.split(" \"");
        String line1 = lines[1];
        int idx1 = line1.indexOf('"');
        String[] codeAndSize = line1.substring(idx1 + 2).split(" ");
        String time = inputString.substring(inputString.indexOf('[') + 1, inputString.indexOf(']'));
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MMM/yyyy:HH:mm:ss ")
                .parseLenient()
                .appendOffset("+HHMM", "Z")
                .toFormatter(Locale.ENGLISH);

        this.ipAddr = inputString.substring(0, inputString.indexOf(" "));
        this.time = LocalDateTime.parse(time, formatter);
        this.httpMethod = HttpMethod.valueOf(line1.substring(0, line1.indexOf(' ')));
        this.path = line1.substring(line1.indexOf("/"), idx1);
        this.responseCode = Integer.parseInt(codeAndSize[0]);
        this.responseSize = Integer.parseInt(codeAndSize[1]);
        this.referer = lines[2].substring(0, lines[2].indexOf('"'));
        this.userAgent = new UserAgent(inputString);
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "LogEntry{" + '\n' +
                "ipAddr=" + ipAddr + '\n' +
                ", time=" + time + '\n' +
                ", httpMethod=" + httpMethod + '\n' +
                ", path=" + path + '\n' +
                ", responseCode=" + responseCode + '\n' +
                ", responseSize=" + responseSize + '\n' +
                ", referer=" + referer + '\n' +
                ", userAgent=" + userAgent +
                '}';
    }
}