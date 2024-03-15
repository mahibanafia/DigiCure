package com.remon.digicure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class SignUpPage extends AppCompatActivity {


    //Firebase Authentication

   FirebaseDatabase firebaseDatabase;
   DatabaseReference reference;
    private EditText Fname, Lname, Title, Categ, NatioNo, RegisNo, Gend, Email, Pass, RePass;
     Button signUpBtn;



    String a = "Patient";
    String b = "Doctor";
    TextView memberLogin;
    private RadioButton PatButton;
    private RadioButton DocButton;
    LinearLayout DocL;

    //gender variable declare....
    String[] genderItem = {"Male", "Female"};
    ArrayAdapter<String> adapterGender;
    AutoCompleteTextView autoCompleteTextViewGender;

    //Title Variable.....
    String[] TitleItem = {"Dr.", "Prof Dr.","assoc. Dr.", "Asst. Dr."};
    ArrayAdapter<String> adapterTitle;
    AutoCompleteTextView autoCompleteTextViewTitle;

    //Category variable.....
    String[] CategoryItem = {"Neurology", "Gynecology","Dermatology", "Psychology", "Orthopedics", "Cardiology","Dentistry", "ENT"};
    ArrayAdapter<String> adapterCategory;
    AutoCompleteTextView autoCompleteTextViewCategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        //Go to Login Page
        memberLogin = findViewById(R.id.Member_Lgoin_Text);

        //Member login page Go...
        memberLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this, loginPage.class));
            }
        });

        //Radio Buttons & Doc Linear Section
        PatButton = findViewById(R.id.radioButton_patient);
        DocButton = findViewById(R.id.radioButton_Doctor);
        DocL = findViewById(R.id.DoctorLinear);


        //Radio Button  Patient Work....
        PatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DP = PatButton.getText().toString();
                if (a.equals(DP)) {
                    DocL.setVisibility(View.GONE);
                }
            }
        });

        //Radio Button Doctor Work....
        DocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DP = DocButton.getText().toString();
                if (b.equals(DP)) {
                    DocL.setVisibility(View.VISIBLE);
                }
            }
        });

        //Gender Item Selection......
        autoCompleteTextViewGender = findViewById(R.id.auto_complete_gender);
        adapterGender = new ArrayAdapter<String>(this, R.layout.list_gender_item, genderItem);

        autoCompleteTextViewGender.setAdapter(adapterGender);

        autoCompleteTextViewGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items = parent.getItemAtPosition(position).toString();
            }
        });


        //Title Item Selection......

        autoCompleteTextViewTitle = findViewById(R.id.auto_complete_Title);
        adapterTitle = new ArrayAdapter<String>(this, R.layout.list_gender_item, TitleItem);

        autoCompleteTextViewTitle.setAdapter(adapterTitle);

        autoCompleteTextViewTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String items = adapterView.getItemAtPosition(position).toString();
            }
        });

        //Category Item Selection......
        autoCompleteTextViewCategory = findViewById(R.id.auto_complete_Category);
        adapterCategory = new ArrayAdapter<String>(this, R.layout.list_gender_item, CategoryItem);

        autoCompleteTextViewCategory.setAdapter(adapterCategory);

        autoCompleteTextViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent2, View view, int position, long id) {
                String items = parent2.getItemAtPosition(position).toString();
            }
        });

        //Authentication....................

        //Title = findViewById(R.id.auto_complete_Title);
        // Categ = findViewById(R.id.auto_complete_Category);

        // Gend = findViewById(R.id.auto_complete_gender);


        signUpBtn = findViewById(R.id.registration_Button1);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });




    }

    private void registerUser() {

        Fname = findViewById(R.id.edit_First_Namewer);
        Lname = findViewById(R.id.edit_Last_nameqrr);
        NatioNo = findViewById(R.id.edit_national_ID);
        RegisNo = findViewById(R.id.edit_Regis_no);
        Email = findViewById(R.id.edit_Reg_Email);
        Pass = findViewById(R.id.edit_Reg_pass);
        RePass = findViewById(R.id.edit_Reg_Repass);


        String fname_ = Fname.getText().toString();
        String lname_ = Lname.getText().toString();
        //String title = Title.getText().toString().trim();
        //String categ = Categ.getText().toString().trim();
        String natioNo_ = NatioNo.getText().toString();
        String regiNo_ = RegisNo.getText().toString();
        //String gend = Gend.getText().toString().trim();
        String email_ = Email.getText().toString();
        String pass_ = Pass.getText().toString();
        String repass_ = RePass.getText().toString();

        if (fname_.isEmpty() || lname_.isEmpty() || natioNo_.isEmpty() || regiNo_.isEmpty() || email_.isEmpty() || pass_.isEmpty() || repass_.isEmpty()) {
            Toast.makeText(SignUpPage.this, "Fill all fields", Toast.LENGTH_SHORT).show();
        } else if (!pass_.equals(repass_)) {
            RePass.setText("");
            Toast.makeText(SignUpPage.this, "Password didn't match", Toast.LENGTH_SHORT).show();
        }
        else{
                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("datauser");

            String fname_s = Fname.getText().toString();
            String lname_s = Lname.getText().toString();
            //String title = Title.getText().toString().trim();
            //String categ = Categ.getText().toString().trim();
            String natioNo_s = NatioNo.getText().toString();
            String regiNo_s = RegisNo.getText().toString();
            //String gend = Gend.getText().toString().trim();
            String email_s = Email.getText().toString();
            String pass_s = Pass.getText().toString();
            String repass_s = RePass.getText().toString();

            User users = new User(fname_s,lname_s, natioNo_s, regiNo_s,email_s, pass_s);
            reference.child(lname_s).setValue(users);

            Toast.makeText(getApplicationContext(), "Sign Up Successfull", Toast.LENGTH_SHORT).show();
            showLoginPage();
        }
    }

    private void showLoginPage(){
        startActivity(new Intent(SignUpPage.this, loginPage.class));
        finish();
    }
}