package org.openlogix;

public class Book {
    Integer id;
    String name;
    String publisherName;
    String authorName;

    Book(String name, String publisherName, String authorName) {
        this.name = name;
        this.publisherName = publisherName;
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Book Name - " + this.name + "\nPublisher Name - " + this.publisherName + "\nAuthor Name - "
                + this.authorName + "\n";
    }
}