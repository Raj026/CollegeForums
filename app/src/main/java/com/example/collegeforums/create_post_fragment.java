package com.example.collegeforums;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeforums.databinding.FragmentCreatePostFragmentBinding;
import com.example.collegeforums.databinding.FragmentProfileScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link create_post_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class create_post_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnAddThread;
    FragmentProfileScreenBinding bind;
    User user;
    String firstName;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    String uid;

    FragmentCreatePostFragmentBinding binding;

    public create_post_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment create_post_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static create_post_fragment newInstance(String param1, String param2) {
        create_post_fragment fragment = new create_post_fragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_post_fragment, container, false);
        binding = FragmentCreatePostFragmentBinding.bind(v);

        EditText title_et = v.findViewById(R.id.topic_thread);
        EditText comment_et = v.findViewById(R.id.comment_thread);

        btnAddThread = v.findViewById(R.id.add_thread_comment);
        btnAddThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = binding.topicThread.getText().toString();
                String comment = binding.commentThread.getText().toString();





                if(title.isEmpty()){
                    title_et.setError("Enter Title");
                }
                else if(comment.isEmpty()){
                    comment_et.setError("Enter comment");
                }



                firebaseAuth = (FirebaseAuth) FirebaseAuth.getInstance();


                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if(currentUser!=null){
                    uid = currentUser.getUid();
//                    firstName = currentUser.getDisplayName();




                }


                firstName = "Jay";











                ArrayList<String> likes = new ArrayList<>(), comments = new ArrayList<>();


                Thread thread = new Thread(title,comment,firstName, uid,likes,comments);
                DocumentReference docref;
                docref = Utility.getCollectionReference().document();

                docref.set(thread).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Thread Added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });








//                fstore.collection("User").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            user = task.getResult().toObject(User.class);
//                            fstore.collection("threads")
//                                    .document()
//                                    .set(new Thread(title, comment, user.firstName, firebaseAuth.getCurrentUser().getUid(), likes, comments));
//                        } else {
//                            Toast.makeText(requireContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


            }
        });

        return v;
    }
}