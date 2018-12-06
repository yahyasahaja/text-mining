import java.math.BigDecimal;
import java.util.Arrays;

public class Term {

    private double[] listTfIdf;
    private int df = 0;
    private int n;
    private double[] listWtfKelas;
    private int[] jumlahKelas;
    private double[] listRataKelas;
    private double[] listVarianKelas;
    private BigDecimal[] listGaussianKelas;
    private String nama;

    public Term() {}

    public Term(int nDokument, String nama) {
        this.n = nDokument;
        listTfIdf = new double[nDokument];
        listWtfKelas = new double[3];
        listRataKelas = new double[3];
        listVarianKelas = new double[3];
        jumlahKelas = new int[3];
        listGaussianKelas = new BigDecimal[3];
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

    public void hitungVarians() {
        for(int i = 0 ; i < listVarianKelas.length; i++){
            listVarianKelas[i] /= jumlahKelas[i];
        }
    }

    public void hitungGaussian(){
        for(int i = 0 ; i < listGaussianKelas.length; i++){
            if (listVarianKelas[i] == 0) {
//                listGaussianKelas[i] = Math.pow(10, -300);//1d * Math.pow(10, 10); // handling ketika varian nol.
                listGaussianKelas[i] = BigDecimal.valueOf(1);
            }else{

//                listGaussianKelas[i] = ((
//                        Math.pow(Math.E, -(Math.pow(listWtfKelas[i] - listRataKelas[i], 2)) / (2 * listVarianKelas[i]))
//                                / Math.sqrt(2 * Math.PI * listVarianKelas[i])
//                )+ Math.pow(10, -300)); //Terlalu kecil untuk

                listGaussianKelas[i] = BigDecimal.valueOf((
                        Math.pow(Math.E, -(Math.pow(listWtfKelas[i] - listRataKelas[i], 2)) / (2 * listVarianKelas[i]))
                                / Math.sqrt(2 * Math.PI * listVarianKelas[i])
                )).add(BigDecimal.valueOf(1)); //Terlalu kecil untuk

                //* Math.pow(10, 10); //untuk scaling dan handling ketika varian nol.
//            System.out.println(-(Math.pow(listWtfKelas[i] - listRataKelas[i], 2)) / (2 * listVarianKelas[i]));
//            System.out.println("Wtf Kelas : "+this.getNama()+", "+i+" : "+listWtfKelas[i]);
//            System.out.println("Rata Kelas : "+this.getNama()+", "+i+" : "+listRataKelas[i]);
//            System.out.println(this.getNama()+", "+i+" : "+listVarianKelas[i]);
            }
//            System.out.println(this.getNama()+", " + i +" : "+ listGaussianKelas[i]);
//            System.out.printf("%s, %d: %.1000f\n", this.getNama(), i, listGaussianKelas[i]);
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal[] getListGaussianKelas() {
        return listGaussianKelas;
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

    public double[] getListVarianKelas() {
        return listVarianKelas;
    }

    public double getIdf() {
        return Math.log10((double)n / df);
    }

    public double[] getListWtf() {
        return listTfIdf;
    }

    public void calculateTfIdf() {
        for (int i = 0; i < listTfIdf.length; i++) {
            listTfIdf[i] *= getIdf();
        }
    }

}
