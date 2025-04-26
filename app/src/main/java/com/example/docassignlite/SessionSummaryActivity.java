package com.example.docassignlite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SessionSummaryActivity extends AppCompatActivity {

    TextView textViewSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_summary);

        textViewSummary = findViewById(R.id.textViewSummary);

        // Load booking from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyBookings", MODE_PRIVATE);
        String lastBooking = prefs.getString("lastBooking", "No bookings found.");

        textViewSummary.setText("Your Last Session:\n\n" + lastBooking);
    }
}
