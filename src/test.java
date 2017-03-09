import java.io.File;
import java.io.IOException;

/**
 * Created by alex on 3/4/17.
 */
public class test {
    public static void main(String args[]){
        File directory=new File("");
        try {
            String path=directory.getCanonicalPath();
            System.out.print(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
