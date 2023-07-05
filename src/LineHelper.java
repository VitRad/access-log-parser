public class LineHelper {
    public LineHelper() {
    }

    public Line getLineFromString(String s) {
        String ip = s.substring(0, s.indexOf(' '));
        String dateTime = s.substring(s.indexOf('['), s.indexOf(']') + 1);
        String tmpRequestMethod = s.substring(s.indexOf('\"') + 1);
        int idx1 = tmpRequestMethod.indexOf('\"');
        String requestMethod = tmpRequestMethod.substring(0, idx1);
        String tmpResponceCode = tmpRequestMethod.substring(idx1 + 2);
        int idx2 = tmpResponceCode.indexOf(' ');
        String responseCode = tmpResponceCode.substring(0, idx2);
        String tmpResponseSize = tmpResponceCode.substring(idx2 + 1);
        String responseSize = tmpResponseSize.substring(0, tmpResponseSize.indexOf(' '));

        String referLink = "";
        String userAgent = "";
        int idx3 = getFirstLetterIndx(tmpResponseSize);
        if (idx3 != -1) {
            char ch = tmpResponseSize.charAt(idx3);
            if (ch == 'h' || ch == 'H') {
                String tmpReferLink = tmpResponseSize.substring(tmpResponseSize.indexOf(ch));
                int idx4 = tmpReferLink.indexOf("\" \"");
                referLink = tmpReferLink.substring(0, idx4);
                userAgent = tmpReferLink.substring(idx4 + 3, tmpReferLink.length() - 1);
            } else {
                userAgent = tmpResponseSize.substring(tmpResponseSize.indexOf(ch), tmpResponseSize.length() - 1);
            }
        }
        return new Line(ip, dateTime, requestMethod, responseCode, responseSize, referLink, userAgent);
    }

    int getFirstLetterIndx(String s) {
        int x = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                x = i;
                break;
            }
        }
        return x;
    }

    String getBot(String userAgent) {
        String fragment = "";
        String[] parts = userAgent.split(";");
        if (parts.length > 1) {
            for (String part : parts) {
                if (part.toLowerCase().contains("ot/")) {
                    fragment = part.substring(0, part.indexOf('/')).trim();
                }
            }

        }
        return fragment;
    }

    double getShereOtTotal(int allCnt, int botCnt) {
        double res = (double) botCnt / allCnt * 100;
        res = Math.round(res * 100);
        return res / 100;
    }
}
