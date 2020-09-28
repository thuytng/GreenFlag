package com.example.dom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class RegisterProfileActivity extends AppCompatActivity {


    DatePickerDialog picker;
    EditText etChooseBirthdate;
    EditText etPostalAddress;
    String[] countries = {"Barbados", "Canada", "Greenland", "Guatemala", "Haiti", "Jamaica", "Mexico", "Panama", "Puerto Rico", "United States"};
//    Spinner countrySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);

        etChooseBirthdate = findViewById(R.id.et_choose_birthdate);
//        countrySpinner = findViewById(R.id.sp_country);
        etPostalAddress = findViewById(R.id.et_postal_address);

        etChooseBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(RegisterProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etChooseBirthdate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        final ArrayAdapter<String> spinner_countries = new  ArrayAdapter<String>(RegisterProfileActivity.this,
                android.R.layout.simple_spinner_dropdown_item, countries);

        etPostalAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(RegisterProfileActivity.this)
                        .setTitle("Choose a country")
                        .setAdapter(spinner_countries, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                etPostalAddress.setText(countries[which].toString());
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });
    }
}