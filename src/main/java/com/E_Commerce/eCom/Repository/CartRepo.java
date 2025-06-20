package com.E_Commerce.eCom.Repository;

import com.E_Commerce.eCom.Model.Cart;
import com.E_Commerce.eCom.Model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.username = ?1")
    Cart findByUsername(String username);


    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems ci JOIN FETCH ci.product WHERE c.user.username = ?1")
    Cart getCartWithProducts(String username);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c = ?1")
    void deleteCartByCart(Cart cart);

    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems ci JOIN FETCH ci.product  p WHERE p.productId = ?1")
    List<Cart> findByProductId(Long productId);
}
