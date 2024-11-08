/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aos1;
import java.net.*; 
import java.io.*; 
/**
 *
 * @author Maaz Shaikh
 */
public class Aos1client
{
public static void main(String ar[])
{
InputStream i=null; 
BufferedReader b=null; 
Socket s=null;
try
{ 
    s=new Socket("127.0.0.1",6017); 
    i=s.getInputStream();
    b=new BufferedReader(new InputStreamReader(i)); 
    String l; 
    while((l=b.readLine())!=null) 
        System.out.println(l);
}
catch(IOException e)
{
System.out.println(e);
}

finally
{
try
{
s.close();
}


catch(Exception e)
{
System.out.println(e.getMessage());
} 
}}}
