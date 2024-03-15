package com.remon.digicure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginPage extends AppCompatActivity {

    TextView memberSignUp;
    EditText logEmail, LogPass;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginBtn = findViewById(R.id.login1);

        memberSignUp= findViewById(R.id.sign_up_Text);

        memberSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginPage.this, SignUpPage.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logEmail = findViewById(R.id.edit_login_Email);
                LogPass = findViewById(R.id.edit_login_pass);

                String logEmail_ = logEmail.getText().toString();
                String LogPass_ = LogPass.getText().toString();

                if (logEmail_.isEmpty() || LogPass_.isEmpty()){
                    Toast.makeText(loginPage.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    final String username_data = logEmail.getText().toString();
                    final String password_data = LogPass.getText().toString();

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

                    Query checkEmail = databaseReference.orderByChild("lname").equalTo(username_data);
                    checkEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()){
                                    String passCheck = snapshot.child(username_data).child("pass").getValue(String.class);
                                    if(passCheck.equals(password_data)){

                                        Toast.makeText(loginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                         startActivity(new Intent(loginPage.this, MainDashboard.class));
                                         finish();
                                    }
                                    else{
                                        Toast.makeText(loginPage.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                            }
                            else {
                                Toast.makeText(loginPage.this, "Username Doesn't Exit", Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }
            }
        });
    }
}