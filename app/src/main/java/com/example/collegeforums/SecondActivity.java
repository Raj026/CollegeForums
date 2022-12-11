package com.example.collegeforums;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeforums.databinding.ActivitySecondBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SecondActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ActivitySecondBinding bind;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    TextView name,email_1;
    Button signOutBtn,signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.name);
        email_1 = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.sign_out);
        signIn = findViewById(R.id.sign_in_second);
        EditText ipcontact = findViewById(R.id.signup_contact_et_second);
        EditText ipconfirmp = findViewById(R.id.signup_confirm_et_second);
        EditText inst = findViewById(R.id.signup_institute_et_second);
        EditText pass = findViewById(R.id.signup_password_et_second);



        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email_1.setText(personEmail);
        }

        String firstName = name.getText().toString();
        String email = email_1.getText().toString();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(acct!=null){

                    String lastName = "";
                    String contact = ipcontact.getText().toString();
                    String institute =inst.getText().toString();
                    String password = pass.getText().toString();
                    String confirmPassword = ipconfirmp.getText().toString();

                    if (contact.isEmpty() || contact.length() < 10 || contact.length() > 11) {
                        ipcontact.setError("Please Enter 10 Digits");

                    } else if (!password.equals(confirmPassword)) {
                        ipconfirmp.setError("Password does'nt match");

                    } else {

                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        startActivity(new Intent(SecondActivity.this, Dashboard.class));


                                        firebaseFirestore.collection("User")
                                                .document(FirebaseAuth.getInstance().getUid())
                                                .set(new User(firstName, lastName, email, contact, institute));


                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SecondActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                                    }
                                });
                    }
                }

            }
        });




        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));

            }
        });
    }
}