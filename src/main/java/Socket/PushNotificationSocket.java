package Socket;

import java.io.IOException;
import java.util.*;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

// the WebSocket is listening to this URI. This is the Endpoint-code
@ServerEndpoint("/pushnotification")
public class PushNotificationSocket {

    public static String userID;
    private static Session session;

    // a list over all connected clients/ sessions (all have pressed the button)
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    // code for broadcasting to all other members of rooms if not in room
    public void broadcast(String message) throws IOException {
        // a message is created and sent to all endpoints (everyone in that room)
        for (Session s : sessions){
            s.getBasicRemote().sendText(message);
        }
    }


    @OnOpen
    public void onOpen(Session session) {
        // get session and websocket connection. UserID is connected to a spesific session
        this.session = session;
        sessions.add(session);

        System.out.println("I onOpen");

    }


    //receives the information from the Websocket when a message is sent to Endpoint
    @OnMessage
    public void onMessage(String msg) throws IOException {
        // sending incoming message to all clients connected to room
        broadcast(msg);
        System.out.println("I onMessage");
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
