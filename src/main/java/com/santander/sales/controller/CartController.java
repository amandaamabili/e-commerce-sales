package com.santander.sales.controller;


import com.santander.sales.dto.CartDTO;
import com.santander.sales.dto.ProductDTO;
import com.santander.sales.exception.UserCartNotFoundException;
import com.santander.sales.service.CartServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    private final CartServiceInterface cartService;

    public CartController(CartServiceInterface cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    ResponseEntity<CartDTO> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(cartService.get(id));
        } catch (UserCartNotFoundException cartNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user/{userID}")
    ResponseEntity<Object> save(@PathVariable("userID") String userID) {
        try {
            return ResponseEntity.ok(cartService.create(userID));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/user/{userID}")
    ResponseEntity<Object> update(@PathVariable String userID, @RequestBody ProductDTO cartDTO) {
        try {
            return ResponseEntity.ok(cartService.update(userID, cartDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/user/{userID}")
    ResponseEntity<Object> delete(@PathVariable String userID) {
        try {
            return ResponseEntity.ok(cartService.delete(userID));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}