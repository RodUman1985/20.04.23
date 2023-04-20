package com.step.name.service.impl;


import com.step.name.repository.GoodDao;
import com.step.name.model.Good;
import com.step.name.service.GoodService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService {
    private final GoodDao goodDao ;

    public GoodServiceImpl(GoodDao goodDao) {

        this.goodDao = goodDao;
    }


    @Override
    public Optional<Good> getByTittle(String tittle) {
        return goodDao.getByTittle(tittle);
    }

    @Override
    public Optional<Good> getById(int id) {
        return goodDao.getById(id);
    }

    @Override
    public List<Good> getGoods() {
        return goodDao.getAll();
    }

    @Override
    public List<String> printGoods() {
        List<Good> list = goodDao.getAll();
        List<String> listOfGoods=new ArrayList<>();
        for (Good good: list){
            listOfGoods.add(good.getTitle()+" "+good.getPrice()+"$");
        }
        return listOfGoods;
    }

}
