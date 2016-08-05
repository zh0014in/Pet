package com.pet.zzz.pet;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class NewEventActivity extends AppCompatActivity  {
    PetDbHelper mDbHelper = new PetDbHelper(NewEventActivity.this);


    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        initializePetSpinner();
        initializeDatepicker();
    }

    private void initializePetSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.pets_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,getData());
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private ArrayList<CharSequence> getData(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_NAME_NAME
        };
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                PetContract.PetEntry.COLUMN_NAME_NAME + " ASC";

        Cursor c = db.query(
                PetContract.PetEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        c.moveToFirst();
        ArrayList<CharSequence> list = new ArrayList<CharSequence>();
        if(c.getCount()>0) {
            String petName = c.getString(c.getColumnIndexOrThrow(PetContract.PetEntry.COLUMN_NAME_NAME));

            list.add(petName);
        }
        list.add("new");
        return list;
    }


    private void initializeDatepicker(){
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        EditText editText = (EditText)findViewById(R.id.datepicker);
        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(NewEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Calendar calendar = Calendar.getInstance();
        myCalendar.setTime(calendar.getTime());
        updateLabel();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EditText editText = (EditText)findViewById(R.id.datepicker);
        editText.setText(sdf.format(myCalendar.getTime()));
    }
}
