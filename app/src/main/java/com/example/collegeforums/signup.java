package com.example.collegeforums;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegeforums.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        EditText first_name = findViewById(R.id.signup_firstname_et);
        EditText ipcontact = findViewById(R.id.signup_contact_et);
        EditText ipass = findViewById(R.id.signup_password_et);
        EditText ipconfirmp = findViewById(R.id.signup_confirm_et);


        binding.signupSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = binding.signupFirstnameEt.getText().toString();
                String lastName = binding.signupLastnameEt.getText().toString();
                String email = binding.signupEmailEt.getText().toString();
                String contact = binding.signupContactEt.getText().toString();
                String institute = binding.signupInstituteEt.getText().toString();
                String password = binding.signupPasswordEt.getText().toString();
                String confirmPassword = binding.signupConfirmEt.getText().toString();


                if(firstName.isEmpty()){
                    first_name.setError("Enter First Name");
                }
                else if (contact.isEmpty() || contact.length() < 10 || contact.length() > 11) {
                    ipcontact.setError("Please Enter 10 Digits");

                }
                else if(password.isEmpty()){
                    ipass.setError("Enter Password");
                }


                else if (!password.equals(confirmPassword) && confirmPassword.isEmpty()) {
                    ipconfirmp.setError("Password does'nt match");

                } else {
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(signup.this, Dashboard.class));
                                    progressDialog.cancel();

                                    firebaseFirestore.collection("User")
                                            .document(FirebaseAuth.getInstance().getUid())
                                            .set(new User(firstName, lastName, email, contact, institute));


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.cancel();

                                }
                            });
                }
            }
        });
    }
}