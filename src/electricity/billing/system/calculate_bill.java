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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Parveen
 */
public class calculate_bill extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5;
    JTextField t1;
    Choice c1,c2;
    JButton b1,b2;
    JPanel p;
    String previousreading = null;
    int preading=0;
    calculate_bill(){
        
        p = new JPanel();
        p.setLayout(new GridLayout(4,2,30,30));
        p.setBackground(Color.WHITE);
        
        l1 = new JLabel("Calculate Electricity Bill");
        l2 = new JLabel("Meter No");
        l3= new JLabel("Month");
        l4 = new JLabel("Current Reading");
        
        
        t1 = new JTextField();
        
        c1 = new Choice();
        try{
            conn c  = new conn();
            String s1 = "select * from emp";
            ResultSet rs  = c.s.executeQuery(s1);
            while(rs.next()){
                c1.addItem(rs.getString("meter"));
            }            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        c2 = new Choice();
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");
        
        b1 = new JButton("Submit");
        b2 = new JButton("Cancel");
        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(180, 270,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l5 = new JLabel(i3);
                        
        l1.setFont(new Font("Senserif",Font.PLAIN,50));
        t1.setFont(new Font("Senserif",Font.PLAIN,20));
        //Move the label to center
        l1.setHorizontalAlignment(JLabel.CENTER);
                
        p.add(l1);
        p.add(l2);
        p.add(c1);
        p.add(l3);
        p.add(c2);
        p.add(l4);
        p.add(t1);
        p.add(b1);
        p.add(b2);
        
        setLayout(new BorderLayout(30,30));
        
        add(l1,"North");
        add(p,"Center");
        add(l5,"West");
                
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);        
        setSize(650,500);
        setLocation(350,120);
    }
    public void actionPerformed(ActionEvent ae){
        String str=ae.getActionCommand();
        if(str.equals("Submit")){
        
        String meternumber = c1.getSelectedItem();
        String month = c2.getSelectedItem();
        
        try{
            conn c  = new conn();
            ResultSet rs = c.s.executeQuery("select * from bill where meter="+c1.getSelectedItem()); 
            while(rs.next()){
             preading = Integer.parseInt(rs.getString("creading"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        int creading = Integer.parseInt(t1.getText());
        int unit=creading-preading;
        int p2 = unit*7;
        int s=(p2*5)/100;
        int g=(p2*9)/100;
        int amt = p2+100+50+s+g;
        
        Date d=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        
        String q = "insert into bill values('"+meternumber+"','"+month+"','"+unit+"','"+amt+"','"+formatter.format(d)+"','"+preading+"','"+creading+"')";
       
        String t="insert into tax values('INSIDE','ELECTRONIC','1','NORMAL','30','100','50','"+s+" ','"+g+" ')";
        
        try{
            conn c = new conn();
            c.s.executeUpdate(q);
            c.s.executeUpdate(t);
            JOptionPane.showMessageDialog(null,"Bill Updated");
        }catch(Exception aee){
            aee.printStackTrace();
        }
        
        }
        else{
            this.setVisible(false);
        }
    }
    
       
    public static void main(String[] args){
        new calculate_bill().setVisible(true);
    }
    
}
