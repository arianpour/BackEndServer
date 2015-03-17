package BackEnd.Extracter.torrentz;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class movieExtractsTz {
    public static final String THE_LINKSFINAL_IS_NULL = "the linksfinal is null";
    public static final String THATS_SAD = "Thats sad";
    private String movieName=null;
    private int movieYear=0;
    private final String sUrl= new TzConst().getTzUrl();
    private final String qUrl= new TzConst().getSearchQurl();
    private final String regex1=".*(search|any|veri|my|pro|help|tracker|report|announcelist).*";
    private final List<Element> linksfinal=new ArrayList<>();
    public movieExtractsTz(String movieName,int year) {
        this.movieName = movieName;
        this.movieYear=year;
    }
    public String smallControll(String args,String link) throws IOException {
            if (linksfinal!=null){
                switch (args){
                    case "new":

                       return mainExtracter(linkcreater(qUrl, movieName,movieYear), regex1, movieYear, new TzConst().movieKeyWords, new TzConst().nMovieKyewords);
                    case "old":
                        linksfinal.clear();
                        if (link!=null) {
                            return mainExtracter(linkcreater(link,null,0),regex1, 0,new TzConst().webLists,null);
                        }else{
                            System.out.println(THE_LINKSFINAL_IS_NULL);
                        }
                }
            }else { System.out.print(THATS_SAD);}
        return null;
    }

    String mainExtracter(String url, String pString, int movieyear, String[] poKeyword, String[] negKeyword) throws IOException {
        Document document = Jsoup.connect(url).userAgent("mozilla").get();
        Elements links = document.select("a[href]");
        String BestLink;
        for (Element link : links) {
                if (!Pattern.compile(pString).matcher(link.attr("href")).find()) {
                    linksfinal.add(link);
                    System.out.println(link);
                }
        }
        BestLink=getbestLink(linksfinal,movieyear,poKeyword,negKeyword);
        System.out.println(BestLink);
        return BestLink;
    }
    String linkcreater(String searchQ, String name, int year){
        if(name!=null){
            return sUrl+qUrl+name+"+"+year;
        }else {
            return sUrl+searchQ;
        }
    }

    private String getbestLink(List<Element> links,int movieyear,String[] poKeyword,String[] negKeyword) {
            int tmpPoint=0;
            int tmpPoint2=0;
            String best=null;
            for (int i=0;i<links.size();i++){
                if (movieyear==0 || Pattern.compile(".*"+movieyear+".*").matcher(links.get(i).text()).find()) {
                    for (String s : poKeyword) {
                        if (Pattern.compile(".*" + s + ".*").matcher(links.get(i).text()).find()) {
                            ++tmpPoint;
                        }
                    }
                    if (negKeyword!=null) {
                        for (String s : negKeyword) {
                            if (Pattern.compile(".*" + s + ".*").matcher(links.get(i).text()).find()) {
                                --tmpPoint;
                            }
                        }
                    }
                    if (tmpPoint > tmpPoint2) {
                        tmpPoint2 = tmpPoint;
                        best = links.get(i).attr("href");
                    }
                }
            }
        if (movieyear!=0 && best!=null) {
            best = trim(best);//aee2bcb4da9a5650941c64b127f491683c273d86
        }
        System.out.println(best);
        return best;
    }
    String trim(String link){
        return link.replace("/","");
    }
}
