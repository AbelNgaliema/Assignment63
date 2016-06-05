package com.example.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import Config.GlobalContext;
import Service.Implementation.EmployeeService.Implementation.EmployeeServiceImpl;

public class MainActivity extends Activity {

    private static final String TAG="EMPLOYEE TEST1";
    private Long id;
    private EmployeeServiceImpl employeeService;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            EmployeeServiceImpl.EmployeeServiceLocalBinder binder
                    = (EmployeeServiceImpl.EmployeeServiceLocalBinder) service;
            employeeService = binder.getService();
            isBound = true;
        }
        //
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    @Override
    protected void onStart()
    {
        super.onStart();
        Intent intent = new Intent(this, EmployeeServiceImpl.class);


        GlobalContext.context = this;
        employeeService = EmployeeServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }


    protected void checkForPassword(View view)
    {

     // //  EditText username = (EditText)findViewById(R.id.name);
     //   EditText password = (EditText)findViewById(R.id.password);
     //   boolean result = employeeService.passwordValidation(password.getText().toString());

    //    if (result)
      //  {
            Toast.makeText(MainActivity.this, "WELCOME", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent (this, SaveCustomer1.class);
            startActivity(intent);

      //  }
     //   else
      //      Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();


    }





}
