package com.example.docassignlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ProviderListActivity extends AppCompatActivity {
    String[] dummyProviders = {
            "Dr. John Smith – Cardiologist",
            "Dr. Emily Rose – Pediatrician",
            "Dr. Henry Lee – Surgeon",
            "Dr. Olivia Adams – Dentist",
            "Dr. Ethan Clark – NP"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_provider_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.provider), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView title = findViewById(R.id.textViewTitle);
        ListView listView = findViewById(R.id.listViewProviders);

        // Get profession passed from BrowseActivity
        String profession = getIntent().getStringExtra("profession");
        title.setText("Providers for: " + profession);
        String[] providers;


        switch (profession) {
            case "Doctor (MD)":
                providers = new String[]{
                        "Dr. Ayesha Khan – Internal Medicine",
                        "Dr. Bilal Ahmed – Cardiology",
                        "Dr. Fatima Noor – Emergency Medicine",
                        "Dr. Hamza Tariq – Oncology",
                        "Dr. Sarah Malik – Neurology"
                };
                break;
            case "Doctor (DO)":
                providers = new String[]{
                        "Dr. Lisa Monroe – Osteopathic Family Medicine",
                        "Dr. Adam Ray – Sports Medicine",
                        "Dr. Nancy Drew – Pain Management",
                        "Dr. Omar Blake – Physical Therapy",
                        "Dr. Janelle Brooks – Preventive Medicine"
                };
                break;
            case "Physician Assistant (PA)":
                providers = new String[]{
                        "PA Sarah Lee – Dermatology",
                        "PA Jamal Scott – Pediatrics",
                        "PA Nina Patel – Cardiology",
                        "PA Carlos Rivera – Surgery Assist",
                        "PA Hannah Brown – Urgent Care"
                };
                break;
            case "Nurse Practitioner (NP)":
                providers = new String[]{
                        "NP Kelly Wong – Primary Care",
                        "NP Jason Kim – Mental Health",
                        "NP Emily Johnson – Women's Health",
                        "NP Tyler Green – Geriatrics",
                        "NP Olivia Brooks – Family Practice"
                };
                break;
            case "Dentist":
                providers = new String[]{
                        "Dr. Robert Chang – Orthodontics",
                        "Dr. Priya Mehta – General Dentistry",
                        "Dr. Alex Martinez – Pediatric Dentistry",
                        "Dr. Linda Zhao – Cosmetic Dentistry",
                        "Dr. Faisal Khan – Oral Surgery"
                };
                break;
            default:
                providers = new String[]{"No providers available."};
        }

        // Show dummy providers (for now the same list for all professions)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                providers
        );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String providerName = parent.getItemAtPosition(position).toString();

            Intent intent = new Intent(ProviderListActivity.this, BookingActivity.class);
            intent.putExtra("providerName", providerName);
            startActivity(intent);
        });
    }
}
