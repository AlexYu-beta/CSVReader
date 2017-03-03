import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex on 3/2/17.
 */
public class CSVReader_demo {

    private String inputCsvFile;                    // Saved input CSV file pathname
    private String spaceMark=",";                   // Space mark , ; : etc.

    public CSVReader_demo(String inputCsvFile, String spaceMark) {
        this.inputCsvFile = inputCsvFile;
        this.spaceMark = spaceMark;
    }

    public CSVReader_demo(String inputCsvFile) {
        this.inputCsvFile = inputCsvFile;
        this.spaceMark=",";
    }

    public Object[] getParsedArray() throws Exception{
        List<List<String>> retval=new ArrayList<List<String>>();

        String regExp = getRegExp();
        BufferedReader in = new BufferedReader(new FileReader(this.inputCsvFile));
        String strLine;
        String str = "";

        while ((strLine = in.readLine()) != null) {
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(strLine);
            List<String> listTemp = new ArrayList<String>();
            while (matcher.find())
            {
                str = matcher.group();
                str = str.trim();

                if (str.endsWith(spaceMark))
                {
                    str = str.substring(0, str.length() - 1);
                    str = str.trim();
                }

                if (str.startsWith("\"") && str.endsWith("\""))
                {
                    str = str.substring(1, str.length() - 1);
                    if (CSVReader_demo.isExisted("\"\"", str))
                    {
                        str = str.replaceAll("\"\"", "\"");
                    }
                }

                if (!"".equals(str))
                {
                    listTemp.add(str);
                }
            }

            // Add to retval
            retval.add(listTemp);
        }
        in.close();

        return retval.toArray();
    }


    private String getRegExp()
    {
        final String SPECIAL_CHAR_A = "[^\",\\n 　]";
        final String SPECIAL_CHAR_B = "[^\""+spaceMark+"\\n]";

        StringBuffer strRegExps = new StringBuffer();
        strRegExps.append("\"((");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*["+spaceMark+"\\n 　])*(");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"{2})*)*");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"[ 　]*"+spaceMark+"[ 　]*");
        strRegExps.append("|");
        strRegExps.append(SPECIAL_CHAR_B);
        strRegExps.append("*[ 　]*"+spaceMark+"[ 　]*");
        strRegExps.append("|\"((");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*["+spaceMark+"\\n 　])*(");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"{2})*)*");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"[ 　]*");
        strRegExps.append("|");
        strRegExps.append(SPECIAL_CHAR_B);
        strRegExps.append("*[ 　]*");
        return strRegExps.toString();
    }

    private static boolean isExisted(String argChar, String argStr)
    {

        boolean blnReturnValue = false;
        if ((argStr.indexOf(argChar) >= 0)
                && (argStr.indexOf(argChar) <= argStr.length()))
        {
            blnReturnValue = true;
        }
        return blnReturnValue;
    }

    public static void main(String args[])throws Exception{
        long read_start=System.currentTimeMillis();         //start the timer
        CSVReader_demo parser=new CSVReader_demo("/home/alex/Documents/ProjectDataSources/股票历史数据ALL.csv",";");
        Object[] arr=parser.getParsedArray();
        long itemNum=0;
        long read_end=System.currentTimeMillis();
        for(Object obj:arr){
            itemNum++;
            List<String> ls=(List<String>)obj;

            for(String item:ls){
                if(itemNum==1){
                    System.out.println(item+" , ");
                }
                System.out.println(item+" , ");
            }

            System.out.println(" ,");
        }
        long print_end=System.currentTimeMillis();
        System.out.println(itemNum);
                  //pause the timer
        System.out.println("it takes:  "+(read_end-read_start)/1000.0+" seconds to finish reading csv file");
        System.out.println("it takes:  "+(print_end-read_end)/1000.0+" seconds to finish printing csv file");
    }
}
