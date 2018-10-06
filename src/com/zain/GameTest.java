package com.zain;

import com.zain.frame.LevelPanel;
import com.zain.frame.MainFrame;
import com.zain.model.Level;
import com.zain.type.GameType;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by yongz on 2018/10/6.
 */
public class GameTest {
    int i = 1;

    @Test
    public void mapTest() {
        MainFrame frame = new MainFrame();// 创建主窗体
        JButton btn = new JButton("按钮");
        frame.getContentPane().add(btn);
        frame.setVisible(true);// 让主窗体可见
        frame.setPanel(new LevelPanel(i, frame, GameType.TWO_PLAYER));// 主窗体跳转至关卡面板
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                i++;
                int code = e.getKeyCode();// 获取按下的按键值
                switch (code) {// 判断按键值
                    case KeyEvent.VK_N:// 如果按下的是“N”，效果同下
                        frame.setPanel(new LevelPanel(i, frame, GameType.TWO_PLAYER));// 主窗体跳转至关卡面板
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gameFrameTest() {
        new GameFrame().launchFrame();
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


class GameFrame extends JFrame {
    public void launchFrame() {
        JButton button = new JButton("按钮");
        MenuBar mb = new MenuBar();
        Menu file = new Menu("文件");

        mb.add(file);
        this.setMenuBar(mb);
        this.setSize(200, 200);
        //this.setUndecorated(true);
        this.setTitle("测试");
        this.getContentPane().add(button);
        this.setLocation(200, 100);
        //this.setResizable(false);
        this.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "用户单击了按钮", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

}

