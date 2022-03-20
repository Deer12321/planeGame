package planewar;

import javax.swing.*;

/*
       描述一个英雄飞机子弹类
       类中创建一个对象---一个英雄飞机的子弹
       每一次创建一个对象表示子弹
       与描述飞机是类似的，有自己的x，y和宽度，高度
 */
public class Bullet {

    //图片（路径）    宽度 高度  起始坐标 x y
    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon bulletImage = new ImageIcon("images/zidan.png");

    //为了让构建子弹对象方便
    //构造方法
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        this.width = bulletImage.getIconWidth();
        this.height = bulletImage.getIconHeight();

    }
    //提供属性对应的get方法

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ImageIcon getBulletImage() {
        return bulletImage;
    }

    //子弹自己的事情  添加一个方法  让子弹的y像素值减小
    public void move(){
        this.y -= 5;//子弹飞行的速度
    }
}
