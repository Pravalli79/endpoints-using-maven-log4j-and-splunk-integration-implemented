package org.openlogix;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Server {
    private int port;

    Server(int port) throws IOException {
        this.port = port;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/health", new HealthHandler());
        server.createContext("/books", new BookHandler());
        server.start();
        System.out.println("App is running" + this.port);
    }

}
