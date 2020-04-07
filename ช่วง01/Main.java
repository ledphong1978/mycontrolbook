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
    JMenu MenuFile;//���
    JMenu MenuReport;//��§ҹ
    JMenu MenuAbout;//����ǡѺ
    JMenuItem ReportMain;//��§ҹ
    JMenuItem FormAbout;//����ǡѺ
    
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
		super("��������ͺ");
        this.setExtendedState(Main.MAXIMIZED_BOTH);//Maximized
        setResizable(false);//������ Resized
        
        getContentPane().setBackground(new Color(0xE8F2FE));
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	getContentPane().setLayout(null);
    		
    	// Label Welcome
    	lblWelcome = new JLabel("�Թ�յ�͹�Ѻ��� ��������ͺ",JLabel.CENTER);
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
        MenuFile = new JMenu("���");//���
        MenuReport = new JMenu("��§ҹ");//��§ҹ
        MenuAbout = new JMenu("����ǡѺ");//����ǡѺ
        JMenu menu2 = new JMenu("������¡��");
        JMenu menu3 = new JMenu("��§ҹ");
        
        //===============================================================
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //=======Begin=============================================
        //=======FormAdd===========================================
        FormAdd = new JMenuItem("����������");//FormAdd
        FormAdd.setText("����������");
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
        //=========�͡�ҡ�к�========================================
        JMenuItem menuExit = new JMenuItem("�͡�ҡ�к�");

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
        //=============ReportMain���==============================
        MenuReport.setText("��§ҹ");
        ReportMain = new JMenuItem("��§ҹ");
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
        FormAbout = new JMenuItem("����ǡѺ");
        FormAbout.setText("����ǡѺ���͹��ѭ��");
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
        //FormAdd f1 = new FormAdd("����������");
        //jDesktopPane1.add(f1);
        //f1.show();
    }
    //=========ReportMain���===================================================
    private void ReportMainActionPerformed(ActionEvent evt) throws ParseException, SQLException {
        //ReportMain f2 = new ReportMain("��§ҹ");
        //jDesktopPane1.add(f2);
        //f2.show();
    }
    //=========FormAbout=======================================================
    private void FormAboutActionPerformed(ActionEvent evt) throws ParseException, SQLException {
        //FormAbout f3 = new FormAbout("����ǡѺ");
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
		lblWelcome.setText("�Թ�յ�͹�Ѻ��� ��������ͺ ������ʴդس " + rec.getString("Name"));
				
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
