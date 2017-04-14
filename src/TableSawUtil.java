import com.github.lwhite1.tablesaw.api.CategoryColumn;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.reducing.CrossTab;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.util.List;

import static com.github.lwhite1.tablesaw.api.QueryHelper.column;
import static com.github.lwhite1.tablesaw.api.QueryHelper.either;
import static com.github.lwhite1.tablesaw.api.QueryHelper.or;

/**
 * Created by alex on 4/8/17.
 */
public class TableSawUtil {

    Table tornadoes;

    public TableSawUtil() throws IOException {
        tornadoes=Table.createFromCsv("/home/alex/Documents/ProjectDataSources/tornado/tornadoes_1950-2014.csv");
    }

    public void printColumnNamesDemo(){
        List<String> columnNames=tornadoes.columnNames();
        for(String str:columnNames){
            System.out.print(str+"  ");
        }
        System.out.println();
    }

    public void tableShapeDemo(){
        System.out.println(tornadoes.shape());
    }

    public void tableStructureDemo(){
        System.out.println(tornadoes.structure().print());
    }

    public void firstDemo(int n){
        System.out.println(tornadoes.first(n).print());
    }

    public void addColumnDemo(){
        CategoryColumn month=tornadoes.dateColumn("Date").month();
        tornadoes.addColumn(month);
        firstDemo(5);
    }

    public void sortDemo(){
        tornadoes.sortOn("Fatalities");
        firstDemo(5);
    }

    public void summaryDemo(){
        System.out.println(tornadoes.column("Fatalities").summary().print());
    }

    public void select_where_demo(){
        System.out.println("filtering fatalities greater than 0");
        System.out.println(tornadoes.selectWhere(column("Fatalities").isGreaterThan((float)0)).first(5).print());
        System.out.println("filtering date in April");
        System.out.println(tornadoes.selectWhere((column("Date")).isInApril()).first(5).print());
        System.out.println("filtering either width greater than 300 or length greater than 10");
        System.out.println(tornadoes.selectWhere(either(column("width").isGreaterThan((float)300)
                                                        ,column("Length").isGreaterThan((float)10))).first(5).print());
        System.out.println("filtering date in Q2");
        System.out.println(tornadoes.select("State","Date").where(column("Date").isInQ2()).first(5).print());
    }

    public void crossTabDemo(){
        System.out.println(CrossTab.xCount(tornadoes,tornadoes.categoryColumn("State")
                                                    ,tornadoes.shortColumn("Scale")).first(5).print());
    }


    public static void main(String args[]) throws IOException {
        TableSawUtil tableSawUtil=new TableSawUtil();
        System.out.println("the column names are:");
        tableSawUtil.printColumnNamesDemo();
        System.out.println("the shape of the table is:");
        tableSawUtil.tableShapeDemo();
        System.out.println("the structure of the table is:");
        tableSawUtil.tableStructureDemo();
        System.out.println("the first 5 row of the table is:");
        tableSawUtil.firstDemo(5);
        System.out.println("add a new column 'month':");
        tableSawUtil.addColumnDemo();
        System.out.println("sort out the table:");
        tableSawUtil.sortDemo();
        System.out.println("summary on the column 'Fatalities':");
        tableSawUtil.summaryDemo();
        System.out.println("filtering the table");
        tableSawUtil.select_where_demo();
        //System.out.println("cross tabulation demonstration:");
        //tableSawUtil.crossTabDemo();



        System.out.println();
    }
}
