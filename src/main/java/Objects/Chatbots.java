package Objects;

import java.util.ArrayList;

public class Chatbots {

    private static ArrayList<Chatbot> chatbots = new ArrayList<>();

    public void initialize() {
        Chatbot moderatorBot = new Chatbot("Moderator");
        Chatbot helperbot = new Chatbot("Caroline");
        Chatbot neutralbot = new Chatbot("Hannah");
        Chatbot negativebot = new Chatbot("Amalie");

        chatbots.add(moderatorBot);
        chatbots.add(helperbot);
        chatbots.add(neutralbot);
        chatbots.add(negativebot);
    }

    public static ArrayList<Chatbot> getChatbots() {
        return chatbots;
    }

    public static void addBot(Chatbot bot){
        chatbots.add(bot);
    }


}
