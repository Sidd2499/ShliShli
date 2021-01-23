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
import com.example.shlishli.dataModels.MerchantDetails;
import com.example.shlishli.dataModels.Search;

import org.w3c.dom.Text;

import java.util.List;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder>{




    List<MerchantDetails> merchantList;


    public MerchantAdapter(List<MerchantDetails> merchantList){
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


        MerchantDetails merchant=merchantList.get(position);

        String merchantName = merchant.getMerchantName();
        String price = String.valueOf(merchant.getPrice());


        holder.merchantName.setText(merchantName);
        holder.price.setText("₹"+price);




        holder.rootView.setOnClickListener(v -> {
            // write funcitonality to change the price based on merchant sel ection
            Context context = v.getContext();
            TextView txtView = (TextView) ((Activity)context).findViewById(R.id.tv_product_main_price);
            txtView.setText(Double.toString(merchant.getPrice()));
            TextView inventoryId = (TextView)((Activity)context).findViewById(R.id.tv_inventory_id);
            inventoryId.setText(Integer.toString(merchant.getInventoryItemId()));
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


