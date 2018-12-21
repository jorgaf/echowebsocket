package ec.edu.utpl.arqapl.websocketdemo.handlers;

import ec.edu.utpl.arqapl.websocketdemo.MainWSocketSurvey;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class SurveyWebSocketHandler {
    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
        String username = "User" + MainWSocketSurvey.userNumber.incrementAndGet();
        MainWSocketSurvey.userUsernameMap.put(session, username);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        String username = MainWSocketSurvey.userUsernameMap.get(session);
        MainWSocketSurvey.userUsernameMap.remove(session);
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        MainWSocketSurvey.broadcastMessage(MainWSocketSurvey.userUsernameMap.get(user), message);
    }

}
