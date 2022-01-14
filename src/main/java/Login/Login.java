package Login;

import Database.DbConnector;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;


    public  class  Login {
        private static final String DB_USERNAME ="android";
        private static final String DB_PASSWORD ="admin";
        private static final String IP= "localhost";
        private static final String DB= "hausarbeit";


        public Integer login(String username, String password){
            DbConnector con = new DbConnector(IP,DB_USERNAME,DB_PASSWORD, DB);
            String ergPass="" ;
            try {
                String sqlString = "SELECT * FROM `user` WHERE `username`='" + username+"';";
                con.connect();

                ResultSet res = con.executeQuery(sqlString);

                 if(res.next()){
                    ergPass = res.getString("password");
                }

                con.close();
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(ergPass.equals(sha256(password))){
              return 1;
            }else {
                return 0;
            }
        }
        public static String sha256(final String base) {
            try{
                final MessageDigest digest = MessageDigest.getInstance("SHA-256");
                final byte[] hash = digest.digest(base.getBytes("UTF-8"));
                final StringBuilder hexString = new StringBuilder();
                for (int i = 0; i < hash.length; i++) {
                    final String hex = Integer.toHexString(0xff & hash[i]);
                    if(hex.length() == 1)
                        hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }

