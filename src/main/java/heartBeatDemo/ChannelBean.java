package heartBeatDemo;

import io.netty.channel.Channel;

import java.io.Serializable;

public class ChannelBean implements Serializable {

    private Channel channel = null;

    public ChannelBean(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
