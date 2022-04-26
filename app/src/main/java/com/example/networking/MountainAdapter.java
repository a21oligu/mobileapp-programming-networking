package com.example.networking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {
    private ArrayList<MountainListItem> mountains;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    public MountainAdapter(Context context, ArrayList<MountainListItem> mountains, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mountains = mountains;
        this.onClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            textView = view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(mountains.get(getAbsoluteAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(mountains.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mountains.size();
    }

    public interface OnClickListener {
        void onClick(MountainListItem mountain);
    }

}
