package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;

import java.util.List;

public interface ProductService {
    public List getList();
    public List getListByStoreId(int storeId);
    public Product getDetail(int productId);
    public Product save(Product product);
}
