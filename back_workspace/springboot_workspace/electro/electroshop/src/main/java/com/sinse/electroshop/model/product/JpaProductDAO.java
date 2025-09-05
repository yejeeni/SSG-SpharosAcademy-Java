package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class JpaProductDAO implements ProductDAO {

    private final JpaProductRepository repository;

    @Override
    public List selectAll() {
        return repository.findAll();
    }

    @Override
    public Product selectById(int productId) {
        return repository.findById(productId);
    }

    @Override
    public List<Product> selectByStoreId(int storeId) {
        return repository.findByStore_storeId(storeId);
    }

    @Override
    public Product regist(Product product) {
        return repository.save(product);
    }


}
