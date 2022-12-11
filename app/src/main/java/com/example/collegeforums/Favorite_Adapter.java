package com.example.collegeforums;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Favorite_Adapter extends FirestoreRecyclerAdapter<Thread,Favorite_Adapter.myFavHolder> {
//    search_user context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param option
     */
    public Favorite_Adapter(@NonNull FirestoreRecyclerOptions<Thread> option) {
        super(option);
    }

    @Override
    protected void onBindViewHolder(@NonNull Favorite_Adapter.myFavHolder holder, int position, @NonNull Thread model) {
        holder.username_fav.setText(model.userName);
        holder.username_title.setText(model.title);
        holder.user_comment.setText(model.comment);



    }

    @NonNull
    @Override
    public myFavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item,parent,false);
        return new myFavHolder(view);
    }

    class myFavHolder extends RecyclerView.ViewHolder{
        TextView username_fav,username_title,user_comment;

        public myFavHolder(@NonNull View itemView){
            super(itemView);
            username_fav = itemView.findViewById(R.id.username_fav);
            username_title = itemView.findViewById(R.id.user_title);
            user_comment = itemView.findViewById(R.id.user_comment);

        }
    }


}

