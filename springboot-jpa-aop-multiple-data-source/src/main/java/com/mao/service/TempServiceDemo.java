package com.mao.service;

import com.mao.config.annotation.MultiDataSource;
import com.mao.mapper.TempRepository;
import com.mao.model.Temp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bigdope
 * @create 2020-01-13
 **/
@Service
@Transactional
public class TempServiceDemo {

    @Autowired
    private TempRepository tempRepository;

    public List<Temp> findAll() {
        return tempRepository.findAll();
    }

    @MultiDataSource(name = "second")
    public void addTempSecond(Temp temp) {
        tempRepository.save(temp);
    }

    @MultiDataSource(name = "third")
    public void addTempThird(Temp temp) {
        tempRepository.save(temp);
    }

    public void deleteTempSecond(Temp temp) {
        tempRepository.delete(temp);
    }

    @MultiDataSource(name = "third")
    public void deleteTempThird(Temp temp) {
        tempRepository.delete(temp);
    }

    @MultiDataSource(name = "second")
    public List<Temp> findAllSecond() {
        return tempRepository.findAll();
    }

    @MultiDataSource(name = "third")
    public List<Temp> findAllThird() {
        return tempRepository.findAll();
    }

}
