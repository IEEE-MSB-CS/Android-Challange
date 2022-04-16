package com.hamza.ieeechallenge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.roomDatabase.entities.Cart;
import com.hamza.ieeechallenge.ui.cart.CartViewModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<Cart> cartList;
    Context context;
    CartViewModel cartViewModel;
    final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public CartAdapter( Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setViewBinderHelper(holder , position);

        setDataToRecyclerView(holder , position);

        holder.minus.setOnClickListener(view -> decreaseQuantity(holder , position));

        holder.plus.setOnClickListener(view -> increaseQuantity(holder , position));

        holder.delete.setOnClickListener(view-> deleteCartItem(position));

    }

    private void setViewBinderHelper(ViewHolder holder, int position) {
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.swipeRevealLayout , String.valueOf(cartList.get(position).getOrderId()));
        viewBinderHelper.closeLayout(String.valueOf(cartList.get(position).getOrderId()));
    }

    @Override
    public int getItemCount() {
        return cartList == null? 0 : cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price,quantity,restaurantName, delete;
        ImageButton plus , minus;
        SwipeRevealLayout swipeRevealLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.cart_image);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.price_Cart);
            quantity = itemView.findViewById(R.id.tv_quantity);
            restaurantName = itemView.findViewById(R.id.tv_restaurant_name);
            plus = itemView.findViewById(R.id.btn_plus);
            minus = itemView.findViewById(R.id.btn_minus);
            delete = itemView.findViewById(R.id.tv_delete);
            swipeRevealLayout = itemView.findViewById(R.id.swipe_layout);
        }
    }

    public void setData(List<Cart> cartList , CartViewModel cartViewModel){
        this.cartList = cartList;
        this.cartViewModel = cartViewModel;
        notifyDataSetChanged();
    }
    void updateCartItem(int position , int quantity){
        Cart cart = new Cart(cartList.get(position).getOrderId() , cartList.get(position).getUserId() ,
                cartList.get(position).getStatus() , cartList.get(position).getTitle() , cartList.get(position).getImage() ,
                cartList.get(position).getRestaurantName() , quantity , cartList.get(position).getPrice());
        cartViewModel.updateCartItem(cart);
    }

    void deleteCartItem(int position){
        Cart cart = new Cart(cartList.get(position).getOrderId() , cartList.get(position).getUserId() ,
                cartList.get(position).getStatus() , cartList.get(position).getTitle() , cartList.get(position).getImage() ,
                cartList.get(position).getRestaurantName() , cartList.get(position).getQuantity() ,
                cartList.get(position).getPrice());
        cartViewModel.deleteCartItem(cart);
    }


    private void setDataToRecyclerView(ViewHolder holder, int position) {
        Glide.with(context)
                .load(cartList.get(position).getImage())
                .into(holder.imageView);
        holder.name.setText(cartList.get(position).getTitle());
        holder.price.setText(String.valueOf(cartList.get(position).getPrice()));
        holder.restaurantName.setText(cartList.get(position).getRestaurantName());
        holder.quantity.setText(String.valueOf(cartList.get(position).getQuantity()));
    }

    private void decreaseQuantity(ViewHolder holder, int position) {
        int quantity = Integer.parseInt(holder.quantity.getText().toString());
        if (quantity>1){
            quantity--;
            holder.quantity.setText(String.valueOf(quantity));
            updateCartItem(position , quantity);
        }
    }

    private void increaseQuantity(ViewHolder holder, int position) {
        int quantity = Integer.parseInt(holder.quantity.getText().toString());
        quantity++;
        holder.quantity.setText(String.valueOf(quantity));
        updateCartItem(position , quantity);
    }

    public List<Cart> getCartList(){
        return cartList;
    }
}
