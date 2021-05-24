package tom.wehner.advertisementWebapp;

public class SearchRequest {

    String term;
    String town;

    public SearchRequest(String city) {
        town = city;
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

}
