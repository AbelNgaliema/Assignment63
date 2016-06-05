package Factories;

import java.util.Map;

import Domain.CustomerAddress;

/**
 * Created by AbelN on 15/04/2016.
 */
public class CustomerAddressFactory {

    public static CustomerAddress createCustomerAddress(Map<String,String> value, int postalCode)
    {
        return  new CustomerAddress.Builder().address(value.get("address")).city(value.get("city")).postalCode(postalCode).build();
    }
}

