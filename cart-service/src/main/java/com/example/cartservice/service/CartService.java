package com.example.cartservice.service;

import com.example.cartservice.model.CartItem;
import com.example.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public CartItem addOrUpdateItem(CartItem item) {
        return cartRepository.save(item);
    }

    public List<CartItem> getCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public void removeItem(Long id) {
        cartRepository.deleteById(id);
    }

    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }
}
