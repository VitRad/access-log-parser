public class UserAgent {
    private final String typeOS;
    private final String browser;

    public UserAgent(String s) {
        int idx1 = s.indexOf(" (");
        int idx2 = s.indexOf(")");
        String[] tmp = null;
        if (idx1 != -1 && idx2 != -1 && (idx1 < idx2)) {
            tmp = s.substring(s.indexOf(" (") + 1, s.indexOf(")")).split(";");
        }
        if (tmp != null && !tmp[0].contains("compatible") && tmp.length > 1) {
            this.typeOS = tmp[0];
            int idx3 = s.lastIndexOf(' ');
            int idx4 = s.lastIndexOf('/');
            if (idx3 < idx4) {
                this.browser = s.substring(s.lastIndexOf(' ') + 1, s.lastIndexOf('/'));
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