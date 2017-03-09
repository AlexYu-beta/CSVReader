import com.csvreader.CsvReader;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by alex on 3/3/17.
 */

public class CSVReader_API {
    public static void main(String args[]) throws Exception {
        long read_start=System.currentTimeMillis();         //start the timer
        String fileName="/home/alex/Documents/ProjectDataSources/股票历史数据ALL.csv";
        ArrayList<String[]> csvList = new ArrayList<String[]>();
        CsvReader reader = new CsvReader(fileName,'\t',Charset.forName("utf8"));

        reader.readHeaders(); //jump the headers
        String[] line=new String[10];
        int i;
        while(reader.readRecord()){
            csvList.add(reader.getValues());
        }
        reader.close();
//        long read_end=System.currentTimeMillis();
//        for(int row=0;row<csvList.size();row++){
//            for(i=0;i<11;i++){
//                System.out.print(csvList.get(row)[i]+" , ");
//            }
//            System.out.println();
//
//        }
//        long print_end=System.currentTimeMillis();
//        System.out.println("it takes:  "+(read_end-read_start)/1000.0+" seconds to finish reading csv file");
//        System.out.println("it takes:  "+(print_end-read_end)/1000.0+" seconds to finish printing csv file");
        int countA=0;
        int countB=0;
        int countC=0;
        for(int row=0;row<csvList.size();row++){
            if(csvList.get(row)[8].compareTo("402")==0){
                countA++;
            }else if(csvList.get(row)[8].compareTo("402")<0){
                countB++;
            }else countC++;
        }
        System.out.println(countA+"  "+countB+"  "+countC);

    }
}

//it takes:  1.468 seconds to finish reading csv file
//it takes:  12.613 seconds to finish printing csv file

