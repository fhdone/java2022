package com.fhdone.java2022.august.job;


import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringBootJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("SpringBootJob作业,分片总数是【{}】,当前分片是【{}】,分片参数是【{}】",
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter());

        switch (shardingContext.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                log.info("do item 0");
                break;
            case 1:
                // do something by sharding item 1
                log.info("do item 1");
                break;
            case 2:
                // do something by sharding item 2
                log.info("do item 2");
                break;
            // case n: ...
            default:
                //ignore
                break;

        }

    }
}
