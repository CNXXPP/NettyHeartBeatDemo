package heartBeatDemo;

import java.io.Serializable;

public class NettyMsg implements Serializable{

    private int opt;  //操作码

    private Object data; //数据

    public NettyMsg(int opt, Object data) {
        this.opt = opt;
        this.data = data;
    }

    public NettyMsg() {
    }

    public int getOpt() {
        return opt;
    }

    public void setOpt(int opt) {
        this.opt = opt;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NettyMsg{" +
                "opt=" + opt +
                ", data=" + data +
                '}';
    }
}
