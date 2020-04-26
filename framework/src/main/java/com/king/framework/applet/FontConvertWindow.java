package com.king.framework.applet;

import com.king.framework.utils.FontUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @创建人 chq
 * @创建时间 2020/4/22
 * @描述
 */
public class FontConvertWindow {

    // 得到显示器屏幕的宽高
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public static int windowsWidth = 600;
    public static int windowsHeight = 400;

    private static JTextArea textArea;
    private static JTextArea textArea1;

    /**
     * 创建主界面
     */
    private static void createAndShowGUI(){
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("中文转Unicode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds((width - windowsWidth) / 2, (height - windowsHeight) / 2, windowsWidth, windowsHeight);
        frame.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setSize(windowsWidth,20);
        JLabel title = new JLabel("中文转换Unicode编码");
        northPanel.add(title);

        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(300, 400));
        textArea = new JTextArea(20, 15);
        textArea.setFont(new Font("微软雅黑",Font.BOLD,15));
        textArea.setLineWrap(true);
        westPanel.add(textArea);

        JPanel centerPanel = new JPanel();
        textArea1 = new JTextArea(20,15);
        textArea1.setFont(new Font("微软雅黑",Font.BOLD,15));
        textArea1.setLineWrap(true);
        centerPanel.add(textArea1);

        JPanel southPanel = new JPanel();
        southPanel.setSize(windowsWidth,20);
        JButton button = new JButton("转换");
        southPanel.add(button);
        button.addActionListener(new ConvertButton());

        frame.add(northPanel,BorderLayout.NORTH);
        frame.add(westPanel,BorderLayout.WEST);
        frame.add(centerPanel,BorderLayout.CENTER);
        frame.add(southPanel,BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    static class ConvertButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = textArea.getText();
            String newStr = FontUtil.chinaToUnicode(str);
            textArea1.setText(newStr);
        }
    }

    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
