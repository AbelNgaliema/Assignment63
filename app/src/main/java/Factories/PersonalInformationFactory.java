package Factories;

import java.util.Map;

import Domain.PersonalInformation;

/**
 * Created by AbelN on 15/04/2016.
 */
public class PersonalInformationFactory {

    public static PersonalInformation createPersonalInformation (Map<String, String> value, int cellphone, int telephone)
    {
        return  new PersonalInformation.Builder().idNumber(value.get("idNumber")).name(value.get("name")).surname(value.get("surname")).
                email(value.get("email")).cellphone(cellphone).telephone(telephone).build();
    }
}
