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
    }
}
