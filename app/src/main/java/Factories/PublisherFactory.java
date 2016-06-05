package Factories;

import java.util.Map;

import Domain.Publisher;

/**
 * Created by AbelN on 15/04/2016.
 */
public class PublisherFactory {

    public static Publisher createPublisher(Map<String,String> value)
    {

        return new Publisher.Builder().city(value.get("city")).registration(value.get("registration")).name(value.get("name")).build();
    }

}
