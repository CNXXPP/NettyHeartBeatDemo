package clientSend;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class ChannelMap {

    private static Integer channelCount = 0;
    public static Map<String,Channel> channelMap = new HashMap<>();

    public static void addChannel(Channel channel,String idendity){
        channelMap.put(idendity,channel);
        channel.writeAndFlush("你已被加入channelMap");
        channelCount++;
    }

    public static Channel getChannel(String id){
        return channelMap.get(id);
    }

}
