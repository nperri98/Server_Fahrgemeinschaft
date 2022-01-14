import Login.Login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
     try {


         Login test = new Login();
         System.out.println(test.login("usertest", "test123"));
         String text = Login.sha256("test123");
         //System.out.println(text);
     }catch (Exception e){
         e.printStackTrace();
     }
     }

}
