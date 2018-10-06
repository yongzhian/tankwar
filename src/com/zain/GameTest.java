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
        MainFrame frame = new MainFrame();// ����������
        JButton btn = new JButton("��ť");
        frame.getContentPane().add(btn);
        frame.setVisible(true);// ��������ɼ�
        frame.setPanel(new LevelPanel(i, frame, GameType.TWO_PLAYER));// ��������ת���ؿ����
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                i++;
                int code = e.getKeyCode();// ��ȡ���µİ���ֵ
                switch (code) {// �жϰ���ֵ
                    case KeyEvent.VK_N:// ������µ��ǡ�N����Ч��ͬ��
                        frame.setPanel(new LevelPanel(i, frame, GameType.TWO_PLAYER));// ��������ת���ؿ����
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
        JButton button = new JButton("��ť");
        MenuBar mb = new MenuBar();
        Menu file = new Menu("�ļ�");

        mb.add(file);
        this.setMenuBar(mb);
        this.setSize(200, 200);
        //this.setUndecorated(true);
        this.setTitle("����");
        this.getContentPane().add(button);
        this.setLocation(200, 100);
        //this.setResizable(false);
        this.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "�û������˰�ť", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

}

