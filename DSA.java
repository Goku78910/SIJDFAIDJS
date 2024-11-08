/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis3;
import java.security.*;
import java.util.Base64;
import java.io.*;

public class DSA {
    public static void main(String[] args) throws Exception {
        // Generate the DSA key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(1024, new SecureRandom());
        KeyPair dsaKeyPair = kpg.generateKeyPair();

        // Extract the public and private keys
        PrivateKey privateKey = dsaKeyPair.getPrivate();
        PublicKey publicKey = dsaKeyPair.getPublic();

        // Print the private key in OpenSSH format
        String privateKeyStr = "-----BEGIN DSA PRIVATE KEY-----\n" +
            Base64.getEncoder().encodeToString(privateKey.getEncoded()) + "\n" +
            "-----END DSA PRIVATE KEY-----";
        System.out.println("Private Key:");
        System.out.println(privateKeyStr);

        // Print the public key in OpenSSH format
        String publicKeyStr = "ssh-dss " + Base64.getEncoder().encodeToString(publicKey.getEncoded()) + " generated-by-java";
        System.out.println("Public Key:");
        System.out.println(publicKeyStr);
    }
}


