package spring.elastic.job.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ElasticSimpleJob implements SimpleJob {

    Logger logger = LoggerFactory.getLogger(ElasticSimpleJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        Date date = new Date();
        logger.info(String.format("线程: %s, 作业分片总数: %s,当前分片项: %s，时间: %s",
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(date)
        ));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(shardingContext.getShardingItem() + " 执行完了");
    }
}
