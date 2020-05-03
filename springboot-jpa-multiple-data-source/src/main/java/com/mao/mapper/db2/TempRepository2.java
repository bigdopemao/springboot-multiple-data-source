package com.mao.mapper.db2;

import com.mao.model.Temp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bigdope
 * @create 2020-01-13
 **/
@Repository
public interface TempRepository2 extends JpaRepository<Temp, Long> {
}
