package ec.edu.utpl.arqapl.websocketdemo;

import ec.edu.utpl.arqapl.websocketdemo.handlers.EchoWebSocketHandler;

import static spark.Spark.*;

public class MainWSocket {
    public static void main(String[] args) {
        webSocket("/echo", EchoWebSocketHandler.class);
        init();
    }
}
