package aos4;

import java.net.*;
import java.io.*;

class Aos4 {
    public static void main(String args[]) {
        try {
            // Create a server socket to listen on port 4040
            ServerSocket ss = new ServerSocket(4040, 50);

            // Array to hold client sockets
            Socket[] client = new Socket[10];

            // Accept clients in a loop
            for (int i = 0; i < client.length; i++) {
                // Accept connection from client
                client[i] = ss.accept();
                System.out.println("Client " + i + ": " + client[i]);

                // Set up input stream for the client
                InputStream in = client[i].getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader br = new BufferedReader(isr);

                // Set up output stream for the client
                OutputStream out = client[i].getOutputStream();
                PrintWriter pw = new PrintWriter(out, true);

                // Set up input stream for server console
                InputStreamReader isr1 = new InputStreamReader(System.in);
                BufferedReader br1 = new BufferedReader(isr1);

                // Server interaction with the client
                while (true) {
                    System.out.println("Enter SAndroid / SIOS / SQuit:");

                    String line = br1.readLine();

                    if (line.equalsIgnoreCase("SAndroid")) {
                        pw.println("Sejal");
                    } else if (line.equalsIgnoreCase("SIOS")) {
                        pw.println("Anam");
                    } else if (line.equalsIgnoreCase("SQuit")) {
                        pw.println("Let's Go Home");
                        client[i].close();  // Close the client connection
                        System.exit(0);  // Exit the program
                    } else {
                        pw.println("Invalid input. Please try again.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);  // Print exception if something goes wrong
        }
    }
}
