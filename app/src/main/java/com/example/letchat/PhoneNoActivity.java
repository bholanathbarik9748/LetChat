package com.example.letchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letchat.databinding.ActivityPhoneNoBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneNoActivity extends AppCompatActivity {

    ActivityPhoneNoBinding binding;
    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityPhoneNoBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_phone_no);
        setContentView(binding.getRoot());

        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNoActivity.this, OTPActivity.class);
                intent.putExtra("phoneNumber", binding.phoneBox.getText().toString());
                startActivity(intent);
            }
        });
    }
}