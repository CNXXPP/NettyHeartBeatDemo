package designPattern;

import designPattern.singleton.EnumSingleton;

public class Run {
    public static void main(String[] args) {
        EnumSingleton.MyFrame instance = EnumSingleton.INSTANCE.getInstance();
        EnumSingleton.MyFrame instance1 = EnumSingleton.INSTANCE.getInstance();
        System.out.println(instance==instance1);
    }
}
