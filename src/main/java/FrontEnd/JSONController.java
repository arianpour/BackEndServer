package FrontEnd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Get return the right movie details or a list of close ones
 * the results are from Rottentomato
 * the results are Movie name,release date in theater,thumbnail , year Made
 */
class JSONController {
    private String MovieName=null;

    public JSONController(String movieName) {
        MovieName = movieName;
    }
//control final exit of class
    public List<MOVIEObject> controller() {
        List<MOVIEObject> mObjects = getMovieDetails();
        if (mObjects != null) {
            return mObjects;
        } else {
            System.out.println("The movie you are looking for is not exist");
            return null;        }
    }
//get the JSON file from rottentomato and send to resealt checker method returns a List of options
List<MOVIEObject> getMovieDetails(){
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(readUrl(LinkMaker(nameMaker(MovieName, "search"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((Integer)jsonObject.get("total")!=0){
        JSONArray movies=jsonObject.getJSONArray("movies");
            return getBestResults(movies);}else{
            return null;
        }

    }
//choose the best result and return a set of clean List file
List<MOVIEObject> getBestResults(JSONArray movies){
        MOVIEObject movie=new MOVIEObject();
        List<MOVIEObject> items=new ArrayList<MOVIEObject>();

        outerlopp:
        for (int i = 0; i < movies.length(); i++) {
            movie=detailSetter(movies.getJSONObject(i));
            if (movie.getName().toLowerCase().equals(nameMaker(MovieName, "comprise"))) {
                if (items!=null)
                    items.clear();
                items.add(movie);
                break outerlopp;
            }else {
                items.add(movie);
            }
        }
        return items;
    }
//setting the JSON data into List customised object
    private MOVIEObject detailSetter(JSONObject movie){
        MOVIEObject item=new MOVIEObject();
        item.setName((String) movie.get("title"));
        item.setYear((Integer) movie.get("year"));
        item.setThumbail((String) movie.getJSONObject("posters").get("thumbnail"));
        item.setReleaseDate(setReDate(movie));
        return item;
    }
//checking if the released data exist
    private String setReDate(JSONObject object){
        String result = null;
        try {
            result = (String) object.getJSONObject("release_dates").get("theater");
        }catch (JSONException e){
            result = "not valid";
        }
            return result;
    }
//get the JSON file from website
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
//make the search link
String LinkMaker(String name){
    String searchLink = "http://api.rottentomatoes.com";
    String SERACH_KEY = "/api/public/v1.0/movies.json?q=";
    String QUERY_KEY = "qg9hr2mprfs5sj938rwsjdqc";
    String LIMITS_KEY = "&page_limit=10&page=1&apikey=";
    return searchLink + SERACH_KEY +name+ LIMITS_KEY + QUERY_KEY;
    }
//Modify the name and check if it is valid
String nameMaker(String name, String Tag){
        if (Tag.equals("comprise")) {
            name=name.trim().replaceAll("[^\\dA-Za-z]"," ").toLowerCase();
        } else if (Tag.equals("search")) {

            name = name.trim();
            name = name.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", "+").toLowerCase();
        }
        return name;
    }
}
