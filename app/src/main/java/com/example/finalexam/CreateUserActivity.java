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

public class CreateUserActivity extends AppCompatActivity {

    private EditText etnom;
    private TextView edate;
    private CheckBox edone;
    private String nom,date;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        nom = "";
        date = "";

        etnom = findViewById(R.id.cnom);
        edate = findViewById(R.id.cdate);
        date = new Date().toString();
        edate.setText(date);
        Button save = findViewById(R.id.guardar);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom = etnom.getText().toString();
                if(nom.equals("")){
                    Toast.makeText(CreateUserActivity.this, "No has insertat totes les dades", Toast.LENGTH_SHORT).show();
                }else{
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("name",nom);
                    resultIntent.putExtra("date",date);

                    setResult(RESULT_OK,resultIntent);
                    finish();
                }
            }
        });
    }
}
