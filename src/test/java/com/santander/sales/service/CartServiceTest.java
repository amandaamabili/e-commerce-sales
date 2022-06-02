package com.santander.sales.service;

import com.santander.sales.dto.CartDTO;
import com.santander.sales.dto.ProductDTO;
import com.santander.sales.exception.UserCartNotFoundException;
import com.santander.sales.exception.UserHasAlreadyCartException;
import com.santander.sales.model.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class CartServiceTest {
//
//    @Mock
//    private CartRepository cartRepository;
//
//    @Test
//    void create_firstTime_ok() {
//        String userID = "ASA@#@";
//        Cart cart = new Cart(userID);
//        when(cartRepository.findByUserID(userID)).thenReturn(Optional.empty());
//        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
//
//        CartService cartService = new CartService(cartRepository);
//        CartDTO dto = cartService.create(userID);
//
//        Assertions.assertNotNull(dto);
//        Assertions.assertTrue(dto.getProductMap().isEmpty());
//        Assertions.assertEquals(0.0, dto.getTotalPrice());
//        Assertions.assertNotNull(dto);
//    }
//
//    @Test
//    void create_userHasAlreadyCart_shouldThrownException() {
//        String userID = "ASA@#@";
//        Cart cart = new Cart(userID);
//        when(cartRepository.findByUserID(userID)).thenReturn(Optional.of(cart));
//
//        CartService cartService = new CartService(cartRepository);
//        UserHasAlreadyCartException userHasAlreadyCartException =
//                assertThrows(UserHasAlreadyCartException.class, () -> cartService.create(userID));
//
//        verify(cartRepository, never()).save(any(Cart.class));
//        Assertions.assertEquals("User has already cart", userHasAlreadyCartException.getMessage());
//    }
//
//    @Test
//    void update_userHasCart_ok() {
//        String userID = "ASA@#@";
//        Cart cart = new Cart(userID);
//        ProductDTO p1 = new ProductDTO(1L, "product 1", 200.0, 2L);
//
//        when(cartRepository.findByUserID(userID)).thenReturn(Optional.of(cart));
//        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
//
//        CartService cartService = new CartService(cartRepository);
//        cartService.update(userID, p1);
//
//        verify(cartRepository).save(argThat(argument -> {
//            Assertions.assertFalse(argument.getProductMap().isEmpty());
//            Assertions.assertEquals(1,argument.getProductMap().size());
//            Assertions.assertEquals(200.0, argument.getProductMap().get(1L).getPrice());
//            Assertions.assertEquals(2, argument.getProductMap().get(1L).getAmount());
//            Assertions.assertEquals("product 1", argument.getProductMap().get(1L).getName());
//            Assertions.assertEquals(400.0, argument.getTotalPrice());
//            return true;
//        }));
//    }
//
//    @Test
//    void update_userReplaceProduct_ok() {
//        String userID = "ASA@#@";
//        Cart cart = new Cart(userID);
//        ProductDTO previous = new ProductDTO(1L, "product 1", 200.0, 2L);
//        ProductDTO current = new ProductDTO(1L, "product 1", 400.0, 3L);
//
//        cart.updateProductList(previous);
//
//
//        when(cartRepository.findByUserID(userID)).thenReturn(Optional.of(cart));
//        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
//
//        CartService cartService = new CartService(cartRepository);
//        cartService.update(userID, current);
//
//        verify(cartRepository).save(argThat(argument -> {
//            Assertions.assertFalse(argument.getProductMap().isEmpty());
//            Assertions.assertEquals(1,argument.getProductMap().size());
//            Assertions.assertEquals(400, argument.getProductMap().get(1L).getPrice());
//            Assertions.assertEquals(3, argument.getProductMap().get(1L).getAmount());
//            Assertions.assertEquals("product 1", argument.getProductMap().get(1L).getName());
//            Assertions.assertEquals(1200.0, argument.getTotalPrice());
//
//            return true;
//        }));
//    }
//
//    @Test
//    void update_userHasNotCart_shouldThrownException() {
//        String userID = "ASESD2323";
//        ProductDTO p1 = new ProductDTO(1L, "product 1", 200.0, 2L);
//        when(cartRepository.findByUserID(userID)).thenReturn(Optional.empty());
//
//        CartService cartService = new CartService(cartRepository);
//        Assertions.assertThrows(UserCartNotFoundException.class, () -> cartService.update(userID, p1));
//
//        verify(cartRepository, never()).save(any(Cart.class));
//    }
//
//    @Test
//    void get() {
//        String userID = "ASAsASSD#@";
//        String cartID = "ASA@#@";
//        Cart cart = new Cart(userID);
//        cart.set_id(cartID);
//        when(cartRepository.findById(cartID)).thenReturn(Optional.of(cart));
//
//        CartService cartService = new CartService(cartRepository);
//        CartDTO cartDTO = cartService.get(cartID);
//
//        Assertions.assertTrue(cartDTO.getProductMap().isEmpty());
//        Assertions.assertTrue(cartDTO.getProductMap().isEmpty());
//    }
//}