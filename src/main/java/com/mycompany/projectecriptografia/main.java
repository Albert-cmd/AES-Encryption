/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectecriptografia;

import java.security.NoSuchAlgorithmException;
import procesos.AES_Encryption;
import procesos.Hash_Generator_SHA256;
import procesos.RSA_Key_Generator;

/**
 *
 * @author Albert Gonzalez
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        Hash_Generator_SHA256 GeneradorHash = null; 
        RSA_Key_Generator GeneradorClausRSA; 
        AES_Encryption EncriptadorAES; 
        
        String ClearMessage =""; 
        
        System.out.println("GENEREM EL HASH SHA 256 " );
        System.out.println("------------------------");
        GeneradorHash.bytesToHex(GeneradorHash.messageToBytes(ClearMessage));
        System.out.println("");
        
        System.out.println("ENCRIPTEM A TRAVES D'AES");
        System.out.println("------------------------");
        
        
        System.out.println("GENEREM EL PARELL DE CLAUS RSA");
        System.out.println("------------------------------");
        
        
    }
    
}
