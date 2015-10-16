package com.rao.playground.swing.utils;

import com.rao.playground.swing.layouts.CenterLayout2;
import com.rao.playground.swing.layouts.FillLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by shuaibrao on 16/10/2015.
 */
public class LockablePanelWithOverlay extends JPanel {

    private final DisableInputLayerUI disableInputLayerUI;
    private final JPanel overLay;
    private final JPanel popUpOverlay;
    private boolean locked;

    public LockablePanelWithOverlay(JComponent contentPanel, JComponent overlayComponent, JComponent popupOverlayComponent) {
        assert SwingUtilities.isEventDispatchThread();

        this.setLayout(new BorderLayout(0, 0));
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new FillLayout());

        disableInputLayerUI = new DisableInputLayerUI();
        JLayer<JComponent> jLayer = new JLayer<JComponent>(contentPanel, disableInputLayerUI);

        overLay = new JPanel(new CenterLayout2());
        overLay.add(overlayComponent);
        overLay.setOpaque(false);
        overLay.setVisible(false);

        popUpOverlay = new JPanel(new CenterLayout2());
        popUpOverlay.add(overlayComponent);
        popUpOverlay.setOpaque(false);
        popUpOverlay.setVisible(false);

        layeredPane.add(jLayer, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(overLay, JLayeredPane.MODAL_LAYER);
        layeredPane.add(popUpOverlay, JLayeredPane.MODAL_LAYER);

        this.add(layeredPane, BorderLayout.CENTER);

    }

    public void showOverLay() {
        assert SwingUtilities.isEventDispatchThread();
        if (!locked) {
            locked = true;
            disableInputLayerUI.start();
        }
        overLay.setVisible(true);
    }

    public void clearOverlay() {
        assert SwingUtilities.isEventDispatchThread();
        if (locked && !popUpOverlay.isVisible()) {
            locked = false;
            disableInputLayerUI.stop();
        }
        overLay.setVisible(false);
    }

    public void showPopupOverLay() {
        assert SwingUtilities.isEventDispatchThread();
        if (!locked) {
            locked = true;
            disableInputLayerUI.start();
        }
        popUpOverlay.setVisible(true);
    }

    public void clearPopupOverlay() {
        assert SwingUtilities.isEventDispatchThread();
        if (locked && !overLay.isVisible()) {
            locked = false;
            disableInputLayerUI.stop();
        }
        popUpOverlay.setVisible(false);
    }

    public boolean isLocked() {
        return locked;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JPanel contentPanel = new JPanel(new BorderLayout());
                contentPanel.setBackground(Color.black);
                contentPanel.add(new JTextField("Stuff"), BorderLayout.CENTER);
                contentPanel.add(new JTextField("More"), BorderLayout.EAST);
                Dimension size1 = new Dimension(100, 100);
                contentPanel.setPreferredSize(size1);
                contentPanel.setMinimumSize(size1);
                contentPanel.setMaximumSize(size1);

                JPanel dialogPanel = new JPanel(new BorderLayout());
                Dimension size = new Dimension(50, 50);
                dialogPanel.setPreferredSize(size);
                dialogPanel.setMinimumSize(size);
                dialogPanel.setMaximumSize(size);
                dialogPanel.add(new JTextField("Locked"), BorderLayout.CENTER);

                JPanel popupPanel = new JPanel(new BorderLayout());
                Dimension size2 = new Dimension(25, 25);
                popupPanel.setPreferredSize(size2);
                popupPanel.setMinimumSize(size2);
                popupPanel.setMaximumSize(size2);
                popupPanel.add(new JTextField("PopUp"), BorderLayout.CENTER);

                final LockablePanelWithOverlay lockablePanelWithOverlay = new LockablePanelWithOverlay(contentPanel, dialogPanel, popupPanel);
                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.add(lockablePanelWithOverlay, BorderLayout.CENTER);
                mainPanel.add(new JButton(new AbstractAction("Lock") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lockablePanelWithOverlay.showOverLay();
                    }
                }), BorderLayout.WEST);

                mainPanel.add(new JButton(new AbstractAction("UnLock") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lockablePanelWithOverlay.clearOverlay();
                    }
                }), BorderLayout.EAST);

                JFrame frame = new JFrame("Test");
                frame.getContentPane().add(mainPanel);
                frame.pack();
                frame.setMaximumSize(frame.getSize());
                frame.setMinimumSize(frame.getSize());
                frame.setResizable(true);
                frame.setVisible(true);
            }
        });
    }
}
