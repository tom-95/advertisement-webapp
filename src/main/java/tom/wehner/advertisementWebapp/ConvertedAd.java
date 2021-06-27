package tom.wehner.advertisementWebapp;

import java.sql.Date;
import java.util.Base64;

public class ConvertedAd {

    private Long id;

    private String title;

    private String description;

    private int price;

    private String town;

    private Date date;

    private String eMail;

    private String image;

    public ConvertedAd(Ad ad) {

        this.date = ad.getDate();
        this.title = ad.getTitle();
        this.description = ad.getDescription();
        this.price = ad.getPrice();
        this.town = ad.getTown();
        this.eMail = ad.getEMail();
        this.image = Base64.getEncoder().encodeToString(ad.getImage());

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getTown() {
        return town;
    }

    public Date getDate() {
        return date;
    }

    public String geteMail() {
        return eMail;
    }

    public String getImage() {
        return image;
    }
}
