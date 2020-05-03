package com.mao.service;

import com.mao.mapper.db1.TempRepository1;
import com.mao.mapper.db2.TempRepository2;
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
    private TempRepository1 tempRepository1;

    @Autowired
    private TempRepository2 tempRepository2;

    public List<Temp> findAll() {
        return tempRepository1.findAll();
    }

    public void addTempSecond(Temp temp) {
        tempRepository1.save(temp);
    }

    public void addTempThird(Temp temp) {
        tempRepository2.save(temp);
    }

    public void deleteTempSecond(Temp temp) {
        tempRepository1.delete(temp);
    }

    public void deleteTempThird(Temp temp) {
        tempRepository2.delete(temp);
    }

    public List<Temp> findAllSecond() {
        return tempRepository1.findAll();
    }

    public List<Temp> findAllThird() {
        return tempRepository2.findAll();
    }

}
