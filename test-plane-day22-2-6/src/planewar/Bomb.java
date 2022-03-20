package planewar;

import javax.swing.*;

/*
       这是爆炸的类
       每创建一个对象就是一个爆炸的图片
 */
public class Bomb {

    //图片（路径）    宽度 高度  起始坐标 x y
    private int x;
    private int y;
    private ImageIcon bombImage = new ImageIcon("images/bomb.png");
    //添加一个属性 控制爆炸图片在屏幕上停留的时间
    private  int time;


    //构造方法
    public Bomb(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }
    //提供属性对应的get方法

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImageIcon getbombImage() {
        return bombImage;
    }



    public int getTime(){
        return time;
    }
    public void setTime(int time){
        this.time = time;
    }
}
