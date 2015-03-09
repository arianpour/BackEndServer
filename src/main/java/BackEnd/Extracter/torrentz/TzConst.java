package BackEnd.Extracter.torrentz;

/**
 * Created by arian on 2/9/15.
 */
class TzConst {
    private final String TzUrl="https://torrentz.eu/";
    private final String SearchQurl="search?q=";
    public final String[] movieKeyWords={"English","BrRip"};
    public final String[] nMovieKyewords={"hindi","French","Dublado","spanish"};
    public final String[] webLists={"1337x","kickass"};
    public String getTzUrl() {
        return TzUrl;
    }

    public String getSearchQurl() {
        return SearchQurl;
    }

    public String[] getMovieKeyWords() {
        return movieKeyWords;
    }

    public String[] getnMovieKyewords() {
        return nMovieKyewords;
    }
}
