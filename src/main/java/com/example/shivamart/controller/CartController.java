  package com.example.shivamart.controller;

  import com.example.shivamart.dto.AddToCartRequest;
  import com.example.shivamart.entity.Cart;
  import com.example.shivamart.service.CartService;

  import lombok.RequiredArgsConstructor;

  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;
  import org.springframework.security.access.prepost.PreAuthorize;



  @RequiredArgsConstructor
  @RestController
  @RequestMapping("/api/cart")

  public class CartController { 

    private final CartService cartService;

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @PostMapping("/add/{userId}")
    public ResponseEntity<Cart> addToCart(
      @PathVariable Long userId,
      @RequestBody AddToCartRequest request){

        Cart cart = cartService.addToCart(
          userId,
          request.getProductId(),
          request.getQuantity()
        );

        return ResponseEntity.ok(cart);

      }

      @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
      @GetMapping("/{userId}")
      public ResponseEntity<Cart> getCart(
        @PathVariable Long userId){
          return ResponseEntity.ok(
            cartService.getCart(userId)
          );
        }

      @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
      @DeleteMapping("/item/{ItemId}")
      public ResponseEntity<String> removeFromCart(
        @PathVariable Long ItemId){
          cartService.removeFromCart(ItemId);

          return ResponseEntity.ok("item removed successfully") ;

        } 

      @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
      @DeleteMapping("/clear/{userId}")
      public ResponseEntity<String> clearCart(
        @PathVariable Long userId){
          cartService.clearCart(userId);

          return ResponseEntity.ok("cart cleared successfully");
        }

  }
