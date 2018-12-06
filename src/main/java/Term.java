import java.math.BigDecimal;
import java.util.Arrays;

public class Term {

    private double[] listTfIdf;
    private int df = 0;
    private int n;
    private String nama;

    public Term() {}

    public Term(int nDokument, String nama) {
        this.n = nDokument;
        listTfIdf = new double[nDokument];
        this.nama = nama;
    }

    public void add(int k, int tf) {
        if (tf == 0) {
            listTfIdf[k] = 0;
        }else {
            listTfIdf[k] = 1 + Math.log10(tf);
            df++;
        }
    }


    public double getIdf() {
        return Math.log10((double)n / df);
    }


    public void calculateTfIdf() {
        for (int i = 0; i < listTfIdf.length; i++) {
            listTfIdf[i] *= getIdf();
        }
    }

}
