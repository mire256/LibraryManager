package LibraryManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import d20200117.MakeConnection;



public class CreateAccount extends JFrame{

	JScrollPane scrollPane;
	JButton SignUp;
	JPanel Bg;
	JTextField IdText,NameText,EmailText,PhoneNumText;
	JPasswordField PwText;
	ImageIcon icon;

	public CreateAccount() {
		super("CreateAccount");
		setBounds(800, 100, 368, 598);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);

		//-------배경이 될 이미지 아이콘 생성		
		icon = new ImageIcon("src/Images/signBg.png");
		Bg = new JPanel() {

			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), -2,-28, 368, 598, null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		Bg.setLayout(null);
		add(Bg);
		//텍스트 필드 생성
		IdText = new JTextField("ID",50);
		PwText = new JPasswordField("PASSWORD",50);
		NameText = new JTextField("NAME",20);
		EmailText = new JTextField("EMAIL",50); 
		PhoneNumText = new JTextField("PHONE",15);

		Color c = new Color(40, 117, 130);
		Color c1 = new Color(235, 235, 235);

		IdText.setFont(new Font("Bowlby One SC",Font.BOLD,15));
		IdText.setBorder(null);
		IdText.setBackground(c);
		IdText.setBounds(85, 176, 200,30);
		IdText.setForeground(c1);
		IdText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				IdText.setText("");

			}		
		});

		NameText.setFont(new Font("Bowlby One SC",Font.BOLD,15));
		NameText.setBorder(null);
		NameText.setBackground(c);
		NameText.setBounds(85, 225, 200, 30);
		NameText.setForeground(c1);
		NameText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			    NameText.setText("");
			}
		});

		PwText.setFont(new Font("Bowlby One SC",Font.BOLD,15));
		PwText.setBorder(null);
		PwText.setBackground(c);
		PwText.setBounds(85, 275, 200, 30);
		PwText.setForeground(c1);
		PwText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				PwText.setText("");
			}
		});


		EmailText.setFont(new Font("Bowlby One SC",Font.BOLD,15));
		EmailText.setBorder(null);
		EmailText.setBackground(c);
		EmailText.setBounds(85, 325, 200, 30);
		EmailText.setForeground(c1);
	    EmailText.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    	 EmailText.setText("");
	    	}
		});


		PhoneNumText.setFont(new Font("Bowlby One SC",Font.BOLD,15));
		PhoneNumText.setBorder(null);
		PhoneNumText.setBackground(c);
		PhoneNumText.setBounds(85, 375, 200, 30);
		PhoneNumText.setForeground(c1);
		PhoneNumText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				PhoneNumText.setText("");
			}
		});
		//버튼 생성
		//		ImageIcon ii = new ImageIcon("src/Images/signBtn.png");
		SignUp = new JButton();
		SignUp.setBounds(49,450, 271, 51);
		SignUp.setIcon(new ImageIcon("src/Images/signBtn.png"));
		SignUp.setPressedIcon(new ImageIcon("src/Images/signBtn2.png"));
		SignUp.setBorderPainted(false);
		SignUp.setContentAreaFilled(false);
		SignUp.setIconTextGap(0);
		SignUp.setFocusPainted(false);
		//		SignUp.setOpaque(false);
		//		SignUp.setLayout(null);
		add(SignUp);
		SignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				//버튼 액션 설정			
				if(obj==SignUp) {
					//라벨 폰트  설정
					JOptionPane.showMessageDialog(CreateAccount.this ,"Welcome To Toy Library!!");
					new Join();
					dispose();
					//					System.out.println("click");
				}else if(obj!=SignUp) {

				}
				// TODO Auto-generated method stub
				Connection conn = MakeConnection.getConnection();

				StringBuffer sb = new StringBuffer();
				sb.append("insert into LIBRARY_ACCOUNT ");
				sb.append("values ( ? , ? , ? , ?, ? ) ");

				String ID = IdText.getText();
				String NAME = NameText.getText();
				String PASSWORD = PwText.getText();
				String EMAIL = EmailText.getText();
				String PHONENUMBER = PhoneNumText.getText();

				PreparedStatement pstmt = null;
				try {
					pstmt = conn.prepareStatement(sb.toString());
					pstmt.setString(1,  ID);
					pstmt.setString(2, NAME);
					pstmt.setString(3, PASSWORD);
					pstmt.setString(4, EMAIL);
					pstmt.setString(5, PHONENUMBER);

					int result = pstmt.executeUpdate();

					System.out.println("result : "+result);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

				finally {
					try {
						if(pstmt!=null)pstmt.close();
						if(conn!=null) conn.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
					}

				}
			}});

		// 컴포넌트에 담기
		Bg.add(SignUp);
		Bg.add(IdText);
		Bg.add(PwText);
		Bg.add(EmailText);
		Bg.add(NameText);
		Bg.add(PhoneNumText);
		scrollPane = new JScrollPane(Bg);
		add(scrollPane);
		setContentPane(Bg);

		setVisible(true);	

	}

	public static void main(String[] args) {
		new CreateAccount();

	}

}



