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

    public boolean autoLogin(){
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
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        return false;
    }


    // During First Time Login
    public void manualLogin(int user_id) {
        try {
            File file = new File("last_session.txt");
            if(file.createNewFile()){
                BufferedWriter bw = new BufferedWriter(new FileWriter("last_session.txt"));
                bw.write(String.valueOf(user_id));
                bw.flush();
                bw.close();
            }else{
                System.out.println("File already exists");
            }
        }catch (IOException e){
            System.out.println("Error writing to file(last_session.txt) " + e.getMessage());
        }

    }
}
