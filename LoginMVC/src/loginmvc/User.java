/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmvc;

import java.security.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author THIEUHV
 */
public class User {
   String strUsername;
   String strHashPassword;

    public User(String strUsername, String strPassword) throws  NoSuchAlgorithmException {
        this.strUsername = strUsername;
        this.strHashPassword = MD5.GetMD5(strPassword);
    }
    
    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrHashPassword() {
        return strHashPassword;
    }

    public void setStrHashPassword(String strHashPassword) {
        this.strHashPassword = strHashPassword;
    }
    
    
    
    public boolean ValidateUser()
    {
        
       String server = "DESKTOP-3PADRBM";
       String user = "sa";
       String password = "123456";
       String db = "MYUSER";
        int port = 1433;
         
       SQLServerDataSource SQLServerDataSource = new SQLServerDataSource();
       SQLServerDataSource.setUser(user);
       SQLServerDataSource.setPassword(password);
       SQLServerDataSource.setPortNumber(port);
       SQLServerDataSource.setServerName(server);
       SQLServerDataSource.setDatabaseName(db);
       SQLServerDataSource.setIntegratedSecurity(false);
      
        try(Connection conn = SQLServerDataSource.getConnection();) 
        {
            String strQuery = "SELECT * FROM UserDB WHERE Username=? AND Password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(strQuery);
            preparedStatement.setString(1  , strUsername);
            preparedStatement.setString(2  , strHashPassword);            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) 
            {
               conn.close();
               return true;
            }
            else
            {
               conn.close();
               return false;
            }             
        } 
        catch (Exception e) 
        {          
            e.printStackTrace();
            return false;
        }      
    }
    
}
