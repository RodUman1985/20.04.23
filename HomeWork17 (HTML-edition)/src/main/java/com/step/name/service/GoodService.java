package com.step.name.service;

import com.step.name.model.Good;

import java.util.List;
import java.util.Optional;

public interface GoodService {

    Optional<Good> getByTittle(String tittle);

    Optional<Good> getById(int id);

    List<Good> getGoods();
    List<String> printGoods();
}
