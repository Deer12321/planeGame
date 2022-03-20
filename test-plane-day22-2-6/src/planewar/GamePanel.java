package planewar;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
/*
    让GamePanel是一个panel
 */
//在这个类中间添加一个缺省适配器模式(23种)，否则必须重写
    public class GamePanel extends JPanel implements MouseMotionListener {

    //添加背景（2个对象）
    private ArrayList<Background> bgList = new ArrayList();
    //飞机
    private Hero hero = new Hero(150,412);
    private HeroDestory heroDestory;
    //子弹(考虑几颗)
    //一堆  用数组好还是集合好？
    //数组（创建时必须指定长度，确定后不能发生改变，定长且连续）
    //集合 （与数组类似，可以存储一组元素。存储后长度可以发生改变）
    //对于本程序的子弹来说集合更加合适 --选择长度后可以改变
    //集合是一个庞大的家族
    // List （有序可重复）
    //      ArrayList   底层是数组--适合遍历，不适合插入和删除    1.5倍扩容 非安全，性能快
    //      LinkedList  底层是链表结构--适合插入删除，不适合便利
    //      Vector      底层也是数组     2倍扩容     线程安全--性能慢
    // Set （无序不可重复） 不重复指不接受
    //      HashSet     散列表  数组+链表      hashCode + equals
    //      TreeSet     二叉树                 compareTo
    // Map  （k-v  key无序不可重复  value 无序可重复）存储重复元素时是覆盖
    //      HashMap     散列表
    //      TreeMap     二叉树
    //      LinkedHashMap   （有自动排序）
    //      CurrentHashMap
    //最好自己用原生的Java代码实现一个集合类的设计 ArrayBox LinkedBox
    private ArrayList<Bullet> bulletList = new ArrayList();

    //敌机（好多个）
    private ArrayList<enemy> enemyList = new ArrayList();

    //存储爆炸对象
    private ArrayList<Bomb> bombList = new ArrayList();

    //变量 记录分数
    private int score = 0;

    //设计一个方法用来给对象做初始化
    public void init(){
        //初始化的时候先添加一个背景
        bgList.add(new Background(0,0));

        //初始化一个飞机对象 上边已经创建完了
        //需要循环不断发射子弹
        int count = 0;//当作一个系数  控制子弹添加的时间
        while(true){
            count++;
            //添加另一张背景
            if(bgList.size() == 1){
                bgList.add(new Background(0,-532));
            }
            //控制背景长度
            for(int i=0;i<bgList.size();i++){
               Background bg =  bgList.get(i);
               if(bg!=null){
                   bg.move();//每次向下移动
                   if(bg.getY()>532){
                       bgList.remove(bg);
                   }
               }
            }

            //添加子弹
            if(count % 10 == 0){//子弹之间的间隔
                //初始化子弹对象 存储子弹的集合已经创建完了 集合内没有子弹
                bulletList.add(new Bullet(hero.getX()+35,hero.getY()-15));
            }
            //控制子弹个数 删除 移动等等
            for(int i=0;i<bulletList.size();i++){
                Bullet bullet = bulletList.get(i);
                if(bullet!=null){
                    //刚存入集合的子弹移动一下
                    bullet.move();
                    if(bullet.getY() < 0 ){//证明子弹飞出去了
                        bulletList.remove(bullet);
                    }
                }
            }

            //添加敌机
            if(count % 40 == 0){//敌机密集程度
                //随机计算一下敌机出现的x位置
                //x位置需要在窗口内
                int tempEnemyX = (int)(Math.random()*350);
                //初始化敌机对象
                enemyList.add(new enemy(tempEnemyX,-30));
            }
            for(int i = 0;i<enemyList.size();i++){
                enemy enemy = enemyList.get(i);
                if(enemy!=null){
                    //判断对人的飞机是否与子弹发生碰撞

                    if(enemy.getY() > 532){
                        enemyList.remove(enemy);

                    }
                    //判断敌人的飞机是否与子弹发生碰撞
                    //做循环看enemy与哪个子弹碰撞
                    for(int j=0;j<bulletList.size();j++){
                        //循环每一刻子弹 与当前那个enemy飞机比较看是否有碰撞
                        Bullet bullet = bulletList.get(j);
                        if(bullet!=null){//严谨判断子弹找到了
                            if(this.isHit(enemy,bullet)){//证明碰撞了
                                enemyList.remove(enemy);
                                bulletList.remove(bullet);

                                //出现击中的效果
                                score += 10;
                                bombList.add(new Bomb(enemy.getX(),enemy.getY(),0));
                            }
                        }
                    }

                    //看敌机是否与英雄碰撞
                    if(this.isHit(hero,enemy)){
                        System.out.println("坠毁");
                        heroDestory = new HeroDestory(hero.getX(),hero.getY());
                        hero = null;//坠毁后删除
                        bombList.add(new Bomb(enemy.getX(),enemy.getY(),0));
                        enemyList.remove(enemy);//敌人删掉
                        repaint();
                        JOptionPane.showMessageDialog(this,"游戏结束,您的得分为："+score);
                        return;
                    }
                    enemy.move();//让飞机向下移动一下
                }
            }

            //控制爆炸图片存储时间
            for(int i=0;i<bombList.size();i++){
                Bomb bomb = bombList.get(i);
                if(bomb!=null){
                    bomb.setTime(bomb.getTime() + 1);
                    if(bomb.getTime() >= 20){
                        bombList.remove(bomb);
                    }
                }
            }

            repaint();

           try {
               Thread.sleep(10);//10ms
           } catch(InterruptedException a){
               a.printStackTrace();
           }
        }

    }


    //展示出来
    public void paint(Graphics g){//是一个重写方法
        //让父类的paint画完
        super.paint(g);
        //===================================
        //先画背景
        for(int i=0;i<bgList.size();i++){
            Background bg = bgList.get(i);
            g.drawImage(bg.getBackgroundImage().getImage(),bg.getX(),bg.getY(),null);
        }

        //画飞机  最好做一个严谨的判断
        if(hero!=null){
            Image image = hero.getHeroImage().getImage();
            g.drawImage(image,hero.getX(),hero.getY(),null);
        }
        //画hero爆炸后的状态
        if(heroDestory!=null){
            g.drawImage(heroDestory.getHeroDestoryImage().getImage(),heroDestory.getX(),heroDestory.getY(),null);
        }

        //画子弹 不只一颗，每一颗都需要画
        for(int i=0;i<bulletList.size();i++){
            //每一次循环获取一个子弹
            Bullet bullet = bulletList.get(i);
            if(bullet!=null){
                g.drawImage(bullet.getBulletImage().getImage(),bullet.getX(),bullet.getY(),null);

            }
        }
        //画敌机
        for(int i=0;i<enemyList.size();i++){
            //每次循环获取敌机
            enemy enemy = enemyList.get(i);
            if(enemy!=null){
                g.drawImage(enemy.getEnemyImage().getImage(),enemy.getX(),enemy.getY(),null);
            }
        }
        //画爆炸
        for (int i=0;i<bombList.size();i++){
            Bomb bomb = bombList.get(i);
            if(bomb!=null){
                g.drawImage(bomb.getbombImage().getImage(),bomb.getX(),bomb.getY(),null);

            }
        }
        //设置字体
        g.setFont(new Font("宋体",Font.BOLD,24));
        //直接在左上角画分数
        g.drawString("得分："+score,10,30);
    }

    //=======================================

    //设计一个方法 计算碰撞
    //两个参数 敌机 子弹
    //返回值 是否碰撞 Boolean
    private boolean isHit(enemy e,Bullet b){
        //利用jdk提供的类
        Rectangle eRect = new Rectangle(e.getX(),e.getY(),e.getWidth(),e.getHeight());
        //子弹的范围
        Rectangle bRect = new Rectangle(b.getX(),b.getY(),b.getWidth(),b.getHeight());

        return eRect.intersects(bRect);// 数据库intersect  这个方法判断是否相交，返回Boolean类型
    }

    //敌机和hero撞击
    private boolean isHit(Hero h,enemy e){
        Rectangle hRect = new Rectangle(h.getX(),h.getY(),h.getWidth(),h.getHeight());
        Rectangle eRect = new Rectangle(e.getX(),e.getY(),e.getWidth(),e.getHeight());
        return hRect.intersects(eRect);
    }

    //这个方法帮我们算飞机的位置
    private void calculatPosition(MouseEvent e){
        //拖拽
        //飞机需要根据我鼠标的位置 移动他自己的位置
        //可以用参数e获取鼠标的位置
        int tempX = e.getX();
        int tempY = e.getY();
        if(hero!=null){
            if(tempX>0&&tempX<362){
                hero.setX(tempX - 40);
            }
            if(tempY>80&&tempY<500){
                hero.setY(tempY - 80);
            }
        }
        //重新画一遍
        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e){
            this.calculatPosition(e);
    }
    @Override
    public void mouseMoved(MouseEvent e){
        //移动（悬停）
    }
    
}
