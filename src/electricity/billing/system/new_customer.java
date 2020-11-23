/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Parveen
 */
public class new_customer extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2;
    JPanel p1,p2,p3;
    new_customer(){
        setLocation(350,100);
        setSize(650,600);
        
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        
        //setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(7,2,2,10));
        p2.setSize(200,200);
        p2.setBackground(Color.RED);
        //p1.setBackground(Color.RED);
        p3.setBackground(Color.ORANGE);
        
        Font f=new Font("SansSerif", Font.BOLD,20);
        int c = JLabel.CENTER;

        l1 = new JLabel("Name");
        l1.setHorizontalAlignment(c);
        l1.setFont(f);
        t1 = new JTextField(15);
        t1.setFont(f);
        p2.add(l1);
        p2.add(t1);
        l2 = new JLabel("Meter No");
        l2.setHorizontalAlignment(c);
        l2.setFont(f);
        t2 = new JTextField(15);
        t2.setFont(f);
        p2.add(l2);
        p2.add(t2);
        l3 = new JLabel("Address");
        l3.setHorizontalAlignment(c);
        l3.setFont(f);
        t3 = new JTextField(20);
        t3.setFont(f);
        p2.add(l3);
        p2.add(t3);
        l4 = new JLabel("State");
        l4.setHorizontalAlignment(c);
        l4.setFont(f);
        t4 = new JTextField(15);
        t4.setFont(f);
        p2.add(l4);
        p2.add(t4);
        l5 = new JLabel("City");
        l5.setHorizontalAlignment(c);
        l5.setFont(f);
        t5 = new JTextField(15);
        t5.setFont(f);
        p2.add(l5);
        p2.add(t5);
        l6 = new JLabel("Email");
        l6.setHorizontalAlignment(c);
        l6.setFont(f);
        t6 = new JTextField(15);
        t6.setFont(f);
        p2.add(l6);
        p2.add(t6);
        l7 = new JLabel("Phone Number");
        l7.setHorizontalAlignment(c);
        l7.setFont(f);
        t7 = new JTextField(15);
        t7.setFont(f);
        p2.add(l7);
        p2.add(t7);
        
        b1 = new JButton("Submit");
        b2 = new JButton("Cancel");
        b1.setFont(f);
        b2.setFont(f);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        setLayout(new BorderLayout());
        
        p3.add(b1);
        p3.add(b2);
        add(p3,BorderLayout.SOUTH);
        
        add(p2,"Center");
        //add(p2,BorderLayout.EAST);
        
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 300,Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);
        l8.setBackground(Color.red);
        //p1.add(l8);
        //add(p1,BorderLayout.WEST);
        add(l8,"West");
        //for changing the color of the whole 
        getContentPane().setBackground(Color.red);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent ae){
        String str=ae.getActionCommand();
        if(str.equals("Submit")){
        
        String a = t1.getText();
        String c = t2.getText();
        String d = t3.getText();
        String e = t4.getText();
        String f = t5.getText();
        String g = t6.getText();
        String h = t7.getText();
        
        String q1 = "insert into emp values('"+a+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"')";
        
        try{
            if(c.isEmpty()){
                JOptionPane.showMessageDialog(null,"Plzz Enter Data"); 
            }
            else{                
                conn c1 = new conn();
                c1.s.executeUpdate(q1);
                JOptionPane.showMessageDialog(null,"Employee Created");
                t1.setText("");t2.setText("");
                t3.setText("");t4.setText("");
                t5.setText("");t6.setText("");
                t7.setText("");
            }
        }
        
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Duplicate Entry");
            t1.setText("");t2.setText("");
            t3.setText("");t4.setText("");
            t5.setText("");t6.setText("");
            t7.setText("");
             ex.printStackTrace();
        }
        }
        else{
            this.setVisible(false);
        }
    }            
    public static void main(String[] args){
        new new_customer().setVisible(true);
    }
    
}
