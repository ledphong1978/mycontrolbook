package com.java.mycontrolbook;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	public static String userName;
    public static String passWord;
    static JLabel lblWelcome;
  
    //=================================    
    JDesktopPane jDesktopPane1;
    JMenuItem FormAdd;
    JMenuBar MenuBar;
    JMenu MenuFile;//แฟ้ม
    JMenu MenuReport;//รายงาน
    JMenu MenuAbout;//เกี่ยวกับ
    JMenuItem ReportMain;//รายงาน
    JMenuItem FormAbout;//เกี่ยวกับ
    
	public static void main(String[] args) {
	java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
            public void run() {
                setDefaultLookAndFeelDecorated(true);
                new Main().setVisible(true);                
            }        
        });
	}
	
	public Main() {
		super("โปรแกรมทดสอบ");
        this.setExtendedState(Main.MAXIMIZED_BOTH);//Maximized
        setResizable(false);//ไม่ให้ Resized
        
        getContentPane().setBackground(new Color(0xE8F2FE));
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	getContentPane().setLayout(null);
    		
    	// Label Welcome
    	lblWelcome = new JLabel("ยินดีต้อนรับสู่ โปรแกรมทดสอบ",JLabel.CENTER);
    	lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	lblWelcome.setBounds(168, 110, 640, 25);
    	getContentPane().add(lblWelcome);
            lblWelcome.setForeground(Color.blue);
    						
    	// When Frame Loaded
    	addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                OpenDialog();
            }
    	});
                    
        //==========Load================================================
        //=============Menu===================================
        jDesktopPane1 = new javax.swing.JDesktopPane();
        MenuBar=new JMenuBar();

        //=============MenuFile===============================
        MenuFile = new JMenu("แฟ้ม");//แฟ้ม
        MenuReport = new JMenu("รายงาน");//รายงาน
        MenuAbout = new JMenu("เกี่ยวกับ");//เกี่ยวกับ
        JMenu menu2 = new JMenu("เพิ่มรายการ");
        JMenu menu3 = new JMenu("รายงาน");
        
        //===============================================================
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //=======Begin=============================================
        //=======FormAdd===========================================
        FormAdd = new JMenuItem("เพิ่มข้อมูล");//FormAdd
        FormAdd.setText("เพิ่มข้อมูล");
        FormAdd.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    FormAddActionPerformed(evt);
                } catch (ParseException | SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu2.add(FormAdd);     
        MenuFile.add(menu2);
        //=========ออกจากระบบ========================================
        JMenuItem menuExit = new JMenuItem("ออกจากระบบ");

        MenuFile.addSeparator();//lets add that separator
        menuExit.setToolTipText("Exit Application");
        MenuFile.add(menuExit);
        //===========Old==========================================
        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", "User Confirmation",JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        MenuBar.add(MenuFile);
        //=============ReportMainชชช==============================
        MenuReport.setText("รายงาน");
        ReportMain = new JMenuItem("รายงาน");
        ReportMain.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    ReportMainActionPerformed(ae);
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu3.add(ReportMain);
        MenuReport.add(menu3);
        MenuBar.add(MenuReport);
        //============FormAbout================================================
        FormAbout = new JMenuItem("เกี่ยวกับ");
        FormAbout.setText("เกี่ยวกับและอนุสัญญา");
        FormAbout.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    try {
                        FormAboutActionPerformed(evt);
                    } catch (ParseException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        MenuAbout.add(FormAbout);
        MenuBar.add(MenuAbout); 
        //======================================================================
        setJMenuBar(MenuBar);       
        pack();
        //======================================================================
	}
	
	//=========FormAdd=========================================================
    private void FormAddActionPerformed(ActionEvent evt) throws ParseException, SQLException {
        //FormAdd f1 = new FormAdd("เพิ่มข้อมูล");
        //jDesktopPane1.add(f1);
        //f1.show();
    }
    //=========ReportMainชชช===================================================
    private void ReportMainActionPerformed(ActionEvent evt) throws ParseException, SQLException {
        //ReportMain f2 = new ReportMain("รายงาน");
        //jDesktopPane1.add(f2);
        //f2.show();
    }
    //=========FormAbout=======================================================
    private void FormAboutActionPerformed(ActionEvent evt) throws ParseException, SQLException {
        //FormAbout f3 = new FormAbout("เกี่ยวกับ");
        //jDesktopPane1.add(f3);
        //f3.show();
    }
  //==========================================================================
    public static Boolean getLogin() {
		
        Connection connect = null;
        PreparedStatement pre = null;
        Boolean status = false;
		
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connect =  DriverManager.getConnection("jdbc:mariadb://localhost/mybook" +
                       "?user=root&password=gomplayer");
			
            String sql = " SELECT * FROM  member " +
				" WHERE Username = ? " +
				" AND Password = ? ";
            pre = connect.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, passWord);

            ResultSet rec = pre.executeQuery();
            if(rec.next()) {
		lblWelcome.setText("ยินดีต้อนรับสู่ โปรแกรมทดสอบ และสวัสดีคุณ " + rec.getString("Name"));
				
		// Passing Variable to Class
		UserClass user = new UserClass();
		user.setUsername(rec.getString("Username"));
		user.setName(rec.getString("Name"));
				
		status = true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
            }
             
        } catch (ClassNotFoundException | SQLException | HeadlessException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
		
        try {
            if(pre != null) {
                pre.close();
                connect.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
            return status;
    }
		
    public static void OpenDialog() {      
        LoginDialog dialog = new LoginDialog();
        dialog.setModal(true);
        dialog.setVisible(true);
	
        userName = dialog.sUsername;
        passWord =  dialog.sPassword;
		
        // Check Login
        if(!getLogin()){
            OpenDialog(); // Login Again
        }		
    }
}
