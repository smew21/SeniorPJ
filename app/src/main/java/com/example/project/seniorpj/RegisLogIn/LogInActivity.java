package com.example.project.seniorpj.RegisLogIn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.seniorpj.Hamburger.HamburgerActivity;
import com.example.project.seniorpj.R;
import com.example.project.seniorpj.UserDB.User;
import com.example.project.seniorpj.UserDB.UserManager;

public class LogInActivity extends AppCompatActivity {

    ImageView img_back;
    ImageView img_submit;
    EditText edt_username;
    EditText edt_password;

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
        setContentView(R.layout.activity_log_in);

        img_back = (ImageView) findViewById(R.id.img_back_login);
        img_submit = (ImageView) findViewById(R.id.img_submit_login);
        edt_username = (EditText) findViewById(R.id.edt_username_login);
        edt_password = (EditText) findViewById(R.id.edt_password_login);

        mManager = new UserManager(this);
        mContext = this;

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String username = edt_username.getText().toString().trim().toLowerCase();
        String password = edt_password.getText().toString().trim();

        User user = new User(username, password);

        User validateUser = mManager.checkUserLogin(user);

        if (null == validateUser) {
            String message = getString(R.string.login_error_message);
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(mContext, HamburgerActivity.class);
            intent.putExtra(User.Column.USERNAME, validateUser.getUsername());
            intent.putExtra(User.Column.ID, validateUser.getId());
            startActivity(intent);
        }
    }
}
