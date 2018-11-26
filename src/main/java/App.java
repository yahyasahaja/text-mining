import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {
    private List<SMS> listSms = new ArrayList<SMS>();
    private List<String> listUnix = new ArrayList<String>();
    private int unixLength = 0;

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

//    //Case Folding
//    public void caseFolding(){
//        List<SMS> smsCaseFolding = new ArrayList<SMS>();
//        for (SMS sms : listSms){
//            smsCaseFolding.add(
//                new SMS(
//                    sms.getBody().toLowerCase(),
//                    sms.getLabel()
//                )
//            );
//        }
//
//        listSms = smsCaseFolding;
//    }


    // Token Unik
    public void termUnix(){
        for (SMS sms : listSms){
            for (String result : sms.getResult()){
                if(!listUnix.contains(result)){
                    listUnix.add(result);
                    unixLength++;
                }
            }
        }
    }

    //Print Dataset
    public void printDataSet(){
//        for (SMS sms : listSms){
//            System.out.printf("%s \n", sms.getResult());
//        }

        for (String unix : listUnix){
            System.out.printf("%s \n", unix);
        }
        System.out.println(unixLength);
    }
}
