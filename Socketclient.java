/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aos2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
public class Aos2client {
  private static Socket s;
  public static void main(String[] args) throws IOException {
    try {
      String h = "localhost";
      int p = 6102;
      InetAddress a;
      a = InetAddress.getByName(h);
      s = new Socket(a, p);
      OutputStream o = s.getOutputStream();
      OutputStreamWriter os = new OutputStreamWriter(o);
      BufferedWriter b = new BufferedWriter(os);
      String m = "computer sci 2016";
      String sm = m + "\n";
      b.write(sm);
      b.flush();
      System.out.println("msg sent to the server :" + sm);
      InputStream is = s.getInputStream();
      InputStreamReader ir = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(ir);
      String mg = br.readLine();
      System.out.println("Message received from msg: "+m);
      }
      catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          s.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
