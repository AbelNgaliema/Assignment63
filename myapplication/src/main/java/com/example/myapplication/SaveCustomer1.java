package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import Domain.Employee;

public class SaveCustomer1 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_customer1);
        Intent intent = getIntent();



    }
    @Override
    protected void onStart()
    {
        super.onStart();




    }


    protected void save(View view)
    {


        EditText name = (EditText)findViewById(R.id.name);
        EditText surname = (EditText)findViewById(R.id.surname);
        EditText position = (EditText)findViewById(R.id.position);
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);

        Employee entity =  new Employee.Builder().name(name.getText().toString())
                .surname(surname.getText().toString())
                .position(position.getText().toString())
                .systemName(username.getText().toString())
                .salary(12333.00)
                .password(password.getText().toString())
                 .build();




        Intent intent = new Intent(this,the_Records.class);
        intent.putExtra("name", entity.getName());
        intent.putExtra("surname",entity.getSurname());
        intent.putExtra("position", entity.getPosition());
        intent.putExtra("systemName", entity.getSystemName());
        intent.putExtra("salary","1130202" );
        intent.putExtra("password", entity.getPassword());

        startActivity(intent);


    }
}
