package com.example.docassignlite;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    TextView textViewProviderName;
    Button btnPickDate, btnPickTime, btnConfirm;
    EditText editTextNotes;
    String selectedDate = "";
    String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        textViewProviderName = findViewById(R.id.textViewProviderName);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnPickTime = findViewById(R.id.btnPickTime);
        btnConfirm = findViewById(R.id.btnConfirm);
        editTextNotes = findViewById(R.id.editTextNotes);

        // Get provider name from intent
        String providerName = getIntent().getStringExtra("providerName");
        textViewProviderName.setText("Booking for: " + providerName);

        btnPickDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dp = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
                selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                btnPickDate.setText("Date: " + selectedDate);
            }, year, month, day);
            dp.show();
        });

        btnPickTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog tp = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
                selectedTime = String.format("%02d:%02d", hourOfDay, minute1);
                btnPickTime.setText("Time: " + selectedTime);
            }, hour, minute, true);
            tp.show();
        });

        btnConfirm.setOnClickListener(v -> {
            String notes = editTextNotes.getText().toString().trim();

            if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select date and time", Toast.LENGTH_SHORT).show();
                return;
            }

            // Show confirmation
            String message = "Session confirmed with " + providerName +
                    "\nOn: " + selectedDate + " at " + selectedTime +
                    "\nNotes: " + (notes.isEmpty() ? "None" : notes);

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            getSharedPreferences("MyBookings", MODE_PRIVATE)
                    .edit()
                    .putString("lastBooking", message)
                    .apply();

// Open the summary screen
            Intent i = new Intent(BookingActivity.this, SessionSummaryActivity.class);
            startActivity(i);
        });
    }
}
