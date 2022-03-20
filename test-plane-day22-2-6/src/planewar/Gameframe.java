package planewar;
//  1.Swing 窗体
//  2.集合 ArrayList
//  3.类的关系  继承 实现 is-a has-a  use-a
//  4.抽象类 接口 匿名内部类
//  5.语法结构 for break while
//  6. 设计模式（模板 监听器（观察着））

import javax.swing.*;

public class Gameframe extends JFrame implements Runnable{
    //1 画一个窗体      默认是隐藏状态
    //private JFrame frame  = new JFrame("飞机大战");
    //2 窗体内需要一个面板（透明容器 装元素 为了布局）
    private GamePanel panel = new GamePanel();//容器 可以有很多个
    //3 很多组件（按钮 文本框 图片..)
    //private JButton button = new JButton("按钮");

    //重写无参构造方法
    public Gameframe(){
        this.addElements();
        this.addListener();
        this.setFrameSelf();
    }
    //将这些元素添加到一起----事情
    private void addElements(){
       // panel.add(button);
        this.add(panel);//this super区别
    }

    //将绑定了监听器的对象添加到窗体中
    public void addListener(){
        this.addMouseMotionListener(panel);
    }

    //设置窗体的属性
    private void setFrameSelf(){
        this.setVisible(true);//设置状态--变为可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame.EXIT_ON_CLOSE可以用3，静态常量 用3可读性不强
        this.setBounds(300,100,366,532);
        this.setResizable(false);//窗口不能拖拽


    }

    public void run(){
        //点用一下init方法 进行初始化、
        panel.init();
    }
}
