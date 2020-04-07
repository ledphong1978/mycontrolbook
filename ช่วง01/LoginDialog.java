package com.java.mycontrolbook;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog {

    public String sUsername;
    public String sPassword;
            	
    public static void main(String[] args) {
        try {
	        LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public LoginDialog() {
		setBounds(100, 100, 296, 175);
		setTitle("Login Username/Password");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
			
		// Label Username
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(33, 25, 82, 14);
		getContentPane().add(lblUsername);
	    lblUsername.setForeground(Color.blue);
			
		// Label Password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(33, 56, 64, 14);
		getContentPane().add(lblPassword);
	    lblPassword.setForeground(Color.blue);
			
		// Input Username
		final JTextField username = new JTextField();
		username.setBounds(125, 22, 101, 20);
		getContentPane().add(username);
	    username.setBackground(Color.pink);
	        		
		// Input Password
		final JPasswordField password = new JPasswordField();
		password.setBounds(125, 53, 101, 20);
		getContentPane().add(password);
	    password.setBackground(Color.pink);
	    username.requestFocus();
	                       	
		// Button OK
		final JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            sUsername = username.getText();
	            sPassword = new String(password.getPassword()); 
	            dispose();
	        }
		});
		btnOK.setBounds(70, 102, 78, 23);
		getContentPane().add(btnOK);
			
		// Button Cancel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
				sUsername = "";
				sPassword = "";
				System.exit(0);
	        }
		});
		btnCancel.setBounds(158, 102, 74, 23);
		getContentPane().add(btnCancel);
        
	    username.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	Object source = e.getSource();
	            //===========txtPassword====================           
	            if(source == username){
	                password.requestFocus();                   
	            }               
	        }
		});
        
	    password.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	Object source = e.getSource();
	            //===========txtPassword====================           
	            if(source == password){
	                btnOK.doClick();
	            }               
	        }
		});
    }
}

