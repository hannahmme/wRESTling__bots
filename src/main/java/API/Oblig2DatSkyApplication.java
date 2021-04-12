package API;

import Objects.Chatbots;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Oblig2DatSkyApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Oblig2DatSkyApplication.class, args);

        // initializing Bot in java
        Chatbots.initialize();

        // making a new chatroom every 1minute in 5 minutes
        for (int i = 0; i <= 5; i++) {
            int finalI = i;
            new java.util.Timer().schedule(

                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Chatbots.generateChatroom(finalI);
                        }
                    },
                    10000
            );
            TimeUnit.MINUTES.sleep(1);
        }

        while (true) {
            Chatbots.checkIllegalRooms();
        }
    }
}