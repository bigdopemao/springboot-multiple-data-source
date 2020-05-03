package com.mao;

import com.mao.config.DruidProperties;
import com.mao.config.MultiDataSource;
import com.mao.model.Temp;
import com.mao.service.TempServiceDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bigdope
 * @create 2020-01-13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaAopMultipleDataSourceApplication.class)
@Slf4j
public class MultiDataSourceTest {

    @Resource
    private TempServiceDemo tempServiceDemo;

    @Autowired
    private MultiDataSource multiDataSource;

    @Test
    public void testMultiDataSource() {
        // 查看配置信息
        List<DruidProperties> datasource = multiDataSource.getDatasource();
        System.out.println(datasource);
        for (int i = 0; i < datasource.size(); i++) {
            System.out.println("第" + (i+1) + "个: " + datasource.get(i));
        }


        Temp temp1 = new Temp();
        temp1.setName("哈哈");
        tempServiceDemo.addTempSecond(temp1);
        Temp temp2 = new Temp();
        temp2.setName("呵呵");
        tempServiceDemo.addTempThird(temp2);
        System.out.println("插入成功");

        System.out.println("\r\n=================\r\n");
        System.out.println(tempServiceDemo.findAllSecond());
        System.out.println("\r\n=================\r\n");
        System.out.println( tempServiceDemo.findAllThird());
        System.out.println("\r\n=================\r\n");

        temp1.setId(1L);
        tempServiceDemo.deleteTempSecond(temp1);
        temp2.setId(1L);
        tempServiceDemo.deleteTempThird(temp2);
    }

}
