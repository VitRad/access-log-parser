public class Line {
    private String ip;
    private String dateTime;
    private String requestMethod;
    private String responseCode;
    private String responseSize;
    private String referLink;
    private String userAgent;

    public Line(String ip,
                String dateTime,
                String requestMethod,
                String responseCode,
                String responseSize,
                String referLink,
                String userAgent) {
        this.ip = ip;
        this.dateTime = dateTime;
        this.requestMethod = requestMethod;
        this.responseCode = responseCode;
        this.responseSize = responseSize;
        this.referLink = referLink;
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseSize() {
        return responseSize;
    }

    public String getReferLink() {
        return referLink;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "Line{" +
                "ip='" + ip + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseSize='" + responseSize + '\'' +
                ", referLink='" + referLink + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
