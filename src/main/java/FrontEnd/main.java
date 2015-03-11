package FrontEnd;

import java.util.List;
import java.util.Scanner;

/**
 * Created by arian on 2/17/15.
 */
class main {
    public static void main(String[] args) {

        //get the movie name and set it into JSONCotroller for process
        String moviename=getInput("Enter the movie Name : ");
        JSONController m=new JSONController(moviename);
       //return movie object if exist and return null
        List<MOVIEObject> mObjects = m.controller();
        if(mObjects!=null) {
            for (int i = 0; i < mObjects.size(); i++) {
                System.out.println(i+"."+mObjects.get(i).getName() + "=>" + mObjects.get(i).getThumbail()
                        + "=>" + mObjects.get(i).getYear() + "=>" + mObjects.get(i).getAudience_score());
            }
        }
        dbHandler mObj=new dbHandler();
        if (mObjects != null) {
            int choice=Integer.parseInt(getInput("Choose movie you want "));
            if (!mObj.duplicateChecker(mObjects.get(choice).getName(),mObjects.get(choice).getYear())){
            mObj.movieInserter(mObjects.get(choice));
            messagePrinter("The new Item already inserted : "+mObjects.get(choice).getName());}
            else {
                messagePrinter("The movie is already in Database");
            }
        }
    }

    private static void messagePrinter(String message) {
        System.out.println(message);
    }

    protected static String getInput(String message){
        Scanner input=new Scanner(System.in);
        System.out.println(message);
        String s= input.nextLine();
        return String.valueOf(s);
    }
}
