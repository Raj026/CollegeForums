package com.example.collegeforums;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.collegeforums.databinding.FragmentSearchUserBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class search_user extends Fragment {


    SearchAdapter adapter;
    //FirebaseRecyclerAdapter<Search_model, UsersViewHolder> firebaseRecyclerAdapter;
    //MainAdapter mainAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public search_user() {
        // Required empty public constructor
    }

    SearchView msearch;
    //search_adapter adapter;
    RecyclerView mResult;
    FragmentSearchUserBinding binding;
    String uid;
    FirebaseAuth firebaseAuth;
    DatabaseReference mUserDatabase;
    Button searchButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_user, container, false);
        mResult = (RecyclerView) v.findViewById(R.id.recycler_view_search_list);
        msearch = (SearchView) v.findViewById(R.id.search_view);

        mResult.setLayoutManager(new LinearLayoutManager(getContext()));
//        searchButton = (Button) v.findViewById(R.id.button);

        msearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newtext) {
                mysearch(newtext);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newtext) {
                mysearch(newtext);
                return false;
            }
        });




    return v;}

    private void mysearch(String str){
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(FirebaseFirestore.getInstance().collection("User").orderBy("firstName").startAt(str).endAt(str+"\uf8ff"), User.class).build();


        adapter = new SearchAdapter(options);
        adapter.startListening();
        mResult.setAdapter(adapter);
    }
}