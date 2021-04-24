package cn.zhiskey.assistant.ui;

import cn.zhiskey.assistant.Main;
import cn.zhiskey.assistant.toolkit.ClipboardTool;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CopyPlusFrame extends JFrame {

    private static final int FRAME_WIDTH = 380;
    private static final int FRAME_HEIGHT = 200;

    private Vector<String> clipboardStrings = new Vector<>();
    private Vector<String> stringListData = new Vector<>();
    private JList<String> stringList = new JList<>(stringListData);

    public CopyPlusFrame() {
        super();

        //设置窗口总在最前
        setAlwaysOnTop(true);
        create();
        new MouseDragAdapter(this).bind(this);

        new Thread(() -> {
            while (true) {
                String clipboardString = null;
                try {
                    Thread.sleep(100);
                    clipboardString = ClipboardTool.getClipboardString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (clipboardString != null && !clipboardStrings.isEmpty()
//                        && !clipboardString.equals(clipboardStrings.firstElement())
                        && !haveString(clipboardStrings, clipboardString)
                        || clipboardStrings.isEmpty()) {
                    clipboardStrings.add(0, clipboardString);
                    if (clipboardString != null && clipboardString.length() > 20) {
                        stringListData.add(0, clipboardString.substring(0, 17) + "...");
                    } else {
                        stringListData.add(0, clipboardString);
                    }

                    stringList.setListData(stringListData);

                    // System.out.println(clipboardStrings);
                }
            }
        }).start();
    }

    private boolean haveString(Vector<String> v, String str) {
        for(String s : v) {
            if(s.equals(str))
            {
                return true;
            }
        }
        return false;
    }

    private void create() {
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension sc=kit.getScreenSize();
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(sc.width - FRAME_WIDTH - 20,sc.height/2 - FRAME_HEIGHT/2 * 3 - 20);

        setUndecorated(true);                               // 无边框
        setBackground(new Color(0,0,0,0));	    //设置窗口背景为透明色

        JPanel panel = new JPanel();
        add(panel);

        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        // panel.setBackground(new Color(0,0,0,0));

        Font fontDefault = stringList.getFont();
        stringList.setFont(new Font(fontDefault.getName(), fontDefault.getStyle(), 15));
        JScrollPane jScrollPane = new JScrollPane(stringList);
        jScrollPane.setBounds(10, 10, 360, 180);
        // new MouseDragAdapter(this).bind(stringList);
        new MouseDragAdapter(this).bind(jScrollPane);

        stringList.addListSelectionListener(e -> {
            int index = stringList.getSelectedIndex();
            if (index >= 0 && index<clipboardStrings.size()) {
                String string = clipboardStrings.get(index);
                if (string != null) {
                    ClipboardTool.setClipboardString(string);
                    // stringListData.remove(index);
                    // clipboardStrings.remove(index);
                    stringList.setListData(stringListData);
                }
            }
        });

        panel.add(jScrollPane);
    }

}

