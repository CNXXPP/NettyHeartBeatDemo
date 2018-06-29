package heartBeatDemo;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class ChannelMap {
    public static Map<String,io.netty.channel.Channel> channelMap = new HashMap<>();
//    public static  Jedis jedis = new Jedis("127.0.0.1",6379);
//    public static void initRedis(){
//        System.out.println(jedis.ping());
//    }
}
