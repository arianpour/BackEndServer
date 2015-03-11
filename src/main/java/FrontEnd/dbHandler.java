package FrontEnd;

import BackEnd.db.dbAdapter;

/**
 * insert clean data into database for extracting progress
 * 10/03/2015 just for movies
 */
public class dbHandler {

    public void movieInserter(MOVIEObject obj){
        dbAdapter db=new dbAdapter();
        db.insertData(obj.getName(), obj.getYear(), 1, "movie", obj.getThumbail(), obj.getReleaseDate());
    }
    public boolean duplicateChecker(String name,int year) {
        dbAdapter db = new dbAdapter();
        boolean Result = false;
        System.out.println(db.getid("name", name, "_id"));
        if (db.getid("name", name, "_id") != null) {
            String result = db.getid("name", name, "_id");
            if (result != null) {
                String x=db.getid("_id", result, "year");
                if (x.equals(year)) {
                    Result = true;
                } else {
                    Result = false;
                }

            }
        }
            return Result;

    }
}
