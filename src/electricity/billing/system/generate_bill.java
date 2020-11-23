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
import java.util.Date;
import java.util.Calendar;
import java.text.*;


/**
 *
 * @author Parveen
 */
public class generate_bill extends JFrame implements ActionListener{
    JLabel l1,l2;
    JTextArea t1;
    JButton b1,b2;
    Choice c3,c2;
    JPanel p1,p2;
    generate_bill(){
        super("Generate Bill");
        setSize(700,650);
        setLayout(new BorderLayout());
        
        p1 = new JPanel();
        p2=new JPanel();
        
        l1 = new JLabel("Select Meter No.:");
        
        c3 = new Choice();
        l2=new JLabel("             Select Month:");
        c2 = new Choice();
        try{
            conn c1  = new conn();
            String s1 = "select * from emp";
            ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                c3.addItem(rs.getString("meter"));
            }            
        }catch(Exception e){
            e.printStackTrace();
        }  
        
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

        
        t1 = new JTextArea(50,15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif",Font.ITALIC,18));
        
        b1 = new JButton("Generate Bill");
        b2=new JButton("Print");
        
        p1.add(l1);
        p1.add(c3);
        p1.add(l2);
        p1.add(c2);
        add(p1,"North");
        
        add(jsp,"Center");
        p2.add(b1);
        p2.add(b2);
        add(p2,"South");
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setLocation(350,40);
    }
    public void actionPerformed(ActionEvent ae){
        String str=ae.getActionCommand();
        if(str.equals("Generate Bill")){
        try{
            Date date=new Date();
            Calendar cc=Calendar.getInstance();
            
            SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy ");
            SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss");
            
            conn c = new conn();
            int year=cc.getWeekYear();
            String monthh = c2.getSelectedItem();
            t1.setForeground(Color.red);
            t1.setEditable(false);
            t1.setText("Dakshin Haryana Bijli Vitran Nigam Limited\n(A Government of Haryana Undertaking)\nELECTRICITY BILL FOR THE MONTH "+monthh+" ,"+year+"\n\n\n");
            t1.append("\n Date :-  "+formatter.format(date));
            t1.append("\t    Time :-  "+time.format(date));
            
            ResultSet rs = c.s.executeQuery("select * from emp where meter="+c3.getSelectedItem());
            
            if(rs.next()){
                t1.append("\n\n Customer Name:"+rs.getString("name"));
                t1.append("\n Meter Number:  "+rs.getString("meter"));
                t1.append("\n Address:            "+rs.getString("address"));
                t1.append("\n State:                 "+rs.getString("state"));
                t1.append("\n City:                   "+rs.getString("city"));
                t1.append("\n Email:                "+rs.getString("email"));
                t1.append("\n Phone Number  "+rs.getString("phone"));
                t1.append("\n-----------------------------------------------------------------------------------------------------");
                t1.append("\n");
            }
            
            rs = c.s.executeQuery("select * from tax");
            
            if(rs.next()){
                t1.append("\n Meter Location:"+rs.getString("meter_location"));
                t1.append("\n Meter Type:      "+rs.getString("meter_type"));
                t1.append("\n Phase Code:    "+rs.getString("phase_code"));
                t1.append("\n Bill Type:         "+rs.getString("bill_type"));
                t1.append("\n Days:               "+rs.getString("days"));
                t1.append("\n");
                t1.append("-------------------------------------------------------------------------------------------------------");
                t1.append("\n");
                t1.append("\n Meter Rent:\t\t\t"+rs.getString("meter_rent"));
                t1.append("\n MCB Rent:  \t\t\t"+rs.getString("mcb_rent"));
                t1.append("\n Service Tax:\t\t\t"+rs.getString("service_rent"));
                t1.append("\n GST@9%:\t\t\t"+rs.getString("gst"));
                t1.append("\n");
                
            }
            
            rs = c.s.executeQuery("select * from bill where meter='"+c3.getSelectedItem()+"' and month='"+monthh+"'");
            
            while(rs.next()){
                t1.append("\n Current Month :\t\t"+rs.getString("month"));
                t1.append("\n Units Consumed:\t\t"+rs.getString("unit"));
                t1.append("\n Total Charges :\t\t"+rs.getString("amt"));
                t1.append("\n-----------------------------------------------------------------------------------------------------");
                t1.append("\n TOTAL PAYABLE BEFORE DUE DATE :\t"+rs.getString("amt"));
                t1.append("\n Surcharge :\t\t\t50");
                int amt=Integer.parseInt(rs.getString("amt"));
                t1.append("\n GROSS PAYABLE AFTER DUE DATE :\t"+(amt+50));
                t1.append("\n-----------------------------------------------------------------------------------------------------");
                
                cc.add(Calendar.DATE, +20);
                t1.append("\n DUE DATE :\t"+cc.getTime());
                t1.append("\n-----------------------------------------------------------------------------------------------------");
                t1.append("\n Customer Care TollFreeNumber:180011804334,1912\n\n\n");
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        }
        else{
            try{
            t1.print();
        }catch(Exception e){
            
        }
        }
    }
    
    public static void main(String[] args){
        new generate_bill().setVisible(true);
        
    }
    
}
