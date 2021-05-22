package tom.wehner.advertisementWebapp;

public class SearchRequest {

    String term;
    String town = "berlin";

    public SearchRequest() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
