package com.example.collegeforums;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Context;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PostAdapter extends FirestoreRecyclerAdapter<Thread,PostAdapter.PostViewHolder> {

    home_screen context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     * @param
     */
    public PostAdapter(@NonNull FirestoreRecyclerOptions<Thread> options,home_screen context) {

        super(options);
        this.context = context;


    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull Thread model) {

        holder.username_post.setText(model.userName);
        holder.post_title.setText(model.title);
        holder.post_desc.setText(model.comment);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView username_post,post_title,post_desc;

        public PostViewHolder(@NonNull View itemView){
            super(itemView);
            username_post = itemView.findViewById(R.id.tv_username);
            post_title = itemView.findViewById(R.id.tv_title);
            post_desc = itemView.findViewById(R.id.tv_comment);
        }
    }
}
