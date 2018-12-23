package com.dave.sharding.custom.route;

import com.dave.sharding.custom.algorithm.PartitionByMurmurHash;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Dave on 2018/12/20
 * Describes
 * @author Dave
 */
@Component
public class CommonDataBaseStrategy implements PreciseShardingAlgorithm<String> {
    @Autowired
    private PartitionByMurmurHash murmurHash;


    private final Logger log= LoggerFactory.getLogger(CommonDataBaseStrategy.class);

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        log.info("collection:{},shardingValue:{}",collection,preciseShardingValue);

        String value=preciseShardingValue.getValue();
        Integer suffix = murmurHash.calculate(value);

        for (String strategy : collection) {

            if(strategy.endsWith(String.valueOf(suffix))){
                return strategy;
            }
        }
        throw new UnsupportedOperationException("can not find route");
    }
}
