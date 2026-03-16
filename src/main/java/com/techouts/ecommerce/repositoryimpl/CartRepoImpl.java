package com.techouts.ecommerce.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import com.techouts.ecommerce.repository.CartRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.techouts.ecommerce.model.Cart;
import com.techouts.ecommerce.model.CartItem;
import com.techouts.ecommerce.model.Product;
import com.techouts.ecommerce.model.User;

@Repository
public class CartRepoImpl implements CartRepo {

    private final SessionFactory sessionFactory;

    CartRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Cart getCart(User user) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Cart WHERE user = :user", Cart.class)
                .setParameter("user", user).getSingleResult();

    }

    public CartItem getCartItem(int cartItemId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(CartItem.class, cartItemId);

    }

    public List<CartItem> getCartItems(Cart cart) {

        Session session = sessionFactory.getCurrentSession();

        Cart userCart = session.get(Cart.class, cart.getId());

        List<CartItem> cartItems = userCart.getCartItemList();

        return cartItems;

    }

    public void saveItemToCart(CartItem cartItem) {

        Session session = sessionFactory.getCurrentSession();

        session.persist(cartItem);

    }

    public void updateItemInCart(CartItem cartItem) {

        Session session = sessionFactory.getCurrentSession();

        session.merge(cartItem);

    }

    public void removeItemFromCart(CartItem cartItem) {

        Session session = sessionFactory.getCurrentSession();

        cartItem.getCartId().getCartItemList().remove(cartItem);

        session.remove(cartItem);

    }

    public List<CartItem> clearCart(Cart cart) {

        List<CartItem> cartItems = new ArrayList<>(getCartItems(cart));
     
            
        for(CartItem cartItem : cartItems) {

            removeItemFromCart (cartItem);

        }

        return cartItems;
        
    }

    public CartItem findCartItem(Cart cart, Product product) {

        List<CartItem> cartItems = getCartItems(cart);

        return cartItems.stream().filter((item) -> item.getProductId().getId() == product.getId()).findAny()
                .orElse(null);

    }

}
