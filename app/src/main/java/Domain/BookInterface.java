package Domain;

/**
 * Created by AbelN on 15/04/2016.
 */
public interface BookInterface {

    int getYear();
    double getPrice();
    String getIsbnNumber();
    int getQuantity();
    String getTitle();
    Author getAuthor();
    Publisher getPublisher();
    Long getId();



}
