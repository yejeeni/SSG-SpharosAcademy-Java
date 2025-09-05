package com.sinse.electroshop.model.store;

import com.sinse.electroshop.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStoreRepository extends JpaRepository<Store, Integer> {
    public Store findByBusinessIdAndPassword(String businessId, String password);
}
