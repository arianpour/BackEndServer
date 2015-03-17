package BackEnd;

import BackEnd.Extracter.Controller;
import BackEnd.db.dbAdapter;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * stand alone backend Server to find all items without magnet links
 * extract and insert the link into documents
 * ( flag 1 is for all items without magnet links )
 * Created by arian on 2/8/15.
 */
public class BackendController {
public boolean ExtractAllNewMovies() throws IOException {

    dbAdapter mydb=new dbAdapter();
    //get all items without magnet link
    if (mydb.getAllflag(1).hasNext()){
        Controller ct=new Controller( mydb.getAllflag(1));
        return ct.startExtract();
    }else{
    return  false;
    }
}

    // Only extract for one item , to search and get th manget link instantly
    public boolean ExtractOneMovie(ObjectId id){
        dbAdapter adapter=new dbAdapter();
        Controller ct=new Controller(adapter.getById(id));
        return ct.startExtract();
    }
}
