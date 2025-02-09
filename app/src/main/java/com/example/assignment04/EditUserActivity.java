package com.example.assignment04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class EditUserActivity extends AppCompatActivity {

    final static public String USER_KEY = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button createButton = findViewById(R.id.submit_button);
        Button cancelButton = findViewById(R.id.button1);
        EditText nameEdit = findViewById(R.id.name_edit);
        EditText emailEdit = findViewById(R.id.email_edit);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(ProfileActivity.USER_KEY)) {
            User user = (User) getIntent().getSerializableExtra(ProfileActivity.USER_KEY);

            nameEdit.setText(user.name);
            emailEdit.setText(user.email);
            if (Objects.equals(user.role, "Student")) {
                radioGroup.check(R.id.radio);
            } else if (Objects.equals(user.role, "Teacher")) {
                radioGroup.check(R.id.radioButton2);
            } else if (Objects.equals(user.role, "Other")) {
                radioGroup.check(R.id.radioButton3);
            }
        }

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
                    Toast.makeText(EditUserActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(name, email, role);
                    Intent intent = new Intent(EditUserActivity.this, ProfileActivity.class);
                    intent.putExtra(USER_KEY, user);
                    startActivity(intent);
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(EditUserActivity.this, ProfileActivity.class);
                // startActivity(intent);
                finish();
            }
        });
    }
}