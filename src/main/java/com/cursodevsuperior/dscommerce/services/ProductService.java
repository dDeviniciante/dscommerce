package com.cursodevsuperior.dscommerce.services;

import com.cursodevsuperior.dscommerce.dto.ProductDTO;
import com.cursodevsuperior.dscommerce.entities.Product;
import com.cursodevsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService { // essa camada devolve um DTO

    @Autowired
    private ProductRepository Repository;

    @Transactional(readOnly = true ) //  um atalho poderoso para não precisar abrir/fechar transações manualmente, garantindo consistência e simplificando o código. readyonly garante que vai ser só leitura!
    public ProductDTO findById(long id) {
        Product product = Repository.findById(id).get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true )
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = Repository.findAll(pageable); // vai no BD e busca todos os produtos
        return result.map(x -> new ProductDTO(x)); // lambda para cada elemento x da lista, crie um novo ProductDTO usando esse x
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) { // insere dados no BD

        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity = Repository.save(entity);

        return new ProductDTO(entity);
    }

}
