package com.mao.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bigdope
 * @create 2020-01-13
 **/
@Accessors(chain = true)
@Data
@Entity
@Table(name = "temp", schema = "", catalog = "")
public class Temp implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "ID",unique = true)
    @ApiModelProperty(value = "主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "NAME")
    @ApiModelProperty(value = "地区名称")
    private String name;
}
