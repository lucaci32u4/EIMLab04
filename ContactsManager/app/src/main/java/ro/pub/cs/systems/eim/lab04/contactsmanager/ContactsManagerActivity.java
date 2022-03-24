package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.provider.ContactsContract;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((EditText) findViewById(R.id.f_phone)).setText(getIntent().getExtras() != null ? getIntent().getExtras().getString("phone", "0771234567") : "0771234567");

        findViewById(R.id.showhide).setOnClickListener(view -> {
            ConstraintLayout layout = findViewById(R.id.layout_hidden);
            if (layout.getVisibility() == ConstraintLayout.VISIBLE) {
                layout.setVisibility(ConstraintLayout.INVISIBLE);
                ((Button) findViewById(R.id.showhide)).setText(((Button) findViewById(R.id.showhide)).getText().toString().replaceAll("[Hh]ide", "show"));
            } else {
                layout.setVisibility(ConstraintLayout.VISIBLE);
                ((Button) findViewById(R.id.showhide)).setText(((Button) findViewById(R.id.showhide)).getText().toString().replaceAll("[Ss]how", "hide"));
            }
        });

        findViewById(R.id.b_cancel).setOnClickListener(view -> finish());

        findViewById(R.id.b_save).setOnClickListener(view -> {
            String name = ((EditText) findViewById(R.id.f_name)).getText().toString();
            String phone = ((EditText) findViewById(R.id.f_phone)).getText().toString();
            String email = ((EditText) findViewById(R.id.f_email)).getText().toString();
            String address = ((EditText) findViewById(R.id.f_address)).getText().toString();

            String jobTitle = ((EditText) findViewById(R.id.f_job)).getText().toString();
            String company = ((EditText) findViewById(R.id.f_company)).getText().toString();
            String website = ((EditText) findViewById(R.id.f_website)).getText().toString();
            String im = ((EditText) findViewById(R.id.f_im)).getText().toString();

            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
            intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
            intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
            intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
            ArrayList<ContentValues> contactData = new ArrayList<>();

            ContentValues websiteRow = new ContentValues();
            websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
            websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
            contactData.add(websiteRow);
            ContentValues imRow = new ContentValues();
            imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
            imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
            contactData.add(imRow);
            intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
            startActivity(intent);

        });
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
}