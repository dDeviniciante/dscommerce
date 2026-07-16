package com.cursodevsuperior.dscommerce.controllers;

import com.cursodevsuperior.dscommerce.dto.ProductDTO;
import com.cursodevsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController// Marca a classe como um controlador REST.
@RequestMapping(value = "/products")
public class ProductController { //recurso é o conceito, controlador é a forma de implementar esse conceito.

    @Autowired // O Spring cria e injeta o ProductRepository.
    private ProductService service;

    @GetMapping(value = "/{id}" ) // "Quando chegar uma requisição GET para esta classe, execute este metodo
    public ResponseEntity<ProductDTO> findById (@PathVariable long id) { //@Pathvariable configura o parametro com {id}
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) { // Page<ProductDTO> → retorna dados paginados e convertidos para DTO. ele é tipo uma lista.
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) { // O @RequestBody diz ao Spring que os dados enviados no corpo da requisição (em JSON, por exemplo) devem ser convertidos para um objeto ProductDTO
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
