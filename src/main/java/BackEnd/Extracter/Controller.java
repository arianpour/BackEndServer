package BackEnd.Extracter;

import BackEnd.Extracter.torrentz.TorrentPage;
import BackEnd.Extracter.torrentz.movieExtractsTz;
import BackEnd.db.dbAdapter;
import org.bson.Document;

import java.io.IOException;
import java.util.Iterator;

// --Commented out by Inspection START (2/20/15 9:27 AM):
public class Controller {
    private Iterator<Document> mLists =null;
    private String magnet=null;
protected String torrentFile;
protected getTorrent LinkMagnet=new getTorrent(magnet);
/// --Commented out by Inspection START (2/20/15 9:27 AM):
    public Controller(Iterator<Document> mList) {
        this.mLists = mList;
    }
// --Commented out by Inspection STOP (2/20/15 9:27 AM)
    public void startExtract() {
        while(mLists.hasNext() ){
            Document aList= (Document) mLists.next();
            switch (aList.get("type").toString()){
                case "movie":
                    try {
                        BackEnd.Extracter.torrentz.movieExtractsTz obj = new movieExtractsTz(nameModifider(aList.getString("name")), aList.getInteger("year"));
                        String x = obj.smallControll("new", null);
                        String pageUrl=obj.smallControll("old", x);
                        System.out.println(pageUrl);
                        TorrentPage thePage=new TorrentPage(pageUrl,"a[href]");
                        magnet= thePage.linkFinder("magnet");
                        System.out.println(magnet);
                        System.out.println(aList.getObjectId("_id").toString());
                        System.out.println(new dbAdapter().insertdata(magnet,aList.getObjectId("_id")).getMatchedCount());
                        break;
                    }catch (IOException e){
                        System.out.print("TAG : "+e);
                   }

                case "tv":
                    System.out.println("TV");

                    break;
            }
        }
    }
    String nameModifider(String name){
        return name.replaceAll(" ","+");
    }
}
// --Commented out by Inspection STOP (2/20/15 9:27 AM)
