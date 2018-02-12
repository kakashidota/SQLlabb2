package com.example.kakashi.checkyourtodos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TodoDetails extends AppCompatActivity {

    Button doneBtn;
    TextView textView;
    TextView contentView;
    Todo currentTodo;
    private DBHelper dbHelper;
    private int todoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        doneBtn = findViewById(R.id.doneBtn);
        textView = findViewById(R.id.todoTextView);
        contentView = findViewById(R.id.contentTextVie);
        Bundle bundle = getIntent().getExtras();
        dbHelper = new DBHelper(this);
        if(bundle != null)
        {
            //TODO here get the string stored in the string variable and do
            textView.setText(bundle.getString("titel"));
            contentView.setText(bundle.getString("content").toString());
            todoId = bundle.getInt("todoid");
            Log.d("bror", "onCreate: " + textView.getText() + contentView.getText() + todoId);

        }
    }

    public void onClickShowSql(View view){
        Intent myIntent = new Intent(getApplicationContext(), SQLActivity.class);
        startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deletetodo, menu);
        return true;
    }


    public void onDoneClicked(View view){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete:
                onClickDelete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickDelete(){
        dbHelper.deleteTodo(todoId);
        finish();
    }
}
