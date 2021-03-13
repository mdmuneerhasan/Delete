package com.example.shehacks;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button login,register;
    RadioGroup rdGroup;
    int checked=R.id.rdParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        rdGroup=findViewById(R.id.rgGroup);




        ((RadioButton)findViewById(checked)).setChecked(true);
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checked=checkedId;
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (checked){
                    case R.id.rdParent:
                        startActivity(new Intent(getBaseContext(),ParentRegister.class));
                        break;
                    case R.id.rdProvider:
                        startActivity(new Intent(getBaseContext(),ServiceProviderRegister.class));
                        break;
                }
            }
        });


    }
}