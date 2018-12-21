package ec.edu.utpl.arqapl.websocketdemo;

import ec.edu.utpl.arqapl.websocketdemo.handlers.SurveyWebSocketHandler;
import org.eclipse.jetty.websocket.api.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static spark.Spark.*;

public class MainWSocketSurvey {
    public static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    public static AtomicInteger userNumber = new AtomicInteger();

    public static void main(String[] args) {

        webSocket("/survey", SurveyWebSocketHandler.class);
        init();
    }

    public static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(getTest());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static String getTest() {
        return "{\n" +
                "  \"name\" : \"My first test\",\n" +
                "  \"questions\" : [\n" +
                "    {\n" +
                "      \"id\" : 1,\n" +
                "      \"text\": \"¿Cuál es la capital de Ecuador?\",\n" +
                "      \"options\" :[\n" +
                "        {\n" +
                "          \"id\":1,\n" +
                "          \"text\" : \"Loja\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\":2,\n" +
                "          \"text\" : \"Quito\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\":3,\n" +
                "          \"text\" : \"Nueva Loja\"\n" +
                "         }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\" : 2,\n" +
                "      \"text\": \"¿Cuál es la capital de Perú?\",\n" +
                "      \"options\" :[\n" +
                "        {\n" +
                "          \"id\":1,\n" +
                "          \"text\" : \"Lima\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\":2,\n" +
                "          \"text\" : \"Chiclayo\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\":3,\n" +
                "          \"text\" : \"El Cusco\"\n" +
                "         }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
