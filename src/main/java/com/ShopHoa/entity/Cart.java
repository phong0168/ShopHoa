package com.ShopHoa.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {


    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    List<CartItem> cartItemList = new ArrayList<CartItem>();
    public void addCartItem(CartItem cartItem){
        for (CartItem item : cartItemList){
            if (item.getFlower().getId() == cartItem.getFlower().getId()){
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        cartItemList.add(cartItem);
    }

    public void removeCartItem(int id){
           for (CartItem item : cartItemList){
                if (item.getFlower().getId() == id){
                    cartItemList.remove(item);
                    return;
                }
            }
    }

    public int getTotalPrice(){
        int total = 0;
        for (CartItem item : cartItemList){
            total += item.getFlower().getPrice() * item.getQuantity();
        }
        return total;
    }

    public int getTotalQuantity(){
        int total = 0;
        for (CartItem item : cartItemList){
            total += item.getQuantity();
        }
        return total;
    }

    public void updateCartItem(int id, int quantity){
        for (CartItem item : cartItemList){
            if (item.getFlower().getId() == id){
                item.setQuantity(quantity);
                return;
            }
        }
    }

}


