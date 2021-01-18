package com.example.shlishli;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {

    private List<Search> searchList;

    public SearchRecyclerAdapter(List<Search> searchList) {
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Search data=searchList.get(position);
        holder.tv_title.setText(data.getTitle());
        holder.tv_name.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_title;
        private View rootView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView=itemView;
            tv_name=itemView.findViewById(R.id.tv_search_name);
            tv_title=itemView.findViewById(R.id.tv_search_title);
        }
    }
}
