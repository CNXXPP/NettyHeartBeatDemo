package designPattern.singleton;

import javax.swing.*;

/**
 * 枚举实现单例，preferred approach
 */
public enum EnumSingleton {

    INSTANCE;
    private MyFrame instance;
    EnumSingleton(){
        instance = new MyFrame();
    }
    public MyFrame getInstance(){
        return instance;
    }
    public class MyFrame extends javax.swing.JFrame{

    }
}
