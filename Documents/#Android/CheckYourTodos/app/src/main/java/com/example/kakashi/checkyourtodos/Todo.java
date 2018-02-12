package com.example.kakashi.checkyourtodos;

import java.io.Serializable;

/**
 * Created by Kakashi on 2018-02-06.
 */
public class Todo implements Serializable {
    private String todoTitle, totoContent;
    private int todoCategory;
    private int todoUserID;
    private int todoID;

    public Todo(String title, String content, int category, int userID) {
    }

    public int getTodoID() {
        return todoID;
    }

    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }



    public int getTodoUserID() {
        return todoUserID;
    }

    public void setTodoUserID(int todoUserID) {
        this.todoUserID = todoUserID;
    }

    public Todo(){

    }
    public Todo (String todoTitle, String todoContent, int todoCategory){
        this.todoTitle = todoTitle;
        this.totoContent = todoContent;
        this.todoCategory = todoCategory;
    }
    public Todo (String todoTitle, String todoContent){
        this.todoTitle = todoTitle;
        this.totoContent = todoContent;
    }
    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTotoContent() {
        return totoContent;
    }

    public void setTotoContent(String totoContent) {
        this.totoContent = totoContent;
    }

    public int getTodoCategory() {
        return todoCategory;
    }

    public void setTodoCategory(int todoCategory) {
        this.todoCategory = todoCategory;
    }
}
