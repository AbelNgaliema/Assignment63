package Domain;

import java.io.Serializable;

/**
 * Created by AbelN on 15/04/2016.
 */
public class PersonalInformation implements Contact, Person,Serializable {

    private String name;
    private String surname;
    private String idNumber;
    private String email;
    private int cellphone;
    private int telephone;
    public int getCellnumber() {
        return cellphone;
    }

    public String getEmailAddress() {
        return email;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getName() {
        return name;
    }

    public String getSutname() {
        return surname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public PersonalInformation(Builder builder)
    {
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.idNumber = builder.idNumber;
        this.telephone = builder.telephone;
        this.cellphone = builder.cellphone;
    }

    public static class Builder
    {
        String name;
        String surname;
        String idNumber; //Password Numbers sometimes contains letters, thus I made it string
        String email;
        int cellphone;
        int telephone;

        public Builder()
        {

        }

        public Builder idNumber (String value)
        {
            this.idNumber = value;
            return this;
        }

        public Builder name(String value)
        {
            this.name = value;
            return this;
        }

        public Builder surname(String value)
        {
            this.surname = value;
            return  this;
        }

        public Builder email(String value)
        {
            this.email = value;
            return  this;
        }
        public Builder cellphone (int value)
        {
            this.cellphone = value;
            return this;
        }
        public Builder telephone(int value)
        {
            this.telephone = value;
            return this;
        }

        public Builder copy(PersonalInformation personalInformation)
        {
            this.idNumber = personalInformation.getIdNumber();
            this.name = personalInformation.getName();
            this.surname = personalInformation.getSutname();
            this.email = personalInformation.getEmailAddress();
            this.telephone = personalInformation.getTelephone();
            this.cellphone = personalInformation.getCellnumber();

            return this;
        }

        public PersonalInformation build()
        {
            return new PersonalInformation(this);
        }


    }
}

