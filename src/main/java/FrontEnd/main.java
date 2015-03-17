package FrontEnd;

import BackEnd.BackendController;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Scanner;
/**
 * Created by arian on 2/17/15.
 */
class main {

    public static final String ENTER_THE_MOVIE_NAME = "Enter the movie Name : ";
    public static final String CHOOSE_MOVIE_YOU_WANT = "Choose movie you want ";
    public static final String THE_NEW_ITEM_ALREADY_INSERTED = "The new Item already inserted : ";
    public static final String THE_MOVIE_IS_ALREADY_IN_DATABASE = "The movie is already in Database";
    public static final String THE_MAGNET_IS_ALREADY_ADDED = "the magnet is already added";
    public static final String CAN_ADD_MAGNET_TO_DATABASE = "can'add magnet to Database";

    public static void main(String[] args) {
        ObjectId documentId = null;
        //get the movie name and set it into JSONCotroller for process
        //TODO: have to change the movie input to user search
        String moviename=getInput(ENTER_THE_MOVIE_NAME);
        JSONController m=new JSONController(moviename);
       //return movie object if exist and return null
        List<MOVIEObject> mObjects = m.controller();
        if(mObjects!=null) {
            //TODO: user UI changer
            for (int i = 0; i < mObjects.size(); i++) {
                System.out.println(i+"."+mObjects.get(i).getName() + "=>" + mObjects.get(i).getThumbail()
                        + "=>" + mObjects.get(i).getYear() + "=>" + mObjects.get(i).getAudience_score());
            }
        }
        //insert data into database after checking no duplicate
        dbHandler mObj=new dbHandler();
        if (mObjects != null) {
            //TODO: change user input after connect to user interface
            int choice=Integer.parseInt(getInput(CHOOSE_MOVIE_YOU_WANT));
            if (!mObj.duplicateChecker(mObjects.get(choice).getName(),mObjects.get(choice).getYear())){
            documentId=mObj.movieInserter(mObjects.get(choice));
                //TODO: user UI changer
            messagePrinter(THE_NEW_ITEM_ALREADY_INSERTED +mObjects.get(choice).getName());}
            else {
                //TODO: user UI changer
                messagePrinter(THE_MOVIE_IS_ALREADY_IN_DATABASE);
            }
            //to extract the added movie instantly
            BackendController backendController=new BackendController();
           if (backendController.ExtractOneMovie(documentId)){
               messagePrinter(THE_MAGNET_IS_ALREADY_ADDED);
           }else{
               messagePrinter(CAN_ADD_MAGNET_TO_DATABASE);
           }

        }
    }
    //TODO: user UI changer
    private static void messagePrinter(String message) {
        System.out.println(message);
    }
    //TODO: user UI changer
    protected static String getInput(String message){
        Scanner input=new Scanner(System.in);
        System.out.println(message);
        String s= input.nextLine();
        return String.valueOf(s);
    }
}
