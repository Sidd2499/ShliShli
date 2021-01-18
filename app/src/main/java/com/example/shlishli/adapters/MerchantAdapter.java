package com.example.shlishli.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shlishli.R;
import com.example.shlishli.dataModels.Merchant;
import com.example.shlishli.dataModels.Search;

import org.w3c.dom.Text;

import java.util.List;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder>{




    List<Merchant> merchantList;


    public MerchantAdapter(List<Merchant> merchantList){
        this.merchantList = merchantList;
    }


    @NonNull
    @Override
    public MerchantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.merchant_item_recycler_view,parent,false);
        return new MerchantAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MerchantAdapter.ViewHolder holder, int position) {


        Merchant merchant=merchantList.get(position);
        holder.merchantName.setText(merchant.getName());
        holder.price.setText(Double.toString(merchant.getPrice()));

        holder.rootView.setOnClickListener(v -> {
            // write funcitonality to change the price based on merchant selection
            Context context = v.getContext();
            TextView txtView = (TextView) ((Activity)context).findViewById(R.id.tv_productPrice);
            txtView.setText(Double.toString(merchant.getPrice()));

        });
    }

    @Override
    public int getItemCount() {
        return this.merchantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView merchantName;
        private TextView price;
        private View rootView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView=itemView;
            merchantName=itemView.findViewById(R.id.tv_merchantName);

            price=itemView.findViewById(R.id.merchantPrice);
        }
    }
}


