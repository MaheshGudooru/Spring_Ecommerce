package com.techouts.ecommerce.service;

import java.util.List;

import com.techouts.ecommerce.repository.CartRepo;
import com.techouts.ecommerce.repository.ProductRepo;
import com.techouts.ecommerce.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techouts.ecommerce.model.Cart;
import com.techouts.ecommerce.model.CartItem;
import com.techouts.ecommerce.model.Product;
import com.techouts.ecommerce.model.User;


@Service
public class CartService {

    private CartRepo cartRepoImpl;
    private ProductRepo productRepoImpl;
    private UserRepo userRepoImpl;

    CartService(CartRepo cartRepoImpl, ProductRepo productRepoImpl, UserRepo userRepoImpl) {
        this.cartRepoImpl = cartRepoImpl;
        this.productRepoImpl = productRepoImpl;
        this.userRepoImpl = userRepoImpl;
    }

    @Transactional(readOnly = true)
    public List<CartItem> getCartItemsByUser(User user) {

        User managedUser = userRepoImpl.getById(user.getId()).orElse(null);

        Cart userCart = managedUser.getCart();

        return userCart.getCartItemList();

    }

    public float calculateTotalCartPrice(List<CartItem> userCartItems) {

        float result = 0;

        for (CartItem cartItem : userCartItems) {

            int productQuantity = cartItem.getQuantity();
            float productPrice = cartItem.getProductId().getPrice();

            result = result + (productPrice * productQuantity);

        }

        return result;

    }

    @Transactional
    public void addToCart(User user, int productId, int quantity) throws Exception {

        User managedUser = userRepoImpl.getById(user.getId()).orElse(null);

        Cart userCart = managedUser.getCart();

        if(userCart == null) {
            throw new Exception ("User cart not found at CartService");
        }

        Product product = productRepoImpl.getById(productId);

        CartItem cartItemInQuestion = cartRepoImpl.findCartItem(userCart, product);

        if (cartItemInQuestion == null) {

            cartRepoImpl.saveItemToCart(new CartItem(userCart, product, quantity));
            return;

        }

        cartItemInQuestion.setQuantity(cartItemInQuestion.getQuantity() + quantity);

        cartRepoImpl.updateItemInCart(cartItemInQuestion);

    }

    @Transactional
    public void removeFromCart(int cartItemId) {

        CartItem cartItemInQuestion = cartRepoImpl.getCartItem(cartItemId);

        if (cartItemInQuestion == null) {
            return;
        }

        cartRepoImpl.removeItemFromCart(cartItemInQuestion);

    }

    @Transactional
    public void changeProductQuantity(int cartItemId, boolean isAddition) {

        CartItem cartItem = cartRepoImpl.getCartItem(cartItemId);

        if (isAddition) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);

            if (cartItem.getQuantity() <= 0) {
                removeFromCart(cartItemId);
                return;
            }
        }

        cartRepoImpl.updateItemInCart(cartItem);

    }

}
