package com.example.finalexam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnNoteListener {

    private MyDB db;
    private static int max;
    private RecyclerView rv;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        omplir();
        this.rv = findViewById(R.id.mainrv);
        max = 0;
        this.db = new MyDB(this);
        setAdapter();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create Task", Snackbar.LENGTH_LONG)
                        .setAction("CREAR", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                crearContacte();
                            }
                        }).show();
            }
        });
    }

    public void setAdapter() {
        Cursor c = db.selectRecords();
        max = c.getCount();
        String[] from = new String[]{"name"};
        int[] to = new int[]{android.R.id.text1};
        c.moveToNext();
        c.moveToNext();
        RecyclerAdapter wla = new RecyclerAdapter(this, c, tasks, this);//CLICK LISTENER?
        rv.setAdapter(wla);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void crearContacte() {
        Intent i = new Intent(this, CreateUserActivity.class);
        startActivityForResult(i, 1);
    }

    public void checkMax() {
        if (max < db.getCount()) {
            max = db.getCount();
        }
    }

    public void omplir() {
        //Recorregut del cursor per a omplir la array Tasks
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {//requestcode 1 es per quan es torna de la pantalla de crear tasks
            if (resultCode == RESULT_OK) {
                checkMax();
                db.createRecords(max + 1, data.getStringExtra("name"), data.getStringExtra("date"), 0);//Afegim la nova task
                Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show();//Toast per a avisar a l'usuari
            }
        }
        if (requestCode == 2){//requestcode 2 es quan es torna d'editar tasks
            if(resultCode == RESULT_OK){//Si s'ha completat l'activitat
                checkMax();
                db.updateRecords(Integer.parseInt(data.getStringExtra("id")),data.getStringExtra("name"),data.getStringExtra("date"),data.getIntExtra("done",0));//Actualitzam les dades
                Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();//Toast per a avisar a l'usuari
            }
        }
    }

    @Override
    public void onNoteClick(int position) {//Métode onClick per al RecyclerAdapter, quan es clica una task, es llança aquest métode agafant les variables necesàries per anar a la pantalla d'editar
        Intent i = new Intent(this, EditTask.class);
        i.putExtra("id",tasks.get(position).get_id());
        i.putExtra("name", tasks.get(position).get_name());
        i.putExtra("date", tasks.get(position).get_date());
        i.putExtra("done", tasks.get(position).get_done());
        startActivityForResult(i, 2);
    }
}
