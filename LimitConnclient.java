/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aos4;

import java.net.*;
import java.io.*;
class Aos4client {
public static void main(String args[]){
try {
Socket s=new Socket("localhost",4040);
//socket input
InputStream in=s.getInputStream(); 
InputStreamReader isr=new InputStreamReader(in); 
BufferedReader br=new BufferedReader(isr);
//socket Output
OutputStream out=s.getOutputStream(); 
PrintWriter pw=new PrintWriter(out,true);
//input
InputStreamReader isr1=new InputStreamReader(System.in);
BufferedReader br1=new BufferedReader(isr1);
while(true)
{
    System.out.println("CAndroid/CIOS/CQUIT"); 
    String line=br1.readLine();
    if(line.equalsIgnoreCase("CAndroid"))
        System.out.println("Sejal");
    else if (line.equalsIgnoreCase("CIOS"))
        System.out.println("Anam");
    else if(line.equalsIgnoreCase("CQuit"))
        System.out.println("Let's Go Home");
    System.exit(0);
}}
catch(Exception e)
{
System.out.println(e);
}}}
