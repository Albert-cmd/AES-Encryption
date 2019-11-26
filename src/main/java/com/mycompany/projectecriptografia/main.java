/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectecriptografia;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import procesos.AES_Encryption;
import procesos.Hash_Generator_SHA256;
import procesos.RSA_Key_Generator;

/**
 *
 * @author Albert Gonzalez
 */
public class main {
    
    private static SecretKeySpec secretKey;
    private static byte[] key;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
         
        
        String ClearMessage =""; 
        
        System.out.println("GENEREM EL HASH SHA 256 " );
        System.out.println("------------------------");
        
        
        bytesToHex(ClearMessage); 
        
        
        System.out.println("");
        
        System.out.println("ENCRIPTEM A TRAVES D'AES");
        System.out.println("------------------------");
        
        
        System.out.println("GENEREM EL PARELL DE CLAUS RSA");
        System.out.println("------------------------------");
        
        
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
    
     
 
    public static void setKey(String myKey) 
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
 
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    
}
