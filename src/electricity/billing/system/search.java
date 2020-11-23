
package electricity.billing.system;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Parveen
 */
public class search extends JFrame implements ActionListener{
    JLabel l1;
    JTextField tf1;
    JTextArea ta1;
    JButton b1,b2,b3;
    JPanel p1,p2;
    String x[] = {"Emp Name","Meter No","Address","State","City","Email","Phone"};
    String y[][] = new String[30][8];
    int i=0, j=0;
    search(){
        super("Search Details");
        setSize(650,650);
        setLocation(400,40);        
        
        l1=new JLabel("Meter Number::");
        tf1=new JTextField(15);
        b3=new JButton("Search");
        p2=new JPanel();
        p2.add(l1);
        p2.add(tf1);
        p2.add(b3);
        add(p2,"North");
        
        ta1 = new JTextArea(15,15);
        JScrollPane jsp = new JScrollPane(ta1);
       
        p1=new JPanel();
        b1 = new JButton("Print");
        b2=new JButton("Back");        
        p1.add(b1);
        p1.add(b2);
        add(p1,"South");
        
        add(jsp,"Center");
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        String str=ae.getActionCommand();
        if(str.equals("Search"))
        {
            try
            { 
                ta1.setEditable(false);
                ta1.setText(" "); 
                ta1.setFont(new Font("Senserif",Font.BOLD,28));
                ta1.setText("\n         Customer Details\n");                
                conn c  = new conn();
                ResultSet rs = c.s.executeQuery("select * from emp where meter='"+tf1.getText()+"'");
                while(rs.next())
                {
                    ta1.append("\n\n    Customer Name :  " +rs.getString("name"));
                    ta1.append("\n    Meter Number   :  " +rs.getString("meter"));
                    ta1.append("\n    Address             :  " +rs.getString("address"));
                    ta1.append("\n    State                  :  " +rs.getString("state"));
                    ta1.append("\n    City                    :  " +rs.getString("city"));
                    ta1.append("\n    Email                 :  " +rs.getString("email"));
                    ta1.append("\n    Phone Number  :  " +rs.getString("phone"));                   
                    ta1.append("\n");              
                }            
            }catch(Exception e){
            }
        }
        else if(str.equals("Back"))
        {
            this.setVisible(false);
            new customer_details().setVisible(true);
        }
        else
        {
         try{
            ta1.print();
            }catch(Exception e){
            }   
        }
    }
    public static void main(String[] args){
        
      new search().setVisible(true);
    }
    
}
