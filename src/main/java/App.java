import org.apache.commons.csv.CSVRecord;

import java.math.BigDecimal;
import java.util.*;

public class App {
    private List<SMS> listSms = new ArrayList<SMS>();
    private List<SMS> listSmsTest = new ArrayList<SMS>();
    private List<String> listUnik = new ArrayList<String>();
    private HashMap<String, Term> listTerm = new HashMap<String, Term>();
    private static final int LABEL_LENGTH = 3;
    private double[] priors = new double[LABEL_LENGTH];

    //CSV Parser
    public App(Iterator<CSVRecord> recordIterator, Iterator<CSVRecord> recordIteratorTest) {
        //get data training
        while (recordIterator.hasNext()) {
            CSVRecord record = recordIterator.next();
            listSms.add(
                    new SMS(
                            record.get(0),
                            record.get(1)
                    )
            );
            priors[Integer.parseInt(record.get(1))]++;
        }

        //get data test
        while (recordIteratorTest.hasNext()){
            CSVRecord record = recordIteratorTest.next();
            listSmsTest.add(
                new SMS(
                        record.get(0),
                        record.get(1)
                )
            );
        }

//        for (SMS sms : listSmsTest){
//            System.out.println(sms.getResult());
//        }
    }

    // Token Unik
    public void termUnix(){
        for (SMS sms : listSms){
            for (String result : sms.getResult()){
                if(!listUnik.contains(result)){
                    listUnik.add(result);
                }
            }
        }
//        System.out.println(listUnik.size());
    }

    // Hitung TfIdf & hitung rata kelas & hitung varian kelas
    public void hitungTfIdf(){
        for (int i = 0 ; i < listUnik.size(); i++){
            String unik = listUnik.get(i);
            Term term = new Term();

            for (int j = 0 ; j < listSms.size(); j++){
                SMS sms = listSms.get(j);
                int tf = 0;

                for (String result : sms.getResult()){
                    if(result.equals(unik)){
                        tf++;
                    }
                }

                if(!listTerm.containsKey(unik)) {
                    listTerm.put(unik, new Term(listSms.size(), unik));
                }

                (term = listTerm.get(unik)).add(j, tf);

            }

            term.calculateTfIdf();
//            System.out.println(term.getNama() +" : "+ Arrays.toString(term.getListWtf()));

            // Hitung rata term kelas
            for (int j = 0 ; j < listSms.size(); j++){
                SMS sms = listSms.get(j);
                int kelas = Integer.parseInt(sms.getLabel());

                term.getListWtfKelas()[kelas] += term.getListWtf()[j];
                term.getJumlahKelas()[kelas]++;
            }
            term.hitungRataKelas();
//            System.out.println(term.getNama() +" : "+ Arrays.toString(term.getListRataKelas()));

            // Hitung varian term kelas
            for (int j = 0 ; j < listSms.size(); j++){
                SMS sms = listSms.get(j);
                int kelas = Integer.parseInt(sms.getLabel());

                term.getListVarianKelas()[kelas] += Math.pow(term.getListWtf()[j] - term.getListRataKelas()[kelas], 2);
            }
            term.hitungVarians();

//            System.out.println(unik +" : "+Arrays.toString(term.getListWtfKelas()));
//            System.out.printf("%-15s : %s\n",unik, Arrays.toString(listTerm.get(unik).getListWtf()));
        }
    }

    //GO
    public void testing(){
        int totalBenar = 0;
        for (SMS sms : listSmsTest){
            BigDecimal[] conditionalProbabilityKelas = {BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1)};
            BigDecimal maxPosterior = BigDecimal.valueOf(-1);
            int kelas = -1;

            //hitung gausian dan conditional probability
            for(String unik : sms.getResult()){
                if (!listTerm.containsKey(unik)) continue;
                Term term = listTerm.get(unik);
                term.hitungGaussian();
                for (int i = 0; i < LABEL_LENGTH; i++) {
//                    conditionalProbabilityKelas[i] *= term.getListGaussianKelas()[i];
                    conditionalProbabilityKelas[i] = conditionalProbabilityKelas[i].multiply(term.getListGaussianKelas()[i]);
                }
            }

            //hitung posterior (conditional probability * prior) dan cari yang terbesar
            BigDecimal[] posteriors = new BigDecimal[LABEL_LENGTH];
            for (int i = 0; i < LABEL_LENGTH; i++) {
//                posteriors[i] = priors[i] * conditionalProbabilityKelas[i];
                posteriors[i] = BigDecimal.valueOf(priors[i]).multiply(conditionalProbabilityKelas[i]);

//                System.out.println(posteriors[i] + " " + maxPosterior + " " +(posteriors[i] > maxPosterior));

                //get maximum posterior and it's class
//                System.out.println(posteriors[i]);
                if (posteriors[i].compareTo(maxPosterior) >= 0) {
                    maxPosterior = posteriors[i];
                    kelas = i;
                }

//                System.out.println(posteriors[i].compareTo(maxPosterior));
            }

            if (kelas == Integer.parseInt(sms.getLabel())) totalBenar++;
            System.out.println(sms.getBody() + " : " + kelas);
        }

        System.out.println("Total benar: " + totalBenar + " / " + listSmsTest.size());
        System.out.println("Akurasi: " + (double) totalBenar / listSmsTest.size());
    }

    // Hitung prior
    public void hitungPrior(){
        for (int i = 0; i < LABEL_LENGTH; i++) {
            priors[i] = priors[i]/listSms.size();
        }

//        System.out.println(Arrays.toString(priors));
    }

}
