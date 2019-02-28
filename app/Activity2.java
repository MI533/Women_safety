package com.example.Mi5.womensafety;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity
{

    EditText e1;
    TextView textView;

    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contacts);

        Toolbar toolbar= (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.app_name);

        e1= (EditText)findViewById(R.id.editText1);
        textView= (TextView)findViewById(R.id.textView3);

        dbHandler = new MyDBHandler(this,null,null,1);

    }


    public void saveClicked(View v)
    {
        Contacts c1 = new Contacts(e1.getText().toString());
        dbHandler.addContact(c1);
        printDB();
    }

    public void deleteClicked(View v)
    {
        dbHandler.deleteContact();
        printDB();
    }

    public void printDB()
    {
        String db = dbHandler.getData();
        textView.setText(db);
        e1.setText("");
    }

}
