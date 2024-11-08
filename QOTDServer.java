/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aos1;
import java.net.*; 
import java.io.*;
import java.util.*;
/**
 *
 * @author Maaz Shaikh
 */
public class Aos1
{
public static void main(String args[])
{
try
{ 
String q=null;
Calendar c=Calendar.getInstance(); 
Date dt=new Date();
c.setTime(dt);
int dow=c.get(Calendar.DAY_OF_WEEK); 
switch(dow)
{
    case 1:
        q="TTS SUNDAY";
        break;

    case 2:
        q="TTS MONDAY";
        break;

    case 3:
        q="TTS TUESDAY";
        break;
    
    case 4:
        q="TTS WEDNESDAY";
        break; 
    
    case 5:
        q="TTS THURSDAY";
        break;
    
    case 6:
        q="TTS FRIDAY";
        break;
    
    case 7:
        q="TTS SATURDAY";
        break; 
    default:
        q="NO WEEK DAY";
        break;
}

ServerSocket sk=new ServerSocket(6017); 
while(true)
{
Socket cl=sk.accept(); 
PrintWriter p=new
PrintWriter(cl.getOutputStream(),true); 
p.println("quote of the day service");
if(q.length()<=512) 
    p.println(q); 
else
    p.println("more than 512 char");
cl.close();
}
}
catch(IOException e)

{ 
    System.out.println(e);
}
}}
