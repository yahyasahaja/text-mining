import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMS {
    private String body;
    private String label;
    private List<String> result = new ArrayList<String>();
    public List<String> stoplist = Arrays.asList(new String[]{"yang", "untuk", "pada", "ke", "para", "namun", "menurut", "antara", "dia", "dua", "ia", "seperti", "jika", "sehingga", "kembali", "dan", "tidak", "ini", "karena", "kepada", "oleh", "saat", "harus", "sementara", "setelah", "belum", "kami", "sekitar", "bagi", "serta", "di", "dari", "telah", "sebagai", "masih", "hal", "ketika", "adalah", "itu", "dalam", "bisa", "bahwa", "atau", "hanya", "kita", "dengan", "akan", "juga", "ada", "mereka", "sudah", "saya", "terhadap", "secara", "agar", "lain", "anda", "begitu", "mengapa", "kenapa", "yaitu", "yakni", "daripada", "itulah", "lagi", "maka", "tentang", "demi", "dimana", "kemana", "pula", "sambil", "sebelum", "sesudah", "supaya", "guna", "kah", "pun", "sampai", "sedangkan", "selagi", "sementara", "tetapi", "apakah", "kecuali", "sebab", "selain", "seolah", "seraya", "seterusnya", "tanpa", "agak", "boleh", "dapat", "dsb", "dst", "dll", "dahulu", "dulunya", "anu", "demikian", "tapi", "ingin", "juga", "nggak", "mari", "nanti", "melainkan", "oh", "ok", "seharusnya", "sebetulnya", "setiap", "setidaknya", "sesuatu", "pasti", "saja", "toh", "ya", "walau", "tolong", "tentu", "amat", "apalagi", "bagaimanapun", "yg"});

    public SMS(){

    }

    public SMS(String body){
        this.body = body;
        setBody(body);
    }

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

        while (m.find()) {
            if (!stoplist.contains(m.group().toLowerCase()))
                result.add(m.group().toLowerCase());
        }
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
