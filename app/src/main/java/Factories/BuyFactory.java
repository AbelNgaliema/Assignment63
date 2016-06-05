package Factories;

import java.util.Map;

import Domain.Buy;

/**
 * Created by AbelN on 15/04/2016.
 */
public class BuyFactory {

    public static Buy createBuy(long id, Map<String,String> value, String book, String customer)
    {

        return new Buy.Builder().cashier(value.get("cashier")).mode(value.get("mode")).book(book).customer(customer).build();

    }
}
