package tom.wehner.advertisementWebapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Ad {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String town;

    @Column(name = "Datum", nullable = false)
    private Date date;

    @Column(nullable = false)
    private String eMail;
    //Image image;

    public Ad() {}

    public Ad(String title, String description, String town, String eMail) {

        date = Date.valueOf(LocalDate.now());
        this.title = title;
        this.description = description;
        this.town = town;
        this.eMail = eMail;

    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getTown() { return town; }

    public Date getDate() { return date; }

    public String getEMail() { return eMail; }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setDate(Date date) {this.date = date;}

    public void setEMail(String contact) {
        this.eMail = contact;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
