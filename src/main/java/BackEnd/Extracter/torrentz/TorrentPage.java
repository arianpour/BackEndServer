package BackEnd.Extracter.torrentz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by arian on 2/12/15.
 */
public class TorrentPage {
    private Elements elements= new Elements();
    public TorrentPage(String pageUrl, String tag){
        try {
            Document document= Jsoup.connect(pageUrl).userAgent("mozilla").get();
            elements=document.select(tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String linkFinder(String rePattern){
        String link = null;
        for (Element element:elements){
            if (Pattern.compile(".*"+rePattern).matcher(element.attr("href")).find()){
                link= element.attr("href");
            }
        }
        System.out.println(link);
        return link;
    }
}
