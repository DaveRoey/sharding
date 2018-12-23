package com.dave.sharding.config;

import com.dave.sharding.custom.algorithm.PartitionByMurmurHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Dave on 2018/12/20
 * Describes 算法初始化
 */
@Configuration
public class MurmurHashConfig {

    @Value("${murmur.node}")
    private int nodeSize;

    @Bean
    public PartitionByMurmurHash  getMurmurHash(){
        PartitionByMurmurHash murmurHash = new PartitionByMurmurHash();
        murmurHash.setCount(nodeSize);
        murmurHash.init();
        return murmurHash;
    }

}
