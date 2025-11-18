package com.example.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dtos.ProductDTO;
import com.example.auth.services.ProductService;

@RestController()
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> postProduct(@RequestBody ProductDTO body){
        body = service.register(body);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(){
       return ResponseEntity.ok().body(service.getAll());
    }
}
