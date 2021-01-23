package com.example.shlishli.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shlishli.R;
import com.example.shlishli.dataModels.OrderItem;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder>{

    private List<OrderItem> orderItems;

    public MyOrderAdapter(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item, parent, false);
        return new MyOrderAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderItem orderItem = orderItems.get(position);

        holder.productDate.setText(orderItem.getOrderedOn() + "");
        holder.productPrice.setText(orderItem.getStatus());
        holder.productName.setText("Qty : " + orderItem.getQuantity() + " | order Id:" + orderItem.getOrderId());

    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private TextView productName;
        private TextView productPrice;
        private TextView productDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            productName = itemView.findViewById(R.id.tv_myorders_product_name);
            productPrice = itemView.findViewById(R.id.tv_myorders_product_price);
            productDate = itemView.findViewById(R.id.tv_myorders_date);

        }
    }
}
