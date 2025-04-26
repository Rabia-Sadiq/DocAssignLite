package com.example.docassignlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
      login=findViewById(R.id.btnLogin);
      login.setOnClickListener(view->loginTo());
    }

    private void loginTo() {
        TextView emails=findViewById(R.id.editTextEmail);
        TextView passwords=findViewById(R.id.editTextPassword);
        String email = emails.getText().toString().trim();
        String password = passwords.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(MainActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // If everything is valid, proceed to BrowseActivity
        Intent intent = new Intent(MainActivity.this, BrowseActivity.class);
        startActivity(intent);
    }
}