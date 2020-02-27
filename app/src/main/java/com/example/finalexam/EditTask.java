package com.example.finalexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class EditTask extends AppCompatActivity {
    private EditText etnom;
    private TextView edate;
    private CheckBox edone;
    private String nom,date;
    private int done;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        Intent i = getIntent();
        nom = i.getStringExtra("name");
        date = i.getStringExtra("date");
        done = i.getIntExtra("done",0);

        etnom = findViewById(R.id.cnom);
        edate = findViewById(R.id.cdate);
        edone = findViewById(R.id.ccompleted);

        etnom.setText(nom);
        edate.setText(date);
        if(done == 1){
            edone.setChecked(true);
        }
        date = new Date().toString();
        edate.setText(date);
        Button save = findViewById(R.id.guardar);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom = etnom.getText().toString();
                if(nom.equals("")){
                    Toast.makeText(EditTask.this, "Has d'insertar un nom", Toast.LENGTH_SHORT).show();
                }else{
                    Intent resultIntent = new Intent();
                    date = new Date().toString();
                    resultIntent.putExtra("name",nom);
                    resultIntent.putExtra("date",date);
                    resultIntent.putExtra("done",done);
                    setResult(RESULT_OK,resultIntent);
                    finish();
                }
            }
        });
    }
}
