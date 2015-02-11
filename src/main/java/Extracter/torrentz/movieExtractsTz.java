package Extracter.torrentz;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class movieExtractsTz {
    protected String movieName=null;
    protected int movieYear=0;
    protected String sUrl= new TzConst().getTzUrl();
    protected String qUrl= new TzConst().getSearchQurl();
    protected Pattern regex = Pattern.compile(".*(search|any|veri|my|pro|help).*");
    protected List<Element> linksfinal=new ArrayList<>();
    public movieExtractsTz(String movieName,int year) {
        this.movieName = movieName;
        this.movieYear=year;
    }

    public String mainExtracter(String args) throws IOException {
        Document document = Jsoup.connect(sUrl + "/" + qUrl + movieName).get();
        Elements links = document.select("a[href]");
        String BestLink = null;
        for (Element link : links) {
                Matcher matcher1 = regex.matcher(link.attr("href"));
                if (!matcher1.find()) {
                    linksfinal.add(link);
                }
        }
        if (linksfinal!=null) {
            switch (args){
                case "new":
                    BestLink = getbestLink(linksfinal);
                    break;
                case "old":

                    break;
            }
        }
        else{
            System.out.print("Thats sad");
        }
        return BestLink;
    }

    private String getbestLink(List<Element> links) {
            int tmpPoint=0;
            int tmpPoint2=0;
            String best=null;
            for (int i=0;i<10;i++){
                if (Pattern.compile(".*"+movieYear+".*").matcher(links.get(i).text()).find()) {
                    for (String s : new TzConst().movieKeyWords) {
                        if (Pattern.compile(".*" + s + ".*").matcher(links.get(i).text()).find()) {
                            ++tmpPoint;
                        }
                    }
                    for (String s : new TzConst().nMovieKyewords) {
                        if (Pattern.compile(".*" + s + ".*").matcher(links.get(i).text()).find()) {
                            --tmpPoint;
                        }
                    }
                    if (tmpPoint > tmpPoint2) {
                        tmpPoint2 = tmpPoint;
                        best = links.get(i).attr("href");
                    }
                }
            }
        return best;
    }
}
