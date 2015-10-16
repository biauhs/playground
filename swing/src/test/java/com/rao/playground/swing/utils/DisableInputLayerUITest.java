package com.rao.playground.swing.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisableInputLayerUITest {
    public JComponent makeUI() {
        final JPanel p = new JPanel();
        final DisableInputLayerUI layerUI = new DisableInputLayerUI();
        final JLayer<JComponent> jlayer = new JLayer<JComponent>(p, layerUI);
        final Timer stopper = new Timer(4000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                layerUI.stop();
            }
        });
        p.add(new JCheckBox());
        p.add(new JTextField(10));
        p.add(new JButton(new AbstractAction("button") {
            public void actionPerformed(ActionEvent e) {
                layerUI.start();
                if (!stopper.isRunning()) {
                    stopper.start();
                }
            }
        }));
        stopper.setRepeats(false);
        return jlayer;
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    public static void createAndShowGUI() {
        JComponent c = new DisableInputLayerUITest().makeUI();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().add(c, BorderLayout.NORTH);
        f.getContentPane().add(new JTextArea());
        f.setSize(320, 240);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}