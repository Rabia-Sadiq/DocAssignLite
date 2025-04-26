package com.example.docassignlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BrowseActivity extends AppCompatActivity {
    ListView listViewProfessions;
    String[] professions = {
            "Doctor (MD)",
            "Doctor (DO)",
            "Physician Assistant (PA)",
            "Nurse Practitioner (NP)",
            "Dentist"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.browse), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listViewProfessions = findViewById(R.id.listViewProfessions);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                professions
        );

        listViewProfessions.setAdapter(adapter);
        listViewProfessions.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedProfession = professions[position];

                Intent intent = new Intent(BrowseActivity.this, ProviderListActivity.class);
                intent.putExtra("profession", selectedProfession); // pass selected profession
                startActivity(intent);
            }
        });    }


}
