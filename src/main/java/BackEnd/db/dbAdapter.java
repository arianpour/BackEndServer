package BackEnd.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Iterator;

// --Commented out by Inspection START (2/20/15 9:27 AM):
///**
// * Database Functions
// * Created by arian on 2/8/15.
// */
public class dbAdapter {
    private MongoCollection<Document> collection=null;
//
//// --Commented out by Inspection START (2/20/15 9:27 AM):
    public dbAdapter() {
        String host = "localhost";
        int port = 27017;
        MongoClient mongoClient = new MongoClient(host, port);
        String db_name = "QueueIndexer";
        MongoDatabase database = mongoClient.getDatabase(db_name);
        String collection_name = "Queues";
        collection= database.getCollection(collection_name);
///
    }
//// --Commented out by Inspection STOP (2/20/15 9:27 AM)
//inset data into database for progress
    public void insertData(String name,int year,int flag,String type,
                                   String thumbnail,String releaseDate){

        Document doc=new Document("name",name)
                .append("year",year)
                .append("flag",flag)
                .append("type",type)
                .append("thumbnail",thumbnail)
                .append("releaseDate",releaseDate);
       collection.insertOne(doc);

    }
    public UpdateResult insertMagnet(String magnet, ObjectId id){
        UpdateResult result=null;
        result= collection.updateOne(new Document("_id", id), new Document("$set", new Document("flag", 2)));
        collection.updateOne(new Document("_id",id),new Document("$set",new Document("magnet",magnet)));
        return result;
    }
    public Iterator<Document> getAllflag(int flag){
        return collection.find(new Document("flag",flag)).iterator();
    }
//
    public boolean setUpdater(String id, int flag){
        UpdateResult res= collection.updateOne(new Document("_id", id), new Document("$set", new Document("flag", flag)));
        return res.wasAcknowledged();
    }
    public String getid(String element,String obj,String rElement){
        Document doc=collection.find(new Document(element,obj)).first();
        if (doc!=null){
            return doc.get(rElement).toString();
        }
        return null;
    }
//    public boolean itemChecker(String element,)

    public boolean delById(String id){

    try{
        DeleteResult result= collection.deleteMany(new Document("flag",id)); //collection.deleteOne(new Document("_id", id));
        return result.wasAcknowledged();
    }catch (Exception e){
        System.out.println("its failed: "+e);
    }
        return false;
    }
}
// --Commented out by Inspection STOP (2/20/15 9:27 AM)
