package com.example.kakashi.checkyourtodos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "willeMain";
    EditText userNameEditText, userPasswordEditText;
    Button regBtn, logInBtn;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        importViewElements();

    }

    private void importViewElements(){
        userNameEditText = (EditText) findViewById(R.id.userNameTextInput);
        userPasswordEditText = (EditText)findViewById(R.id.userPasswordTextInput);
        logInBtn = findViewById(R.id.logInBtn);
        regBtn = findViewById(R.id.regBtn);
        dbHelper  = new DBHelper(this);
    }

    public void onClickLogin(View view){

        String userName = userNameEditText.getText().toString();
        String userPassword = userPasswordEditText.getText().toString();

        boolean b = dbHelper.loginUser(userName, userPassword);
        if (b){
            User user = dbHelper.getCurrentUser();
            int userID = user.getUserID();
            Intent intent = new Intent(this, TodoActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent);

            Log.d(TAG, "Du är inloggad");
        }

    }

    public void onClickReg (View v){
        String userName = userNameEditText.getText().toString();
        String userPassword = userPasswordEditText.getText().toString();

        boolean userExists = dbHelper.checkIfuserExists(userName);


        if (!userExists){
            boolean b = dbHelper.createUser(userName, userPassword);
            if (b) {
                Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClickReg: " + b);
            }

        } else {
            Toast.makeText(this, "User Already Exists", Toast.LENGTH_SHORT).show();
        }
    }
}
