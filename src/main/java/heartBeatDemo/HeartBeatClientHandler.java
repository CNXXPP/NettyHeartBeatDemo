package heartBeatDemo;
  
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
import io.netty.handler.timeout.IdleState;  
import io.netty.handler.timeout.IdleStateEvent;  
import io.netty.util.CharsetUtil;  
import io.netty.util.ReferenceCountUtil;  
  
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {  
  
      
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",  
            CharsetUtil.UTF_8));
      
    private int currentTime = 0;  
      
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        System.out.println("连接激活时间是："+new Date());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","0527");
        ctx.channel().writeAndFlush(new NettyMsg(0x01, jsonObject));

        //System.out.println("HeartBeatClientHandler channelActive");
        ctx.fireChannelActive();  
    }  
  
    @Override  
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {  
        System.out.println("连接停止时间是："+new Date());
        //System.out.println("HeartBeatClientHandler channelInactive");
    }  
  
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {  
        System.out.println("客户端无写动作超过设定时间，触发时间："+new Date());
        if (evt instanceof IdleStateEvent) {  
            IdleStateEvent event = (IdleStateEvent) evt;  
            if (event.state() == IdleState.WRITER_IDLE) {  
//                if(currentTime <= TRY_TIMES){
                if(currentTime==10){
                    return;
                }
                    System.out.println("当前触发次数:"+ ++currentTime);
                    //ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
                    ctx.channel().writeAndFlush(new NettyMsg(0x02,null));
//                }
            }  
        }  
    }  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        NettyMsg message = (NettyMsg) msg;
        System.out.println(message);
        ReferenceCountUtil.release(msg);  
    }  
}  