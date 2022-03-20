package planewar;

//登录页面

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    //需要一个panel展示   label也行
    private JLabel label = new JLabel();

    public LoginFrame(){
        label.setBounds(0,0,360,530);
        //label上可以直接添加图片
        label.setIcon(new ImageIcon("images/login.png"));
        this.add(label);

        //点击
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getX()>=84&&e.getX()<=246&&e.getY()>377&&e.getY()<424){
                    LoginFrame.this.setVisible(false);//垃圾回收还没有执行完
                    //开一个新窗口
                    new Thread(new Gameframe()).start();//新线程
                }else{
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        //移动
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if(e.getX()>=84&&e.getX()<=246&&e.getY()>377&&e.getY()<424){
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }else{
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        this.setBounds(300,100,360,530);
        this.setVisible(true);
    }

}
