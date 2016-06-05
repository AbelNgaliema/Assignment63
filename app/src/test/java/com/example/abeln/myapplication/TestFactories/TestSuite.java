package com.example.abeln.myapplication.TestFactories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by AbelN on 17/04/2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAuthorFactory.class,
        TestBookFactory.class,
        TestBuyFactory.class,
        TestCustomerAddressFactory.class,
        TestCustomerFactory.class,
        TestPersonalInformationFactory.class,
        TestPublisherFactory.class,
        TestEmployeeFactory.class,
        TestBranchFactory.class,
        TestSupplierFactory.class

})
public class TestSuite {
}
