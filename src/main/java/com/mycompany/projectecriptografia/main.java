/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectecriptografia;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import procesos.AES_Encryption;
import procesos.Hash_Generator_SHA256;
import procesos.RSA_Key_Generator;

/**
 *
 * @author Albert Gonzalez
 */
public class main {
    
     static String PRkey=null; 
     static String PUkey=null; 
    private static SecretKeySpec secretKey;
    private static byte[] key;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
        // TODO code application logic here
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        
        String ClearMessage ="TEXTO DE PRUEBA"; 
        
        System.out.println("GENEREM EL HASH SHA 256 " );
        System.out.println("------------------------");
        
        
         String SHA = bytesToHex(ClearMessage); 
        
        System.out.println("GENEREM EL PARELL DE CLAUS RSA");
        System.out.println("------------------------------");
        
        
        GenerateRSAKeys(); 
        
         
        
        // SIGNEM EL SHA AMB RSA 
        
         String input = "sample input";
         
        // Not a real private key! Replace with your private key!
        
        //String strPk = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIi0oLIqCxBcg5HkWeBCzK/eRHp8oXbqs1sh4hRZPZzSIwGg/Hb2h60ZyBqYrxnCCSPbbP9cDu2cziWMiPb5tTFwYOzkaW49GVxbjyhGEj/sOkSsXpQh3eLrg/qUSsXF7uhlfk1FjEPftZqx3HgEUP+vvj80ULVKF9cZA1h1hxD3AgMBAAECgYADnfPagu5EAo6gn5AZNOtBg/n/26hSAye3s4/NxgaxTYYLlvzXEPLKC7KVsZCAd/fR8Q5TO1ZRxyJVMAC3uCepG063i7J1rIO4Jr2BLrrFDZge8wrW4ML+I9CKcmBLBBbtlqJGSIVaesECPyalAYOYTwgH93xDiXg3Qkmlzz2UgQJBAMCqtLj365S+vGC3oerdKoDA+Pqs9coTWnNcN1UJo3Gtj/f663LaD5UvKjGsJGBTvGe/cmKk4tc7Qc35sOXfIxcCQQC1pKwmbEnSX0A+gn+HzOKUV9mMOcAQDzK4uByULurC/XsKBvAGt6qtz9Bws/6UQFDFb3I+AGn2SzPHghxbe60hAkEAhe9luNwoOfwqSnX9qp7bKCx+KQ1JxJOVonJtyMDtjV4hojsdqHbstEHYbDWGCCzN4bWWF/sV8pewFDdLAVIQOwJAXwy6oKiNwK3jOlKjVXabIjEOP9iAbmbljc+Z9NjQNBTca5TRyVIW2Dkkw7UMhJtbKNV2nIcu65jSkZW+LQj8wQJBAISZSOgnIS0GX8CqM4aeVwwbTTtSF4mmCl/2vTJPGkKa3UDwsIanuzG9WgK1X7Og/YhpKixrYC6Fk+wlIaufu34=";
         
        String base64Signature = signSHA256RSA(SHA,PRkey);
        
        
        System.out.println(" SHA256 Signat amb la Private Key: "+base64Signature);
        
        
        
        
  
        
        
    }
    
    
    
    
    
     public static void setKey(String myKey) throws NoSuchAlgorithmException 
    {
        
       
        
        
        MessageDigest sha = null;
        
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
       
    public  static String bytesToHex(String missatge) throws NoSuchAlgorithmException {
        
     MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] encodedhash = digest.digest(
    missatge.getBytes(StandardCharsets.UTF_8));
        
    StringBuffer hexString = new StringBuffer();
    
    for (int i = 0; i < encodedhash.length; i++) {
    String hex = Integer.toHexString(0xff & encodedhash[i]);
    if(hex.length() == 1) hexString.append('0');
        hexString.append(hex);
        
    }
    
    System.out.println(hexString.toString());
    return hexString.toString();
}
    
      
    public static  void GenerateRSAKeys (){
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
             PRkey = enc.encodeToString(prk.getEncoded()); 
            System.out.println();
            System.out.println("Public Key: " + enc.encodeToString(puk.getEncoded()));
             PUkey = enc.encodeToString(puk.getEncoded()); 
            System.out.println();
            System.out.println("--- End ---");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     


    
    // SIGNAR EL SHA AMB RSA 
    
  
 
    
    // Create base64 encoded signature using SHA256/RSA.
    private static String signSHA256RSA(String input, String strPk) throws Exception {
        // Remove markers and new line characters in private key
        String realPK = strPk.replaceAll("-----END PRIVATE KEY-----", "")
                             .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                             .replaceAll("\n", "");
 
        byte[] b1 = Base64.getDecoder().decode(realPK);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance("RSA");
 
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(kf.generatePrivate(spec));
        privateSignature.update(input.getBytes("UTF-8"));
        byte[] s = privateSignature.sign();
        return Base64.getEncoder().encodeToString(s);
        
    }
    
    
    
    
}
