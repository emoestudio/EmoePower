package com.emoe.iot;
import java.awt.*;
import javax.swing.JFrame;
public class GUI extends JFrame{
    Container windowContent = null;
    private static final long serialVersionUID = 0xF6L;
    public GUI(){
        setTitle("Emoe Power Supply Controller");
        setSize(640, 320);
        windowContent = getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }   // Initialize the Window
}
