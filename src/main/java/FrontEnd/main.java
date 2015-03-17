package FrontEnd;

import BackEnd.BackendController;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Scanner;
/**
 * Created by arian on 2/17/15.
 */
class main {
    public static void main(String[] args) {
        ObjectId documentId = null;
        //get the movie name and set it into JSONCotroller for process
        //TODO: have to change the movie input to user search
        String moviename=getInput("Enter the movie Name : ");
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
            int choice=Integer.parseInt(getInput("Choose movie you want "));
            if (!mObj.duplicateChecker(mObjects.get(choice).getName(),mObjects.get(choice).getYear())){
            documentId=mObj.movieInserter(mObjects.get(choice));
                //TODO: user UI changer
            messagePrinter("The new Item already inserted : "+mObjects.get(choice).getName());}
            else {
                //TODO: user UI changer
                messagePrinter("The movie is already in Database");
            }
            //to extract the added movie instantly
            BackendController backendController=new BackendController();
            backendController.ExtractOneMovie(documentId);

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
