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
////
    }
//// --Commented out by Inspection STOP (2/20/15 9:27 AM)
//
    public UpdateResult insertdata(String magnet,ObjectId id){
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
    public String getid(String name){
        Document doc=collection.find(new Document("name",name)).first();
        return doc.get("_id").toString();
    }
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
