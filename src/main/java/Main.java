import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

public class Main {
    private static App app;
    public static void main(String[] args) {
        Reader reader;
        try {

            //CSV Parser
            reader = new FileReader("data_latih_v2.csv");
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            Iterator<CSVRecord> iterator = records.iterator();

            reader = new FileReader("data_uji_v2.csv");
            Iterable<CSVRecord> recordsTest = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            Iterator<CSVRecord> iteratorTest = recordsTest.iterator();

            //Init App
            app = new App(iterator, iteratorTest);
						app.termUnix();
						app.hitungTfIdf();
            app.hitungPrior();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
