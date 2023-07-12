package org.openlogix;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class BookHandler implements HttpHandler {

    Gson gson;
    String response;
    private Object exchange;

    private String getBooks(BookInfo db) {
        HashMap<String, String> json = new HashMap<String, String>();
        for (Book book : db.searchBooks("Harry Potter")) {
            json.put("name", book.name);
            json.put("id", String.valueOf(book.id));
            json.put("publisherName", book.publisherName);
            json.put("authorName", book.authorName);
        }
        return gson.toJson(json);
    }

    public void handle(HttpExchange exchange) throws IOException {
        System.out.println(exchange.getRequestMethod() + exchange.getRequestURI());
        gson = new Gson();

        BookInfo db = new BookInfo();
        // String response;
        if (exchange.getRequestMethod().equals("GET")) {
            response = getBooks(db);
        } else if (exchange.getRequestMethod().equals("POST")) {
            String input = IOUtils.toString(exchange.getRequestBody(), StandardCharsets.UTF_8);

            // response = data;
            Book payload = this.gson.fromJson(input, Book.class);
            Book book = new Book(payload.name, payload.publisherName, payload.authorName);

        } else {
            response = "";
        }

        exchange.sendResponseHeaders(200, response.getBytes().length);
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}