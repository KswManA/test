/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmvc;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author THIEUHV
 */
public class MD5 
{
   public static String GetMD5(String strInput)
   {
        try 
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(strInput.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String strMD5 = no.toString(16);
            
            while (strMD5.length() < 32) 
            {
                strMD5 = "0" + strMD5;
            }            
            return strMD5;
        } 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);           
        }
        
   }
}
