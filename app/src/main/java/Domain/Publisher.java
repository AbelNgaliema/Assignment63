package Domain;

import java.io.Serializable;

/**
 * Created by AbelN on 15/04/2016.
 */
public class Publisher implements PublisherInterface, Serializable {

    private String name;

    private String city;
    private String registrationNumber;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getRegistration() {
        return registrationNumber;
    }
    public Publisher(Builder builder){
        this.name = builder.name;
        this.city= builder.city;
        this.registrationNumber = builder.registrationNumber;
    }
    public static class Builder{
        String name;
        String city;
        String registrationNumber;


        public Builder(){


        }
        public Builder city(String value){
            this.city = value;
            return this;
        }

        public Builder registration(String value){
            this.registrationNumber = value;
            return this;
        }

        public Builder name(String value)
        {
            this.name = value;
            return this;
        }
        public Publisher build(){
            return new Publisher(this);
        }

        public Builder copy(Publisher value){
            this.name = value.name;
            this.city = value.city;
            this.registrationNumber = value.registrationNumber;
            return this;
        }

    }
}

