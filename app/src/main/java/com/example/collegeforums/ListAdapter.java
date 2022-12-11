package com.example.collegeforums;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private ArrayList<Thread> mThreads = new ArrayList<>();
    private Context mContext;

    public ListAdapter(Context context, ArrayList<Thread> threads) {
        mContext = context;
        mThreads = threads;
    }

    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListViewHolder holder, int position) {
        holder.bindRestaurant(mThreads.get(position));
    }

    @Override
    public int getItemCount() {
        return mThreads.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUsername, tvTitle, tvComment;

        public ListViewHolder(View itemView) {
            super(itemView);
            this.tvUsername = itemView.findViewById(R.id.tv_username);
            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.tvComment = itemView.findViewById(R.id.tv_comment);
        }

        public void bindRestaurant(Thread thread) {
            tvUsername.setText(thread.userName);
            tvTitle.setText(thread.title);
            tvComment.setText(thread.comment);
        }
    }


}
