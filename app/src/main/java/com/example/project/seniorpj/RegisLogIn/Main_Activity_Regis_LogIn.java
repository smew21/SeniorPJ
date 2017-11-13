package com.example.project.seniorpj.RegisLogIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.project.seniorpj.R;

/**
 * Created by Smew on 22/9/2560.
 */

public class Main_Activity_Regis_LogIn extends AppCompatActivity{

    Button bt_register;
    Button bt_signIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_regis_login);

        bt_register = (Button)findViewById(R.id.bt_register);
        bt_signIn = (Button)findViewById(R.id.bt_LogIn);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LogInActivity.class);
                startActivity(intent);
            }
        });

    }

}
