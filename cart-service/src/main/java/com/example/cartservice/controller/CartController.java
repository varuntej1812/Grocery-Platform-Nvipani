package com.example.cartservice.controller;

import com.example.cartservice.model.CartItem;
import com.example.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public CartItem addOrUpdate(@RequestBody CartItem item) {
        return cartService.addOrUpdateItem(item);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        return cartService.getCart(userId);
    }

    @DeleteMapping("/remove/{id}")
    public void removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
    }
}
