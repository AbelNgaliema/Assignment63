package com.example.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import Config.GlobalContext;
import Domain.Employee;
import Service.Implementation.EmployeeService.Implementation.EmployeeServiceImpl;

public class listOfObjects extends AppCompatActivity {
    private static final String TAG="EMPLOYEE TEST1";
    private Long id;
    private EmployeeServiceImpl employeeService;
    private boolean isBound;
    private Employee employee;
    private ArrayAdapter<Employee> listAdapter ;



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
        setContentView(R.layout.activity_list_of_objects);
        Intent intent = getIntent();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent intent = new Intent(this, EmployeeServiceImpl.class);


        GlobalContext.context = this;
        employeeService = EmployeeServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        Set<Employee> employees;
        employees =   employeeService.findAll();
        ArrayList<String> names = new ArrayList<String>();

        for (Employee employee : employees)
        {
                names.add( employee.getName() + " "+ employee.getSurname());
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);

        ListView listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(arrayAdapter);




    }
    protected void home(View view)
    {

        // //  EditText username = (EditText)findViewById(R.id.name);
        //   EditText password = (EditText)findViewById(R.id.password);
        //   boolean result = employeeService.passwordValidation(password.getText().toString());

        //    if (result)
        //  {
        Toast.makeText(listOfObjects.this, "WELCOME", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);

        //  }
        //   else
        //      Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();


    }


}
