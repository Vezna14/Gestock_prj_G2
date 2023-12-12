package be.heh.g2.adapter.web;

import be.heh.g2.application.domain.model.Order;
import be.heh.g2.application.port.in.OrderManaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resto/orders")
public class OrderController {
    private final OrderManaUseCase orderUseCase;

    public OrderController(OrderManaUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        System.out.println("orderControler: " + order);
        try {
            orderUseCase.makeSaveOrder(order);
            return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pay/{idTable}")
    public String payOrder(@PathVariable int idTable) {
        return orderUseCase.makePayOrder(idTable);
    }

    @GetMapping("/show/{idTable}")
    public Order showOrder(@PathVariable int idTable) {
        return orderUseCase.makeShowOrder(idTable);
    }
}
