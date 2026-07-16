package com.cursodevsuperior.dscommerce.services;

import com.cursodevsuperior.dscommerce.dto.ProductDTO;
import com.cursodevsuperior.dscommerce.entities.Product;
import com.cursodevsuperior.dscommerce.repositories.ProductRepository;
import com.cursodevsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService { // essa camada devolve um DTO

    @Autowired
    private ProductRepository Repository;

    @Transactional(readOnly = true ) //  um atalho poderoso para não precisar abrir/fechar transações manualmente, garantindo consistência e simplificando o código. readyonly garante que vai ser só leitura!
    public ProductDTO findById(long id) {
        Product product = Repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true )
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = Repository.findAll(pageable); // vai no BD e busca todos os produtos
        return result.map(x -> new ProductDTO(x)); // lambda para cada elemento x da lista, crie um novo ProductDTO usando esse x
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) { // insere dados no BD

        Product entity = new Product(); //instancia
        copyDtoToEntity(dto, entity); // copia
        entity = Repository.save(entity); // salva

        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(long id, ProductDTO dto) { // insere dados no BD
        try {
            Product entity = Repository.getReferenceById(id); // instacia com a referencia
            copyDtoToEntity(dto, entity); // instancia
            entity = Repository.save(entity); // salva
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {}
           throw new ResourceNotFoundException("Product not found");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!Repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            Repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
    }



}
