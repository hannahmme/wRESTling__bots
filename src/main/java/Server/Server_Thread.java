package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Server_Thread extends Thread{

    private Socket socket;
    private ArrayList<Server_Thread> threads;
    private PrintWriter pw;


    public Server_Thread() {
        super("Server_Thread");
        this.socket = socket;
    }


    @Override
    public void run(){
        try {
            // reading input from the Client
            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            while(true){
                // saving the message from client in out
                String out = bf.readLine();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }





}
