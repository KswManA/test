/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmvc;

import java.security.NoSuchAlgorithmException;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author THIEUHV
 */
public class RegisterUser extends User{
    
    String strMail;
    String strGender;
    String strAdddress;

    public RegisterUser(String strMail, String strGender, String strAdddress, String strUsername, String strHashPassword) throws  NoSuchAlgorithmException {
        super(strUsername, strHashPassword);
        this.strMail = strMail;
        this.strGender = strGender;
        this.strAdddress = strAdddress;
    }

    public String getStrMail() {
        return strMail;
    }

    public void setStrMail(String strMail) {
        this.strMail = strMail;
    }

    public String getGender() {
        return strGender;
    }

    public void setGender(String Gender) {
        this.strGender = Gender;
    }

    public String getStrAdddress() {
        return strAdddress;
    }

    public void setStrAdddress(String strAdddress) {
        this.strAdddress = strAdddress;
    }

    
    public int AddToDb()
    {
       String server = "DESKTOP-3PADRBM";
       String user = "sa";
       String password = "123456";
       String db = "MyUser";
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
            String strQuery = "SELECT Username FROM UserDB WHERE Username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(strQuery);
            preparedStatement.setString(1  , strUsername);       
            ResultSet resultSet = preparedStatement.executeQuery();
            
            
            if (resultSet.next()) 
            {
                conn.close(); 
               return -1;
            }
            else
            {
                String strInsert = "INSERT INTO UserDB VALUES (?,?,?,?,?)";
                PreparedStatement preparedInsert = conn.prepareStatement(strInsert);
                preparedInsert.setString(1  , strUsername);
                preparedInsert.setString(2  , strHashPassword);     
                preparedInsert.setString(3, strGender);
                preparedInsert.setString(4, strMail);
                preparedInsert.setString(5, strAdddress);                   
                
                int nReturn = preparedInsert.executeUpdate();
                conn.close();
                
                return nReturn;
                
            }             
        } 
        catch (Exception e) 
        {          
           e.printStackTrace();
           return -1;
        }   
        
    }
    
}
