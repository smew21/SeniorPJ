package com.example.project.seniorpj.RegisLogIn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.seniorpj.Hamburger.HamburgerActivity;
import com.example.project.seniorpj.R;
import com.example.project.seniorpj.UserDB.User;
import com.example.project.seniorpj.UserDB.UserManager;

public class RegisterActivity extends AppCompatActivity {

    ImageView img_back;
    ImageView img_submit;
    EditText edt_username;
    EditText edt_password;
    EditText edt_email;
    EditText edt_age;
    Spinner spinner_gender;

    private UserManager mManager;
    private Context mContext;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        img_back = (ImageView) findViewById(R.id.img_back_regis);
        img_submit = (ImageView) findViewById(R.id.img_submit_regis);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_age = (EditText) findViewById(R.id.edt_age);
        spinner_gender = (Spinner) findViewById(R.id.spinner_gender);

        mManager = new UserManager(this);
        mContext = this;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinner_gender.getSelectedView()).setTextColor(Color.WHITE);
                ((TextView) spinner_gender.getSelectedView()).setTextSize(20);
            }
        });
        spinner_gender.setAdapter(adapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edt_username.getText().toString().trim().toLowerCase();
                String password = edt_password.getText().toString();
                String email = edt_email.getText().toString();
                String age = edt_age.getText().toString();
                String gender = spinner_gender.getSelectedItem().toString();

                User user = new User(username, password,email,age,gender);
                long rowId = mManager.registerUser(user);
                if (rowId == -1) {
                    String message = getString(R.string.register_error_message);
                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                } else {
                    String message = getString(R.string.register_success);
                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), HamburgerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
