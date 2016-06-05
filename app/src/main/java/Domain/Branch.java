package Domain;

import java.io.Serializable;

/**
 * Created by AbelN on 07/05/2016.
 */
public class Branch implements Serializable {

    private String address;

    public long getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getManager() {
        return manager;
    }

    public Long getId() {
        return id;
    }

    private long telephoneNumber;
    private String manager;

    private Long id;


    public Branch(Builder builder)
    {
      this.manager = builder.manager;
      this.address = builder.address;
      this.telephoneNumber = builder.telephoneNumber;
      this.id = builder.id;
    }

    public static class Builder
    {

        long telephoneNumber;
        String address;
        String manager;
        Long id;



        public Builder ()
        {


        }


        public Builder id(Long value)

        {
            this.id = value;
            return this;
        }

        public Builder telephoneNumber(long value)
        {
            this.telephoneNumber= value;
            return this;
        }

        public Builder manager(String value)
        {
            this.manager = value;
            return this;
        }

        public Builder address(String value)
        {
            this.address = value;
            return this;
        }


        public Builder copy(Branch branch)
        {
            this.manager = branch.getManager();
            this.address = branch.getAddress();
            this.telephoneNumber= branch.getTelephoneNumber();
            this.id = branch.getId();
            return this;
        }

        public Branch build()
        {
            return  new Branch(this);
        }
    }
}
