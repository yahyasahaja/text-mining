import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMS {
    private String body;
    private String label;
    private List<String> result = new ArrayList<String>();

    public SMS(String body, String label) {
        this.body = body;
        this.label = label;
        setBody(body);
    }

    public List<String> getResult() {
        return result;
    }

    public String getBody() {
        return body;
    }

    public String getLabel() {
        return label;
    }

    public void setBody(String body) {
        String line = body;

        // Regex untuk hapus link
        String pattern = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        line = m.replaceAll("");

        // Regex untuk mengambil kata
        String pattern2 = "[a-zA-Z]+";
        r = Pattern.compile(pattern2);
        m = r.matcher(line);

        while(m.find()){
            result.add(m.group().toLowerCase());
        }
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
