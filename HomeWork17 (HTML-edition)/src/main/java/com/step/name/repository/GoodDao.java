package com.step.name.repository;

import com.step.name.model.Good;

import java.util.List;
import java.util.Optional;

public interface GoodDao {
    Optional<Good> getByTittle(String tittle);

    Optional<Good> getById(int id);

    List<Good> getAll();

}
