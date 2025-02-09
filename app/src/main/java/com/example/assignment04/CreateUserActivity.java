package com.example.assignment04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.atomic.AtomicReference;

public class CreateUserActivity extends AppCompatActivity {

    final static public String USER_KEY = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button createButton = findViewById(R.id.button);
        EditText nameEdit = findViewById(R.id.name_edit);
        EditText emailEdit = findViewById(R.id.email_edit);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString().trim();
                String email = emailEdit.getText().toString().trim();
                String role = "";

                if (radioGroup.getCheckedRadioButtonId() == R.id.radio) {
                    role = "Student";
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton2) {
                    role = "Teacher";
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton3) {
                    role = "Other";
                }

                if (name.isEmpty() || email.isEmpty() || role.isEmpty()) {
                    Toast.makeText(CreateUserActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(name, email, role);
                    Intent intent = new Intent(CreateUserActivity.this, ProfileActivity.class);
                    intent.putExtra(USER_KEY, user);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}