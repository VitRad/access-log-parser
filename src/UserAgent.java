public class UserAgent {
    private final String typeOS;
    private final String browser;

    public UserAgent(String s) {
        String[] tmp = s.split(" \"");
        int idxTmpStart = tmp[3].indexOf(" (");
        int idxTmpEnd = tmp[3].indexOf(") ");
        String ss = null;
        if (idxTmpStart != -1 && idxTmpEnd != -1 && idxTmpStart < idxTmpEnd) {
            ss = tmp[3].substring(idxTmpStart + 1, idxTmpEnd + 1);
        }
        if (ss != null && !ss.contains("compatible") && ss.length() > 1 && !ss.contains("http")) {
            String[] sss = ss.split(";");
            if (sss.length == 1) {
                this.typeOS = sss[0].substring(sss[0].indexOf("(") + 1, sss[0].lastIndexOf(")"));
            } else {
                this.typeOS = sss[0].substring(sss[0].indexOf("(") + 1);
            }
            int idx3 = s.lastIndexOf(' ');
            int idx4 = s.lastIndexOf('/');
            if (idx3 < idx4) {
                String str = s.substring(s.lastIndexOf(' ') + 1, s.lastIndexOf('/'));
                if (str.toLowerCase().contains("http") || str.contains(";")) {
                    this.browser = "";
                } else this.browser = str;
            } else this.browser = "";
        } else {
            this.typeOS = "";
            this.browser = "";
        }
    }

    public String getTypeOS() {
        return typeOS;
    }

    public String getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "typeOS='" + typeOS + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }
}