package planewar;

import javax.swing.*;

/*
       描述一个敌方飞机类
       类中创建一个对象---一个敌方飞机
       与子弹类似
 */
public class enemy {

    //图片（路径）    宽度 高度  起始坐标 x y
    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon enemyImage = new ImageIcon("images/enemy.png");

    //为了让构建子弹对象方便
    //构造方法
    public enemy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = enemyImage.getIconWidth();
        this.height = enemyImage.getIconHeight();

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

    public ImageIcon getEnemyImage() {
        return enemyImage;
    }

    //敌人自己的事情  添加一个方法  让敌人的y像素值增加
    public void move(){
        this.y += 3;//敌人飞行的速度
    }
}
