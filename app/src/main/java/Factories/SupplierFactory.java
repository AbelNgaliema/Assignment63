package Factories;

import java.util.Map;

import Domain.Supplier;

/**
 * Created by AbelN on 07/05/2016.
 */
public class SupplierFactory {
    public static Supplier createSupplier(Map<String,String> value, long tel)
    {
        return new Supplier.Builder().name(value.get("name")).registrationNumber(value.get("regNumber")).address(value.get("address")).telephoneNumber(tel).build();
    }
}



