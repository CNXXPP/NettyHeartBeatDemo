package heartBeatDemo;
  
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;  
import io.netty.handler.timeout.IdleState;  
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {  


    private int loss_connect_time = 0;  
  
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {  
        if (evt instanceof IdleStateEvent) {  
            IdleStateEvent event = (IdleStateEvent) evt;  
            if (event.state() == IdleState.READER_IDLE) {  
                loss_connect_time++;  
                System.out.println("5 秒没有接收到客户端的信息了");
                if (loss_connect_time > 2) {  
                    System.out.println("关闭这个不活跃的channel");
                    ctx.channel().writeAndFlush(new NettyMsg(0x03,"跪安吧"));
                    ChannelMap.channelMap.get("0527").writeAndFlush(new NettyMsg(0x03,"跪安吧2"));
                    ChannelMap.channelMap.remove("0527");
                    ctx.channel().close();
                }  
            }  
        } else {  
            super.userEventTriggered(ctx, evt);  
        }  
    }  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMsg nettyMsg = (NettyMsg)msg;
        int opt = nettyMsg.getOpt();
        switch (opt){
            case 1 :
                JSONObject data = (JSONObject) nettyMsg.getData();
                String id = (String) data.get("id");
                ChannelMap.channelMap.put(id.toString(),ctx.channel());
                System.out.println("channelmap中数据："+ChannelMap.channelMap);
                System.out.println(new Date() + "===" + ctx.channel().remoteAddress() + "->Server :" +nettyMsg.toString());
                break;
            case 2 :
                System.out.println(new Date() + "===心跳数据" + ctx.channel().remoteAddress() + "->Server :" +nettyMsg.toString());
        }

    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.printStackTrace();  
        ctx.close();  
    }  
  
} 