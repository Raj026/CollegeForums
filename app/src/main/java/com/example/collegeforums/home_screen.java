package com.example.collegeforums;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.collegeforums.databinding.FragmentHomeScreenBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Context;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class home_screen extends Fragment {

//    String uid;
//    User user;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    List<Thread> postList;
    FragmentHomeScreenBinding binding;
    //TextView username,user_email;
//    View headerView;
//    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home_screen, container, false);
        binding =FragmentHomeScreenBinding.bind(v);

        binding.createPostFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.create_post_fragment);




            }
        });
















//        db.collection(uid)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            for(DocumentSnapshot documentSnapshot:task.getResult()){{
//                                username.setText(documentSnapshot.getString("firstName"));
//                                user_email.setText(documentSnapshot.getString("email"));
//                            }}
//                        }
//                    }
//                });




//        uid = Objects.requireNonNull(auth.getCurrentUser()).getUid();
//
//
//        DocumentReference docRef = db.collection("User").document(uid);
//        docRef.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                username.setText(value.getString("firstName"+" "+"lastName"));
//                user_email.setText(value.getString("email"));

//                fname.setText(value.getString("firstName"));
//                lname.setText(value.getString("lastName"));
//                contact.setText(value.getString("contact"));
//                email.setText(value.getString("email"));
//                institute.setText(value.getString("institute"));
//            }
//        });

        recyclerView = binding.postRv;
        setUpRecyclerView();










        return v;
    }

    void setUpRecyclerView() {




        FirestoreRecyclerOptions<Thread> options = new FirestoreRecyclerOptions.Builder<Thread>()
                .setQuery(Utility.getCollectionReference().orderBy("title",Query.Direction.ASCENDING), Thread.class).build();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        postAdapter = (PostAdapter) new PostAdapter(options, this);
        recyclerView.setAdapter(postAdapter);


//        FirestoreRecyclerOptions<Thread> options = new FirestoreRecyclerOptions.Builder<Thread>()
//                .setQuery(FirebaseFirestore.getInstance().collection("threads").document().collection("my_posts")., Thread.class).build();
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        postAdapter = (PostAdapter) new PostAdapter(options, this);
//        recyclerView.setAdapter(postAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        postAdapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        postAdapter.notifyDataSetChanged();
    }
}