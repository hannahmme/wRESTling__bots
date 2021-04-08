package Objects;

import java.util.ArrayList;

public class Chatbots {

    private static ArrayList<Chatbot> chatbots = new ArrayList<>();

    public void initialize() {
        Chatbot adminBot = new Chatbot("Admin", "Admin");
        Chatbot helperbot1 = new Chatbot("Caroline", "helper");
        Chatbot helperbot2 = new Chatbot("Hannah", "helper");
        Chatbot helperbot3 = new Chatbot("Amalie", "helper");
    }

    public static ArrayList<Chatbot> getChatbots() {
        return chatbots;
    }

    public static void addBot(Chatbot bot){
        chatbots.add(bot);
    }


}
