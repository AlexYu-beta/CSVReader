import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by alex on 3/9/17.
 */
public class CSVParser {
    static Set<Integer> codeSet=new LinkedHashSet<>();
    //do not run it again!!!
    public static void main(String args[])throws Exception{
        long read_start=System.currentTimeMillis();         //start the timer
        String fileName="/home/alex/Documents/ProjectDataSources/股票历史数据ALL.csv";
        String path="/home/alex/Documents/ProjectDataSources/StockDataSplit";
        ArrayList<String[]> csvList = new ArrayList<String[]>();
        CsvReader reader = new CsvReader(fileName,'\t', Charset.forName("utf8"));
        CsvWriter writer=null;
        reader.readHeaders(); //jump the headers
        while(reader.readRecord()){
            csvList.add(reader.getValues());
        }
        reader.close();
        //parse the csvList
        for(int row=0;row<csvList.size();row++){
            codeSet.add(Integer.parseInt(csvList.get(row)[8]));
        }

        for(int code:codeSet){
            //create a folder
            File file=new File(path);
            file.mkdirs();
            writer=new CsvWriter(path+"/"+code+".csv",'\t',Charset.forName("utf8"));
            for(int row=0;row<csvList.size();row++){
                if(Integer.parseInt(csvList.get(row)[8])==code){
                    writer.writeRecord(csvList.get(row));
                }
            }
            writer.close();
        }
    }
}
