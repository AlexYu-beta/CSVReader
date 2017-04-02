/**
 * Created by alex on 3/9/17.
 */
class StockThread implements Runnable{
    int i=0;
    @Override
    public void run() {
        synchronized (this) {

        }
    }
}

public class CSVReader_API_MultiThread{

    public static void main(String args[]){
        for(int i=0;i<10;i++){
            new Thread(new StockThread()).start();
        }


    }
}
