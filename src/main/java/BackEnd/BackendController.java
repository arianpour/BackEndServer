package BackEnd;

import BackEnd.Extracter.Controller;
import BackEnd.db.dbAdapter;

import java.io.IOException;

/**
 * Created by arian on 2/8/15.
 */
class BackendController {
public static void main(String[] args) throws IOException {

    dbAdapter mydb=new dbAdapter();
    if (mydb.getAllflag(1).hasNext()){
    Controller ct=new Controller( mydb.getAllflag(1));
        ct.startExtract();
    }else{
        System.out.println("there is not new file added");
    }
}
}
