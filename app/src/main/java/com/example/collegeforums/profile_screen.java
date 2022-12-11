package com.example.collegeforums;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeforums.databinding.FragmentProfileScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.HashMap;


public class profile_screen extends Fragment {


    FirebaseAuth auth;
    FragmentProfileScreenBinding binding;
    //CollectionReference fstore;
//    DocumentReference dataRef;
    DatabaseReference databaseReference;
    FirebaseFirestore db;
    String uid;
    User user;

    EditText fname;
    AppCompatButton logout,update_btn;
    EditText lname,contact,email,institute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v =  inflater.inflate(R.layout.fragment_profile_screen, container, false);
        logout = v.findViewById(R.id.profile_logout_bt);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });


//        update_btn = v.findViewById(R.id.update_btn);
//        update_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String fname = binding.profileFirstnameTv.getText().toString();
//                String lname = binding.profileLastnameTv.getText().toString();
//                String contact = binding.profileContactTv.getText().toString();
//                String email = binding.profileEmailTv.getText().toString();
//                String institute = binding.profileIntsTv.getText().toString();
//
//                binding.profileFirstnameTv.setText("");
//                binding.profileLastnameTv.setText("");
//                binding.profileContactTv.setText("");
//                binding.profileEmailTv.setText("");
//                binding.profileIntsTv.setText("");
//
//                updateData(fname,lname,contact,email,institute);
//            }
//
//            private void updateData(String fname, String lname, String contact, String email, String institute) {
//                DocumentReference docRef = db.collection("User").document(uid);
//                docRef.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                        binding.profileFirstnameTv.setText(value.getString("firstName"));
//                        binding.profileLastnameTv.setText(value.getString("lastName"));
//                        binding.profileContactTv.setText(value.getString("contact"));
//                        binding.profileEmailTv.setText(value.getString("email"));
//                        binding.profileIntsTv.setText(value.getString("institute"));
//                    }
//                });
//
//
//            }
//
//
//        });


        fname = v.findViewById(R.id.profile_firstname_tv);
        lname = v.findViewById(R.id.profile_lastname_tv);
        contact = v.findViewById(R.id.profile_contact_tv);
        email = v.findViewById(R.id.profile_email_tv);
        institute = v.findViewById(R.id.profile_ints_tv);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        uid = auth.getCurrentUser().getUid();





//        DocumentReference docref = db.collection("user").document(uid);
//        docref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                fname.setText(value.getString("firstName"));
//            }
//        });

          DocumentReference docRef = db.collection("User").document(uid);
          docRef.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                fname.setText(value.getString("firstName"));
                lname.setText(value.getString("lastName"));
                contact.setText(value.getString("contact"));
                email.setText(value.getString("email"));
                institute.setText(value.getString("institute"));
            }
        });

//        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                user = documentSnapshot.toObject(User.class);
//                fname.setText(documentSnapshot.getString("firstName"));
//            }
//        });





        return v;
    }




}





//                databaseReference = FirebaseDatabase.getInstance().getReference("User");
//                databaseReference.child("User").updateChildren(user_up).addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        if(task.isSuccessful()){
//                            binding.profileFirstnameTv.setText("");
//                            binding.profileLastnameTv.setText("");
//                            binding.profileContactTv.setText("");
//                            binding.profileEmailTv.setText("");
//                            binding.profileIntsTv.setText("");
//                            Toast.makeText(getActivity(),"Successfully Updated!",Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(getActivity(),"Successfully Updated!",Toast.LENGTH_SHORT).show();
//
//
//                        }
//                    }
//                });