/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Albert Gonzalez
 */
public class Hash_Generator_SHA256 {
    

    
    
   
    
    public  String bytesToHex(String missatge) throws NoSuchAlgorithmException {
        
     MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] encodedhash = digest.digest(
    missatge.getBytes(StandardCharsets.UTF_8));
        
    StringBuffer hexString = new StringBuffer();
    
    for (int i = 0; i < encodedhash.length; i++) {
    String hex = Integer.toHexString(0xff & encodedhash[i]);
    if(hex.length() == 1) hexString.append('0');
        hexString.append(hex);
        
    }
    
    return hexString.toString();
}
    
    
    
    
    
    
    
}
