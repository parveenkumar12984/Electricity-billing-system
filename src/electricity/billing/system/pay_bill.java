/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity.billing.system;

import javax.swing.*;
/**
 *
 * @author Parveen
 */
public class pay_bill extends JFrame{
     pay_bill(){
        JEditorPane j = new JEditorPane();
        j.setEditable(false);   

        try {
            j.setPage("https://www.google.com");
        }
        catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load</html>");
        } 

        JScrollPane scrollPane = new JScrollPane(j);     
        getContentPane().add(scrollPane);
        setExtendedState(MAXIMIZED_BOTH);
    }
    public static void main(String[] args){
        new pay_bill().setVisible(true);
    }
    
}
