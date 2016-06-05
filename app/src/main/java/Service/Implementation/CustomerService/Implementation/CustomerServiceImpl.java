package Service.Implementation.CustomerService.Implementation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Set;

import Config.GlobalContext;

import Domain.Buy;
import Repository.BranchRepository.BranchRepository;
import Repository.BranchRepository.Implementation.BranchRepositoryImpl;
import Repository.BuyRepository.BuyRepository;
import Repository.BuyRepository.Implementation.BuyRepositoryImpl;
import Service.Implementation.CustomerService.CustomerService;

/**
 * Created by AbelN on 09/05/2016.
 */
public class CustomerServiceImpl extends Service implements CustomerService {


    @Nullable
    BranchRepository repoBranch;
    BuyRepository repoBuy;
    private static CustomerServiceImpl service = null;

    public CustomerServiceImpl()
    {
        repoBranch = new BranchRepositoryImpl(GlobalContext.getAppContext());
        repoBuy = new BuyRepositoryImpl(GlobalContext.getAppContext());

    }
    private final IBinder localBinder = new CustomerServiceLocalBinder();

    public static CustomerServiceImpl getInstance() {
        if (service == null)
            service = new CustomerServiceImpl();
        return service;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class CustomerServiceLocalBinder extends Binder {
        public CustomerServiceImpl getService() {
            return CustomerServiceImpl.this;
        }
    }
    @Override
    public boolean crossValidation(String name) {
        //The record will not deleted. Because it is being used in the other
        //table
        Set<Buy> buys;
        buys =   repoBuy.findAll();

        for (Buy  buy : buys)
        {
            if (buy.getCustomer().trim().equalsIgnoreCase(name))
                return true;
        }
        return false;

    }

}
