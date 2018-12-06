import java.math.BigDecimal;
import java.util.Arrays;

public class Term {

    private double[] listTfIdf;
    private int df = 0;
    private int n;
    private String nama;
    private double[] listWtfKelas;
    private int[] jumlahKelas;
    private double[] listRataKelas;
    

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

    public void hitungRataKelas(){
        for(int i = 0 ; i < listRataKelas.length; i++){
            listRataKelas[i] = listWtfKelas[i]/jumlahKelas[i];
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int[] getJumlahKelas() {
        return jumlahKelas;
    }

    public double[] getListWtfKelas() {
        return listWtfKelas;
    }

    public double[] getListRataKelas() {
        return listRataKelas;
    }

    public double[] getListWtf() {
        return listTfIdf;
    }

}
