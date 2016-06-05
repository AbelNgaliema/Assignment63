package Domain;

import java.io.Serializable;

/**
 * Created by AbelN on 15/04/2016.
 */
public class Customer implements CustomerInterface,Serializable {


    private CustomerAddress customerAddress;
    private PersonalInformation personalInformation;

    private Long id;
    public Long getId(){ return id;}


    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public Customer(Builder builder)
    {
        this.id = builder.id;
        this.customerAddress = builder.customerAddress;
        this.personalInformation = builder.personalInformation;
    }

    public static class Builder
    {
        CustomerAddress customerAddress;
        PersonalInformation personalInformation;
        Long id;


        public Builder ()
        {

        }


        public Builder CustomerAddress(CustomerAddress customerAddress)
        {
            this.customerAddress = customerAddress;
            return this;
        }


        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder PersonalInformation(PersonalInformation personalInformation)
        {
            this.personalInformation = personalInformation;
            return this;
        }


        public Builder copy(Customer customer)
        {

            this.id = customer.getId();
            this.personalInformation = customer.getPersonalInformation();
            this.customerAddress = customer.getCustomerAddress();
            return this;
        }

        public Customer build()
        {
            return new Customer(this);
        }
    }



}

