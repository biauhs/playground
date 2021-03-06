package com.rao.playground.swing.transitions;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class TestPanel {

  private JComboBox comboBox1;
  private JTextField textField2;
  private JCheckBox checkBox1;
  private JRadioButton radioButton1;
  private JList list1;
  private JTree tree1;
  private JPanel mainPanel;
  private JLabel panelNameLabel;

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public void setPanelName(String name) {
    panelNameLabel.setText(name);
  }

  {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());
    final JLabel label1 = new JLabel();
    label1.setText("Text Field");
    GridBagConstraints gbc;
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(label1, gbc);
    final JLabel label2 = new JLabel();
    label2.setText("Combo Box");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(label2, gbc);
    comboBox1 = new JComboBox();
    final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
    defaultComboBoxModel1.addElement("Item 1");
    defaultComboBoxModel1.addElement("Item 2");
    defaultComboBoxModel1.addElement("Item 3");
    defaultComboBoxModel1.addElement("Item 4");
    comboBox1.setModel(defaultComboBoxModel1);
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(comboBox1, gbc);
    final JLabel label3 = new JLabel();
    label3.setText("Check Box");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(label3, gbc);
    textField2 = new JTextField();
    textField2.setMinimumSize(new Dimension(100, 24));
    textField2.setPreferredSize(new Dimension(100, 24));
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(textField2, gbc);
    checkBox1 = new JCheckBox();
    checkBox1.setText("");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(checkBox1, gbc);
    radioButton1 = new JRadioButton();
    radioButton1.setText("");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(radioButton1, gbc);
    final JLabel label4 = new JLabel();
    label4.setText("Radio Button");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(label4, gbc);
    final JLabel label5 = new JLabel();
    label5.setText("List Box");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(label5, gbc);
    final JScrollPane scrollPane1 = new JScrollPane();
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(scrollPane1, gbc);
    list1 = new JList();
    final DefaultListModel defaultListModel1 = new DefaultListModel();
    defaultListModel1.addElement("Item 1");
    defaultListModel1.addElement("Item 2");
    defaultListModel1.addElement("Item 3");
    defaultListModel1.addElement("Item 4");
    list1.setModel(defaultListModel1);
    scrollPane1.setViewportView(list1);
    final JLabel label6 = new JLabel();
    label6.setText("Table");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 3;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(label6, gbc);
    final JScrollPane scrollPane2 = new JScrollPane();
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(6, 6, 6, 6);
    mainPanel.add(scrollPane2, gbc);
    tree1 = new JTree();
    scrollPane2.setViewportView(tree1);
    panelNameLabel = new JLabel();
    panelNameLabel.setFont(new Font(panelNameLabel.getFont().getName(), Font.BOLD, panelNameLabel.getFont().getSize()));
    panelNameLabel.setText("Panel name");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 4;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(6, 6, 0, 6);
    mainPanel.add(panelNameLabel, gbc);
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return mainPanel;
  }
}
