package API;

import Objects.Chatbots;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oblig2DatSkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oblig2DatSkyApplication.class, args);

        Chatbots.initialize();

    }
}