package com.dave.sharding.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */

@Data
public class Employee {
    private Long id;
    private String code;
    private Date createTime;

}
