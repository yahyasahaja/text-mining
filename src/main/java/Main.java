import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Reader reader;
        try {

            //CSV Parser
            reader = new FileReader("data.csv");
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            Iterator<CSVRecord> iterator = records.iterator();

            //Init App
            App app = new App(iterator);
//            app.caseFolding();
            app.printDataSet();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
