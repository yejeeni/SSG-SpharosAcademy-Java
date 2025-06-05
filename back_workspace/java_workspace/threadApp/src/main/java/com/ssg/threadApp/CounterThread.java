package com.ssg.threadApp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;

public class CounterThread extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JButton startButton;

    public CounterThread() {
        setTitle("Counter Example");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        label1 = new JLabel("숫자 1: 0", JLabel.CENTER);
        label2 = new JLabel("숫자 2: 0", JLabel.CENTER);
        startButton = new JButton("시작");

        add(label1);
        add(label2);
        add(startButton);

        startButton.addActionListener(new StartButtonListener());

        setVisible(true);
    }

    class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Thread t1 = new Thread(new Counter(label1, "숫자 1: "));
            Thread t2 = new Thread(new Counter(label2, "숫자 2: "));
            t1.start();
            t2.start();
        }
    }

    class Counter implements Runnable {
        private JLabel label;
        private String prefix;

        public Counter(JLabel label, String prefix) {
            this.label = label;
            this.prefix = prefix;
        }

        public void run() {
            int count = 0;
            while (true) {
                count++;
                label.setText(prefix + count);

                try {
                    Thread.sleep(1000); // 1초 대기
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        new CounterThread();
    }
}

