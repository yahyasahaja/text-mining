import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {
    private List<SMS> listSms = new ArrayList<SMS>();

    //CSV Parser
    public App(Iterator<CSVRecord> recordIterator) {
        while (recordIterator.hasNext()) {
            CSVRecord record = recordIterator.next();
            listSms.add(
                    new SMS(
                            record.get(0),
                            record.get(1)
                    )
            );
        }
    }

    //Case Folding
    public void caseFolding(){
        List<SMS> smsCaseFolding = new ArrayList<SMS>();
        for (SMS sms : listSms){
            smsCaseFolding.add(
                new SMS(
                    sms.getBody().toLowerCase(),
                    sms.getLabel()
                )
            );
        }

        listSms = smsCaseFolding;
    }

    //Print Dataset
    public void printDataSet(){
        for (SMS sms : listSms){
            System.out.printf("%s \n", sms.getBody());
        }
    }
}
