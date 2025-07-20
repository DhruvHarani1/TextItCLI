package com.TextIt.service.session;

import com.TextIt.database.DataBase;
import com.TextIt.service.user.UserData;

import java.io.*;

import static com.TextIt.model.utils.CommonMethods.openInNewCMD;


/**
 * For Keeping Track of Logged-in User And for easy tracking of it
 * Create The Session To persist
 */
public class SessionManger {

    private  UserData userData ;
    private DataBase dataBase = new DataBase();
    // It will Load During SignUP
    public SessionManger() {
    }

    public void autoLogin(){
        File file = new File("last_session.txt");
        if (file.exists()) {
            // Do auto Login()
            FileReader fr = null;
            try {
                fr = new FileReader(file);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
            try(BufferedReader br = new BufferedReader(fr)){
                userData = dataBase.getUserData(Integer.parseInt(br.readLine()));
                openInNewCMD("com.TextIt.UI.LoginPage");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }


    // During First Time Login
    public void manualLogin(int user_id) {
        try {
            File file = new File("last_session.txt");
            if(file.createNewFile()){
                BufferedWriter bw = new BufferedWriter(new FileWriter("last_session.txt"));
                bw.write(user_id);
            }else{
                System.out.println("File already exists");
            }
        }catch (IOException e){
            System.out.println("Error writing to file(last_session.txt) " + e.getMessage());
        }

    }
}
