package Domain;

import java.io.Serializable;

/**
 * Created by AbelN on 15/04/2016.
 */
public class Buy implements  BuyInterface,Serializable {


    private String cashier;
    private String mode;
    private String book;
    private String customer;

    private Long id;


    public String getCashier() {
        return cashier;
    }

    public String getMode() {
        return mode;
    }

    public String getBook() {
        return  book;
    }

    public String getCustomer() {
        return customer;
    }


    public Long getId() {
        return id;
    }


    public Buy(Builder builder)
    {
        this.cashier = builder.cashier;
        this.mode = builder.mode;
        this.book = builder.book;
        this.customer = builder.customer;
        this.id = builder.id;
    }

    public static class Builder
    {

        String cashier;
        String mode;
        String book;
        Long id;

        String customer;

        public Builder ()
        {


        }


        public Builder id(Long value)

        {  this.id = value;
            return this;
        }

        public Builder mode(String value)
        {
            this.mode= value;
            return this;
        }

        public Builder book(String value)
        {
            this.book = value;
            return this;
        }

        public Builder customer(String customer)
        {
            this.customer = customer;
            return this;
        }

        public Builder cashier(String value)
        {
            this.cashier = value;
            return this;
        }

        public Builder copy(Buy buy)
        {
            this.cashier = buy.getCashier();
            this.mode = buy.getMode();
            this.book = buy.getBook();
            this.customer = buy.getCustomer();
            this.id = buy.getId();
            return this;
        }

        public Buy build()
        {
            return  new Buy(this);
        }
    }
}

