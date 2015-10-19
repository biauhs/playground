package com.rao.playground.swing.transitions;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */

public class TransitionPanelTester {

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel centrePanel;
    private JPanel bottomPanel;
    private CardTransitionPanel transitionPanel;
    private JButton doTransitionButton;
    private JSlider transitionPositionSlider;
    private JButton startTransitionButton;
    private JButton nextButton;
    private JRadioButton verticalRadioButton;
    private JRadioButton horizontalRadioButton;
    private JSlider flipSpeedSlider;
    private JCheckBox flipIntoDistanceCheckBox;
    private JRadioButton forwardRadioButton;
    private JRadioButton backwardRadioButton;
    private JRadioButton flipRadioButton;
    private JRadioButton slideRadioButton;
    private JRadioButton fadeRadioButton;

    private FlipTransition flipTransition = new FlipTransition();
    private SlideTransition slideTransition = new SlideTransition();
    private FadeTransition fadeTransition = new FadeTransition();

    public TransitionPanelTester() {
        $$$setupUI$$$();
        doTransitionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transitionPanel.setUseAnimation(true);
                transitionPanel.setDebug(false);
                transitionPanel.transitionToNext();
            }
        });

        doTransitionButton.setBackground(Color.GREEN);
        startTransitionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transitionPositionSlider.setValue(0);
                transitionPanel.setUseAnimation(false);
                transitionPanel.setDebug(true);
                transitionPanel.transitionToNext();
            }
        });
        transitionPositionSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                transitionPanel.setTransitionPosition(transitionPositionSlider.getValue() / 100f);
            }
        });
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transitionPositionSlider.setValue(0);
                transitionPanel.transitionToNext();
            }
        });
        horizontalRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFlipOrientation();
            }
        });
        verticalRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFlipOrientation();
            }
        });
        flipRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTransitionType();
            }
        });
        slideRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTransitionType();
            }
        });
        fadeRadioButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                updateTransitionType();
            }
        });
        flipSpeedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                transitionPanel.setAnimationDuration(flipSpeedSlider.getValue());
            }
        });
        flipIntoDistanceCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flipTransition.setFlipIntoDistance(flipIntoDistanceCheckBox.isSelected());
            }
        });
        forwardRadioButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                updateFlipDirection();
            }
        });
        backwardRadioButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                updateFlipDirection();
            }
        });
    }

    private void updateFlipDirection() {
        transitionPanel.setTransitionDirection(forwardRadioButton.isSelected() ? TransitionDirection.FORWARD : TransitionDirection.BACKWARD);
    }

    private void updateFlipOrientation() {
        transitionPanel.setTransitionAxis(horizontalRadioButton.isSelected() ? TransitionAxis.HORIZONTAL : TransitionAxis.VERTICAL);
    }

    private void updateTransitionType() {
        Transition transition;
        if (flipRadioButton.isSelected()) {
            transition = flipTransition;
        } else if (slideRadioButton.isSelected()) {
            transition = slideTransition;
        } else {
            transition = fadeTransition;
        }
        transitionPanel.setTransition(transition);
        flipIntoDistanceCheckBox.setVisible(flipRadioButton.isSelected());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        transitionPanel = new CardTransitionPanel();
        TestPanel testPanel1 = new TestPanel();
        testPanel1.setPanelName("11111111111111111111111111111111111");
        testPanel1.getMainPanel().setBackground(Color.YELLOW);
        transitionPanel.add(testPanel1.getMainPanel(), "Test 1");
        TestPanel testPanel2 = new TestPanel();
        testPanel2.setPanelName("22222222222222222222222222222222222");
        transitionPanel.add(testPanel2.getMainPanel(), "Test 2");
        testPanel2.getMainPanel().setBackground(Color.GREEN);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        centrePanel = new JPanel();
        centrePanel.setLayout(new GridBagLayout());
        mainPanel.add(centrePanel, BorderLayout.CENTER);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        centrePanel.add(transitionPanel, gbc);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        startTransitionButton = new JButton();
        startTransitionButton.setText("Start Transition");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(startTransitionButton, gbc);
        final JSeparator separator1 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 8;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 0, 4, 0);
        bottomPanel.add(separator1, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Manual Control");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Orientation");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(label2, gbc);
        horizontalRadioButton = new JRadioButton();
        horizontalRadioButton.setSelected(true);
        horizontalRadioButton.setText("Horizontal");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(horizontalRadioButton, gbc);
        verticalRadioButton = new JRadioButton();
        verticalRadioButton.setText("Vertical");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(verticalRadioButton, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Animation Speed");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(label3, gbc);
        flipSpeedSlider = new JSlider();
        flipSpeedSlider.setMaximum(2000);
        flipSpeedSlider.setMinimum(100);
        flipSpeedSlider.setMinorTickSpacing(100);
        flipSpeedSlider.setPaintLabels(true);
        flipSpeedSlider.setPaintTicks(true);
        flipSpeedSlider.setValue(100);
        flipSpeedSlider.setValueIsAdjusting(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(flipSpeedSlider, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Animation Position");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(label4, gbc);
        transitionPositionSlider = new JSlider();
        transitionPositionSlider.setMinimum(0);
        transitionPositionSlider.setMinorTickSpacing(10);
        transitionPositionSlider.setPaintLabels(true);
        transitionPositionSlider.setPaintTicks(true);
        transitionPositionSlider.setValue(10);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(transitionPositionSlider, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Direction");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(label5, gbc);
        forwardRadioButton = new JRadioButton();
        forwardRadioButton.setSelected(true);
        forwardRadioButton.setText("Forward");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(forwardRadioButton, gbc);
        backwardRadioButton = new JRadioButton();
        backwardRadioButton.setText("Backward");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(backwardRadioButton, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Transition Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(label6, gbc);
        flipRadioButton = new JRadioButton();
        flipRadioButton.setSelected(true);
        flipRadioButton.setText("Flip");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(flipRadioButton, gbc);
        slideRadioButton = new JRadioButton();
        slideRadioButton.setText("Slide");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(slideRadioButton, gbc);
        fadeRadioButton = new JRadioButton();
        fadeRadioButton.setText("Fade");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(fadeRadioButton, gbc);
        nextButton = new JButton();
        nextButton.setText("Next");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        bottomPanel.add(nextButton, gbc);
        doTransitionButton = new JButton();
        doTransitionButton.setText("Do Transition");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 8, 8, 8);
        bottomPanel.add(doTransitionButton, gbc);
        flipIntoDistanceCheckBox = new JCheckBox();
        flipIntoDistanceCheckBox.setSelected(true);
        flipIntoDistanceCheckBox.setText("Flip Into Distance");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        bottomPanel.add(flipIntoDistanceCheckBox, gbc);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(horizontalRadioButton);
        buttonGroup.add(verticalRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(forwardRadioButton);
        buttonGroup.add(backwardRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(flipRadioButton);
        buttonGroup.add(slideRadioButton);
        buttonGroup.add(fadeRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}