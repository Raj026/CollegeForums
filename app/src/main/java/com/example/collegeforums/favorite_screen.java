package com.example.collegeforums;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favorite_screen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favorite_screen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favorite_screen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favorite_screen.
     */
    // TODO: Rename and change types and number of parameters
    public static favorite_screen newInstance(String param1, String param2) {
        favorite_screen fragment = new favorite_screen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    SearchView msearch;
    RecyclerView mResult;
    Favorite_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_favorite_screen, container, false);

        mResult = (RecyclerView) v.findViewById(R.id.recycler_view_fav_list);
        msearch = (SearchView) v.findViewById(R.id.search_fav);

        mResult.setLayoutManager(new LinearLayoutManager(getContext()));
//        searchButton = (Button) v.findViewById(R.id.button);

        msearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newtext) {
                myfav(newtext);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newtext) {
                myfav(newtext);
                return false;
            }
        });


        return v;
    }

    private void myfav(String str){
        FirestoreRecyclerOptions<Thread> options = new FirestoreRecyclerOptions.Builder<Thread>()
                .setQuery(Utility.getCollectionReference().orderBy("title", Query.Direction.ASCENDING).startAt(str).endAt(str+"\uf8ff"), Thread.class).build();


        adapter = new Favorite_Adapter(options);
        adapter.startListening();
        mResult.setAdapter(adapter);
    }
}