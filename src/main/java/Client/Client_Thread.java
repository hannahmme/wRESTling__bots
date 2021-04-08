package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client_Thread extends Thread{

    Socket sock = new Socket("localhost", 8080);
    private DataInputStream console   = null;
    private DataOutputStream streamOut = null;



    public Client_Thread() throws IOException {


    }


    @Override
    public void run(){
    }



}
