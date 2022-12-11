package com.example.collegeforums;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchAdapter extends FirestoreRecyclerAdapter<User,SearchAdapter.SearchViewHolder> {
//    search_user context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SearchAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position, @NonNull User model) {
        holder.username_search.setText(model.getFirstName());
        holder.username_email.setText(model.email);


    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list,parent,false);
        return new SearchViewHolder(view);
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView username_search,username_email;

        public SearchViewHolder(@NonNull View itemView){
            super(itemView);
            username_search = itemView.findViewById(R.id.text_view_user_name_search_list);
            username_email = itemView.findViewById(R.id.text_view_description_search_list);

        }
    }


}
