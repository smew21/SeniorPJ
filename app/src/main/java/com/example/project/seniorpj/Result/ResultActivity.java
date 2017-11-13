package com.example.project.seniorpj.Result;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.project.seniorpj.Camera.CameraActivity;
import com.example.project.seniorpj.R;

import java.util.Calendar;

/**
 * Created by Smew on 22/9/2560.
 */

public class ResultActivity extends AppCompatActivity {

    ImageView img;
    ImageView img_save;
    ImageView img_cancel;
    TextView tv_namefood;
    TextView tv_energy;
    TextView tv_protein;
    TextView tv_lipid;
    TextView tv_carbs;
    TextView tv_date;
    Spinner spinner_day;
    private int mYear, mMonth, mDay;
    private Context mContext;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfood);

        mContext = this;

        img = (ImageView) findViewById(R.id.img_detailfood);
        img_save = (ImageView) findViewById(R.id.img_save);
        img_cancel = (ImageView) findViewById(R.id.img_cancel);
        tv_namefood = (TextView) findViewById(R.id.tv_namefood_detailfood);
        tv_energy = (TextView) findViewById(R.id.tv_energy);
        tv_protein = (TextView) findViewById(R.id.tv_protein);
        tv_lipid = (TextView) findViewById(R.id.tv_lipid);
        tv_carbs = (TextView) findViewById(R.id.tv_carbs);
        tv_date = (TextView) findViewById(R.id.tv_date);
        spinner_day = (Spinner) findViewById(R.id.spinner_day);

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                Activity activity = (Activity) mContext;
                DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tv_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_day.setAdapter(adapter);

        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CameraActivity.class);
                startActivity(intent);
            }
        });

        img_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialogResult alertdFragment = new AlertDialogResult();
                // Show Alert DialogFragment
                alertdFragment.show(getFragmentManager(),"Alert Dialog Fragment");

//                Intent intent = new Intent(getBaseContext(), HamburgerActivity.class);
//                startActivity(intent);
            }
        });

    }

}
