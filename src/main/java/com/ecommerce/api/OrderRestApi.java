package com.ecommerce.api;

import com.ecommerce.api.dto.OrderCreateRequestDTO;
import com.ecommerce.api.dto.OrderResponseDTO;
import com.ecommerce.api.dto.TotalSalesDTO;
import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderRestApi {

    @Autowired
    private OrderService orderService;

    @GetMapping("/total-sales")
    public TotalSalesDTO totalSales(){
        return TotalSalesDTO.fromTotalSalesDTO(orderService.totalSales());
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderCreateRequestDTO requestDTO){
        Order orderFromApi = orderService.createOrderFromApi(requestDTO);
        OrderResponseDTO responseDTO = OrderResponseDTO.fromOrder(orderFromApi);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
