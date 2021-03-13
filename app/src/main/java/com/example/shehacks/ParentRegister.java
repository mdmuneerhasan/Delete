package com.example.shehacks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ParentRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spHours;

    String hrs;
    private String[] hours;
    EditText edtName , edtAge,edtAddress,edtZipCode,edtExpectation,edtAdhaar,edtEmail,edtPassword;
    CheckBox chTerms;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_register);


        spHours = findViewById(R.id.spHours);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtAddress = findViewById(R.id.edtAddress);
        edtZipCode = findViewById(R.id.edtZip);
        edtExpectation = findViewById(R.id.edtExpectation);
        edtAdhaar = findViewById(R.id.edtAdhaar);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        chTerms = findViewById(R.id.chTerms);
        btnRegister = findViewById(R.id.btnRegister);


        hours=new String[25];
        hours[0]="Select number of hours";

        for (int i = 1; i <=24; i++) {
            hours[i]= i +" hrs ";
        }

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hours);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHours.setAdapter(ad);

        chTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chTerms.setError(null);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chTerms.isChecked()){
                    chTerms.setError("Please accept terms and condition");
                }else{
                    String password=edtPassword.getText().toString();
                    String email=edtEmail.getText().toString();
                    if(email.trim().isEmpty()){
                        edtEmail.setError("Email can't be empty");
                    }else if(password.trim().isEmpty()){
                        edtPassword.setError("Password can't be empty");
                    }else{
                        FirebaseAuth mAuth=FirebaseAuth.getInstance();
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(ParentRegister.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            MyUser myUser=new MyUser.Builder()
                                                    .setName(edtName.getText().toString())
                                                    .setEmail(email)
                                                    .setAge(edtAge.getText().toString())
                                                    .setAddress(edtAddress.getText().toString())
                                                    .setExpectation(edtExpectation.getText().toString())
                                                    .setAdhaar(edtAdhaar.getText().toString())
                                                    .setServiceHours(hrs)
                                                    .setAccountType(MyUser.PARENT)
                                                    .setUid(user.getUid())
                                                    .setZipCode(edtZipCode.getText().toString())
                                                    .build();
                                            Connection.getUserRef().child(user.getUid()).setValue(myUser);

                                            finish();
                                            startActivity(new Intent(getBaseContext(),HomeActivity.class));
                                            Toast.makeText(getBaseContext(), "Sign up successfully!!.",
                                                    Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(getBaseContext(), "User already exist!!",
                                                    Toast.LENGTH_LONG).show();
                                            Log.e("TAG", "onComplete: "+task.toString() );
                                        }
                                    }
                                });

                    }

                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hrs=hours[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        hrs="Not selected";
    }
}