package com.techouts.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techouts.ecommerce.model.Cart;
import com.techouts.ecommerce.model.CartItem;
import com.techouts.ecommerce.model.Product;
import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.repositoryimpl.CartRepoImpl;
import com.techouts.ecommerce.repositoryimpl.ProductRepoImpl;
import com.techouts.ecommerce.repositoryimpl.UserRepoImpl;

@Service
public class CartService {

    private CartRepoImpl cartRepoImpl;
    private ProductRepoImpl productRepoImpl;
    private UserRepoImpl userRepoImpl;

    CartService(CartRepoImpl cartRepoImpl, ProductRepoImpl productRepoImpl, UserRepoImpl userRepoImpl) {
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

        System.out.println("CALCULATED TOTAL PRICE: " + result);
        return result;

    }

    @Transactional
    public void addToCart(User user, int productId) {

        User managedUser = userRepoImpl.getById(user.getId()).orElse(null);

        Cart userCart = managedUser.getCart();

        Product product = productRepoImpl.getById(productId);

        CartItem cartItemInQuestion = cartRepoImpl.findCartItem(userCart, product);

        if (cartItemInQuestion == null) {

            cartRepoImpl.saveItemToCart(new CartItem(userCart, product, 1));
            return;

        }

        cartItemInQuestion.setQuantity(cartItemInQuestion.getQuantity() + 1);

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
        } else if (!isAddition) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);

            if (cartItem.getQuantity() <= 0) {
                removeFromCart(cartItemId);
                return;
            }
        }

        cartRepoImpl.updateItemInCart(cartItem);

    }

}
