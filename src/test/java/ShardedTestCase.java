import org.junit.Test;
import org.junit.runner.RunWith;
import org.sniff.cache.template.CacheTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @auth snifferhu
 * @date 2017/4/18 21:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
/** 声明spring主配置文件位置，注意：以当前测试类的位置为基准,有多个配置文件以字符数组声明 **/
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class ShardedTestCase {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    @Autowired
    private CacheTemplate template;

    @Test
    public void test(){
        ShardedJedis jedis = shardedJedisPool.getResource();
        for (int i = 0;i < 100;i++)
            jedis.set(String.valueOf(i),String.valueOf(i));
    }

    @Test
    public void delTest(){
        ShardedJedis jedis = shardedJedisPool.getResource();
        for (int i = 0;i < 100;i++)
            jedis.del(String.valueOf(i));
    }

    @Test
    public void mapTest(){
        template.getMapOperation("map").put("a",1);
        System.out.println(template.getMapOperation("map").get("a"));
        shardedJedisPool.getResource().del("map");
    }
}
