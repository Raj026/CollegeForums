package com.example.collegeforums;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;




public class Utility {




    static CollectionReference getCollectionReference(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String docid = "FQQJThMZKcRuJCWaUZ6dty3WvHv2";
        return FirebaseFirestore.getInstance().collection("threads")
                .document(docid).collection("my_posts");
    }
    static CollectionReference getDocumentReference(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        User user = new User();
        FirebaseAuth auth;
        FirebaseFirestore db;

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        String uid =auth.getCurrentUser().getUid();
        return FirebaseFirestore.getInstance().collection("User").document(uid).collection(user.getFirstName());
    }


    static Task<QuerySnapshot> taskquery(){

        return FirebaseFirestore.getInstance().collection("threads")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<DocumentSnapshot> myList = task.getResult().getDocuments();

                        }
                    }
                });
    }
}
