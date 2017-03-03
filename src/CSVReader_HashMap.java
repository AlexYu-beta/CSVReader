import com.csvreader.CsvReader;

import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by alex on 3/3/17.
 */

public class CSVReader_HashMap {
    public static void main(String args[]) throws Exception {
        long read_start=System.currentTimeMillis();         //start the timer
        String fileName="/home/alex/Documents/ProjectDataSources/股票历史数据ALL.csv";
        LinkedHashMap<Integer,String[]> csvList=new LinkedHashMap<Integer,String[]>();
        CsvReader reader = new CsvReader(fileName,'\t',Charset.forName("utf8"));

        reader.readHeaders(); //jump the headers
        String[] line=new String[10];
        int i;
        int count=0;
        while(reader.readRecord()){
            for(i=0;i<10;i++){
                line[i]=reader.get(i);
            }
            csvList.put(count,line);
            count++;
        }
        reader.close();
        String[] buff;
        long read_end=System.currentTimeMillis();
        Set set=csvList.keySet();
        for(Object arr:set){
            buff=csvList.get(arr);
            for(i=0;i<10;i++){
                System.out.print(buff[i]+" , ");
            }
            System.out.println();

        }
        long print_end=System.currentTimeMillis();
        System.out.println("it takes:  "+(read_end-read_start)/1000.0+" seconds to finish reading csv file");
        System.out.println("it takes:  "+(print_end-read_end)/1000.0+" seconds to finish printing csv file");

    }
}


