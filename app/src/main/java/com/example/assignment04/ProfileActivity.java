package com.example.assignment04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    final static public String USER_KEY = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView profile_name = findViewById(R.id.profile_name);
        TextView profile_email = findViewById(R.id.profile_email);
        TextView profile_role = findViewById(R.id.profile_role);
        TextView button = findViewById(R.id.submit_button);


        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(CreateUserActivity.USER_KEY)) {
            User user = (User) getIntent().getSerializableExtra(CreateUserActivity.USER_KEY);

            profile_name.setText("Name:   " + user.name);
            profile_email.setText("Email:   " + user.email);
            profile_role.setText("Role:   " + user.role);

            button.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, EditUserActivity.class);
                intent.putExtra(USER_KEY, user);
                startActivity(intent);
                finish();
            });
        }

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(EditUserActivity.USER_KEY)) {
            User user = (User) getIntent().getSerializableExtra(EditUserActivity.USER_KEY);

            profile_name.setText("Name:   " + user.name);
            profile_email.setText("Email:   " + user.email);
            profile_role.setText("Role:   " + user.role);

            button.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, EditUserActivity.class);
                intent.putExtra(USER_KEY, user);
                startActivity(intent);
                // finish();
            });
        }
    }
}