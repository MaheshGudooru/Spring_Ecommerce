package com.techouts.ecommerce.repository;

import com.techouts.ecommerce.model.Cart;
import com.techouts.ecommerce.model.CartItem;
import com.techouts.ecommerce.model.Product;
import com.techouts.ecommerce.model.User;

import java.util.List;

public interface CartRepo {

    Cart getCart(User user);

    CartItem getCartItem(int cartItemId);

    List<CartItem> getCartItems(Cart cart) ;

    void saveItemToCart(CartItem cartItem);

    void updateItemInCart(CartItem cartItem);

    void removeItemFromCart(CartItem cartItem);

    List<CartItem> clearCart(Cart cart);

    CartItem findCartItem(Cart cart, Product product);
}
