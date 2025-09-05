package com.sinse.electroshop.model.store;

import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.exception.StoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    private final StoreDAO storeDAO;

    @Override
    public Store regist(Store store) {
        return storeDAO.save(store);
    }

    @Override
    public Store login(Store store) throws StoreNotFoundException {
        Store obj=storeDAO.login(store);

        if(obj==null){
            throw new StoreNotFoundException("상점 로그인 정보가 올바르지 않습니다");
        }
        return obj;
    }


}
