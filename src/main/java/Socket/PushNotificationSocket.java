package Socket;

import Objects.Message;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

// the WebSocket is listening to this URI. This is the Endpoint-code
@ServerEndpoint("/pushnotification/")
public class PushNotificationSocket {

    private Session session;
    private static final List<Message> messages = Collections.synchronizedList(new LinkedList<Message>());

    // a list over all connected clients/ sessions
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    public static void broadcast(Message message) throws IOException, EncodeException {
        // a message is created and sent to all endpoints (everyone in that room)

    }


    @OnOpen
    public void onOpen(Session session, String userID) throws IOException, EncodeException {
        // get session and websocket connection. UserID is connected to a spesific session

        sessions.add(session);
        for (Message msg : messages) {
            session.getBasicRemote().sendObject(msg);
        }

        /*
        this.session = session;
        Message message = new Message();
        message.setUserID(userID);
        message.setMessage("Connected to push notifications");
        broadcast(message);*/


    }


    //receives the information from the Websocket when a message is sent to Endpoint
    @OnMessage
    public void onMessage(Session session, String msg) {

        for (Session openSession : sessions) {
            try {
                openSession.getBasicRemote().sendObject(msg);
            } catch (IOException | EncodeException e) {
                sessions.remove(openSession);
            }
        }


        /*
        // handles new messages
        System.out.println("Message from " + session.getId() + ": " + msg);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    session.getBasicRemote().sendText("PushNotification");
                } catch (IOException ex) {
                    Logger.getLogger(PushNotificationSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 5 * 1000);*/
    }


    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes. AKA when a user logs out
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

}
