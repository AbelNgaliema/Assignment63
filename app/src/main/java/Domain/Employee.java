package Domain;

import java.io.Serializable;

/**
 * Created by AbelN on 07/05/2016.
 */
public class Employee implements Serializable {

    public String getName() {
        return name;
    }


    private String name;



    public String getSurname() {
        return surname;
    }

    private String surname;

    public String getPosition() {
        return position;
    }

    private String position;

    public double getSalary() {
        return salary;
    }

    private double salary;


    public String getPassword() {
        return password;
    }

    private String password;

    public String getSystemName() {
        return systemName;
    }

    private String systemName;


    public Long getId() {
        return id;
    }

    private Long id;



    public Employee(Builder builder)
    {
        this.name = builder.name;
        this.surname = builder.surname;
        this.position = builder.position;
        this.salary = builder.salary;
        this.password = builder.password;
        this.systemName = builder.systemName;
        this.id = builder.id;
    }

    public static class Builder
    {

        String name;
        String surname;
        String position;
        Long id;

        double salary;
        String password;
        String systemName;

        public Builder ()
        {


        }

        public Builder id(Long value)

        {
            this.id = value;
            return this;
        }

        public Builder name(String value)
        {
            this.name= value;
            return this;
        }
        public Builder surname(String value)
        {
            this.surname= value;
            return this;
        }


        public Builder position(String value)
        {
            this.position = value;
            return this;
        }
        public Builder password (String value)
        {
            this.password = value;
            return this;
        }

        public Builder systemName(String value)
        {
             this.systemName = value;
             return this;
        }
        public Builder salary(double value)
        {
            this.salary = value;
            return this;
        }

        public Builder copy(Employee employee)
        {
            this.name = employee.getName();
            this.surname = employee.getSurname();
            this.position = employee.getPosition();
            this.salary = employee.getSalary();
            this.password = employee.getPassword();
            this.systemName = employee.getSystemName();
            this.id = employee.getId();
            return this;
        }

        public Employee build()
        {
            return  new Employee(this);
        }
    }




}
