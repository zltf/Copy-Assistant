package cn.zhiskey.assistant.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainFloatFrame extends JFrame {

    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 200;

    public MainFloatFrame() {
        super();
        //设置窗口总在最前
        setAlwaysOnTop(true);
        create();
        new MouseDragAdapter(this).bind(this);
    }

    private void create() {
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension sc=kit.getScreenSize();
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(sc.width - FRAME_WIDTH - 20,sc.height/2 - FRAME_HEIGHT/2);

        setUndecorated(true);                               // 无边框
        setBackground(new Color(0,0,0,0));	    //设置窗口背景为透明色

        JPanel panel = new JPanel();
        add(panel);

        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(new Color(0,0,0,0));

        JButton btn = new JButton("btn");
        btn.setBorderPainted(false);
        btn.setBounds(0, 0, 100, 200);
        new MouseDragAdapter(this) {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("按钮被点击1");
                CopyPlusFrame copyPlusFrame = new CopyPlusFrame();
                copyPlusFrame.setVisible(true);
            }
        }.bind(btn);

        JButton btn1 = new JButton("btn");
        btn1.setBorderPainted(false);
        btn1.setBounds(100, 0, 100, 200);
        new MouseDragAdapter(this) {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("按钮被点击2");
            }
        }.bind(btn1);

        panel.add(btn);
        panel.add(btn1);
    }

}
