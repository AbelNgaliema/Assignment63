package com.example.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import Config.GlobalContext;
import Domain.Employee;
import Service.Implementation.EmployeeService.Implementation.EmployeeServiceImpl;

public class the_Records extends Activity {

    private static final String TAG="EMPLOYEE TEST1";
    private Long id;
    private EmployeeServiceImpl employeeService;
    private boolean isBound;
    private Employee employee;



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
        setContentView(R.layout.activity_the__records);
        Intent intent = getIntent();
        Log.i(TAG,intent.getStringExtra("name"));
        Log.i(TAG,intent.getStringExtra("surname"));
        Log.i(TAG,intent.getStringExtra("position"));
        Log.i(TAG,intent.getStringExtra("systemName"));
        Log.i(TAG,intent.getStringExtra("password"));
        Log.i(TAG,intent.getStringExtra("salary"));

        TextView name = (TextView) findViewById(R.id.name);
        TextView surname = (TextView) findViewById(R.id.surname);
        TextView salary = (TextView) findViewById(R.id.salary);
        TextView position = (TextView) findViewById(R.id.position);
        TextView username = (TextView)  findViewById(R.id.systemName);
        TextView password = (TextView) findViewById(R.id.Password);




        name.setText(intent.getStringExtra("name"));
        surname.setText(intent.getStringExtra("surname"));
        position.setText(intent.getStringExtra("position"));
        username.setText(intent.getStringExtra("systemName"));
        password.setText(intent.getStringExtra("password"));
        salary.setText(intent.getStringExtra("salary"));

        employee = new Employee.Builder().name(intent.getStringExtra("name"))
                .surname(intent.getStringExtra("surname"))
                .position(intent.getStringExtra("position"))
                .salary(Double.parseDouble(intent.getStringExtra("salary")))
                .systemName(intent.getStringExtra("systemName"))
                .password(intent.getStringExtra("salary"))
                 .build();



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

    protected void saveTheRecord (View v)
    {

        Employee record = null;
        record = employeeService.save(employee);

        if (record != null) {
            Toast.makeText(the_Records.this, "SUCCESSFUL ADDED", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, listOfObjects.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(the_Records.this,"failed",Toast.LENGTH_LONG).show();
        }

    }
}
