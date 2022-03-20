package planewar;

import javax.swing.*;

/*
      这是一个背景图的类
      每次创建一个背景图
 */
public class Background {

    //图片（路径）    宽度 高度  起始坐标 x y
    private int x;
    private int y;
    private ImageIcon BackgroundImage = new ImageIcon("images/bg.png");

    //为了让构建子弹对象方便
    //构造方法
    public Background(int x, int y){
        this.x = x;
        this.y = y;

    }
    //提供属性对应的get方法

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImageIcon getBackgroundImage() {
        return BackgroundImage;
    }

    //子弹自己的事情  添加一个方法  让子弹的y像素值减小
    public void move(){
        this.y += 1;//背景的移动速度
    }
}
