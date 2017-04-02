import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.python.core.PyObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by alex on 4/1/17.
 */
public class CSVPyReader {

    public static void main(String args[]) throws IOException {
        File file =new File("");
        String path = file.getCanonicalPath();
        String[] str=new String[2];
        str[0]="paths";
        str[1]="data";
        List<PyObject> pos=JythonUtil.transP2JData(path+"/pyscript/CSVPyReader.py",str);
        PyObject paths=pos.get(0);
        PyObject data=pos.get(1);
        try{
            for(int i=0;i<100;i++){
                System.out.println(paths.__getitem__(i).toString());
            }
        }catch (Exception e){
        }

    }
}
