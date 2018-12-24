package com.dave.sharding.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */

@Data
@Document(indexName = "emp",type = "emp",shards = 5,replicas = 0)
public class Employee {
    @Id
    private Long id;
    @Field
    private String code;
    @Field
    private Date createTime;

}
