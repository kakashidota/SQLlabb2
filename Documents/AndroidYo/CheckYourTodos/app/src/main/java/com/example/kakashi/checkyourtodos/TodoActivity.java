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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {
    private ListView listView;
    private EditText titleTextView, contentTextView;
    private Button submitBtn, addTodoBtn;
    private int todoId;
    private TextView totaltView;
    private CheckBox checkBox;
    private List<Todo> todoList;
    private List<String> titleArray;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> todoAdapter;
    private String TAG = "willeTodo";
    private DBHelper dbHelper;
    private User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        importViewElements();
        dbHelper = new DBHelper(this);
        final Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null){
            int userID = b.getInt("userID");
            currentUser.setUserID(userID);
            Log.d(TAG, "TODO userID: " + userID);
        }
        Log.d(TAG, "USERID: " + currentUser.getUserID());

        todoList = dbHelper.getAllUserTodos(currentUser.getUserID());
        for (int i = 0; i < todoList.size(); i ++){
            Log.d(TAG, "Loop all todos : "+ i + todoList.get(i).getTodoTitle());
            Log.d(TAG, "Loop all todos : "+ i + todoList.get(i).getTodoUserID());
        }

        titleArray = new ArrayList<>();
        //titleArray = dbHelper.getAllUserNames();

        for (int i = 0; i < todoList.size(); i++) {
            titleArray.add(todoList.get(i).getTodoTitle());
        }




        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, titleArray);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(getApplicationContext(), TodoDetails.class);
                Todo currentTodo = todoList.get(position);
                myIntent.putExtra("todoid", currentTodo.getTodoID());
                myIntent.putExtra("titel", currentTodo.getTodoTitle());
                myIntent.putExtra("content", currentTodo.getTotoContent());
                startActivityForResult(myIntent, 1);

            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean b = checkBox.isChecked();
                int category;
                String title = titleTextView.getText().toString();
                String content = contentTextView.getText().toString();
                if (b){
                     category = 1;
                } else {
                     category = 0;
                }
                int userID = currentUser.getUserID();
                dbHelper.createTodo(title, content, category, userID);
                titleArray.add(titleTextView.getText().toString());
                showListAfterInput();
                adapter.notifyDataSetChanged();
            }
        });

    }


    private void showListAfterInput() {
        titleTextView.setVisibility(View.INVISIBLE);
        contentTextView.setVisibility(View.INVISIBLE);
        submitBtn.setVisibility(View.INVISIBLE);
        checkBox.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }
    private void importViewElements(){
        listView = findViewById(R.id.todoListView);
        checkBox = findViewById(R.id.checkBox);

        titleTextView = findViewById(R.id.editTodoTitle);
        contentTextView = findViewById(R.id.editTodoContent);
        submitBtn = findViewById(R.id.addTodoBtn);

        titleTextView.setVisibility(View.INVISIBLE);
        contentTextView.setVisibility(View.INVISIBLE);
        submitBtn.setVisibility(View.INVISIBLE);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    private void onClickAddBtn(){
        listView.setVisibility(View.INVISIBLE);
        titleTextView.setVisibility(View.VISIBLE);
        contentTextView.setVisibility(View.VISIBLE);
        submitBtn.setVisibility(View.VISIBLE);
        checkBox.setVisibility(View.VISIBLE);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add_task:
                onClickAddBtn();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this, "Todo is dead", Toast.LENGTH_SHORT).show();
                showListAfterInput();
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                showListAfterInput();
                adapter.notifyDataSetChanged();

            }
        }
    }//onActivityResult


}
