/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
public class IntegrityLock
{
  public static void main(String[] args) 
  {
    String passwordToHash = "password";
    String generatedPassword = null;
    Connection conn = null;
    Statement stmt = null;
    try 
    {
      // Create MessageDigest instance for MD5
      MessageDigest md = MessageDigest.getInstance("MD5");
      // Add password bytes to digest
      md.update(passwordToHash.getBytes());
      // Get the hash's bytes
      byte[] bytes = md.digest();
      // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      // Get complete hashed password in hex format
      generatedPassword = sb.toString();
      Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.cj.jdbc.Driver
      conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/cyberdb", "root", "pkyt");
      System.out.println("Connection is created successfully:");
      stmt = (Statement) conn.createStatement();
      String query1 = "INSERT INTO table1 " + "VALUES ('"+generatedPassword+"')";
      stmt.executeUpdate(query1);
	  System.out.println("Encyrpted pwd stored successfully:");
	  
    } catch (Exception e) {
		System.out.println("Error!");
      e.printStackTrace();
    }
    System.out.println(generatedPassword);
  }
}
