package API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oblig2DatSkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oblig2DatSkyApplication.class, args);


        // kode som kanskje må inn for server?
        /*try {
            ServerSocket ss = new ServerSocket(8080);
            Socket s = ss.accept();     //establishes connection


            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String) dis.readUTF();
        }
        catch (Exception e){

        }*/


    }

}
