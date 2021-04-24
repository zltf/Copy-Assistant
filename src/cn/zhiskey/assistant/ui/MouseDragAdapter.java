package cn.zhiskey.assistant.ui;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseDragAdapter extends MouseInputAdapter {
    private Point point;
    private JFrame frame;

    public MouseDragAdapter(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.point = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (point != null) {
            // 改变光标形状
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

            Point p = e.getPoint();
            int dx = p.x - point.x;
            int dy = p.y - point.y;

            int x = frame.getX();
            int y = frame.getY();
            frame.setLocation(x + dx, y + dy);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        point = null;
        // 改变光标形状
        frame.setCursor(Cursor.getDefaultCursor());
    }

    public void bind(Component component) {
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
    }
}
