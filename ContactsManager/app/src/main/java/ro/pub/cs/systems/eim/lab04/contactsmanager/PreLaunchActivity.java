package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class PreLaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_launch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            String number = ((EditText) findViewById(R.id.f_launch_phone)).getText().toString();
            Intent intent = new Intent(this, ContactsManagerActivity.class);
            if (!number.isEmpty()) {
                intent.putExtra("phone", number);
            }
            startActivity(intent);
        });
    }
}