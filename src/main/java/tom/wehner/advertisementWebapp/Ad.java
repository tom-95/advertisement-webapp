package tom.wehner.advertisementWebapp;

import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

public class Ad {

    String id;
    String title;
    String description;
    String town;
    LocalDate date;
    String contact;
    Image image;

    public Ad(String title, String description, String town, String contact) {

        id = UUID.randomUUID().toString();
        date = LocalDate.now();
        this.title = title;
        this.description = description;
        this.town = town;
        this.contact = contact;

    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public Image getImage() { return image; }

    public String getId() { return id; }

    public String getTown() { return town; }

    public LocalDate getDate() { return date; }

    public String getContact() { return contact; }
}
