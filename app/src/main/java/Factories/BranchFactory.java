package Factories;

import java.util.Map;

import Domain.Branch;

/**
 * Created by AbelN on 07/05/2016.
 */
public class BranchFactory {

    public static Branch createBranch(Map<String,String> value, long tel)
    {
        return new Branch.Builder().telephoneNumber(tel).manager(value.get("manager")).address(value.get("address")).build();
    }
}
