/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 *
 * @author Albert Gonzalez
 */
public class RSA_Key_Generator {
    
  

public class RSAKeyPairGen {
    
    public  void GenerateRSAKeys (){
        try {
            
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024); // You can set a different value here
            
            KeyPair kp = kpg.generateKeyPair();
            PrivateKey prk = kp.getPrivate();
            PublicKey puk = kp.getPublic();
            
            Base64.Encoder enc = Base64.getEncoder();
            
            System.out.println();
            System.out.println("---- ( RSA Key Pair Generator ) ----");
            System.out.println("Private Key: " + enc.encodeToString(prk.getEncoded()));
            System.out.println();
            System.out.println("Public Key: " + enc.encodeToString(puk.getEncoded()));
            System.out.println();
            System.out.println("--- End ---");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
}
