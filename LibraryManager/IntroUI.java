package LibraryManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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



public class IntroUI extends JFrame {

	// 멤버 변수
	JScrollPane scrollPane;
	ImageIcon icon;
	JPanel background;
	JTextField IDText,InputJoin;
	JButton Login, Join;
	JPasswordField PWText;
	JLabel gif;

	Connection conn = MakeConnection.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	// 생성자 초기화
	public IntroUI() {
		super("Library Manager");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(800, 100, 368, 598);
		setLayout(null);
		setResizable(false);
		//-------배경이 될 이미지 아이콘 생성		
		icon = new ImageIcon("src/Images/Bg.png");

		//배경 Panel(background) 생성을 만들고, ContentPane(주 내용창)으로 지정      
		background = new JPanel() {

			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), -5,-30, 368, 598, null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		background.setLayout(null);

        gif = new JLabel();
        gif.setIcon(new ImageIcon("src/Images/bird.gif"));
        gif.setBounds(279, 32, 60, 45);
        gif.setLayout(null);
        add(gif);
		//----------------textFiled ID------------------------------		
		Color c = new Color(251, 175, 54);
		Color c1 = new Color(33, 8, 7);
		IDText = new JTextField("NAME",20);
		IDText.setFont(new Font("Bowlby One",Font.BOLD,15));
		IDText.setBorder(null);
		IDText.setBounds(94, 275, 200, 20);
		IDText.setBackground(c);
		IDText.setForeground(c1);
		IDText.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				IDText.setText("");
			}
			
			
			
		});

		//-----------------textFiled PW------------------------------	
		PWText = new JPasswordField("PASSWORD",20);
		PWText.setFont(new Font("Bowlby One",Font.BOLD,15));
		PWText.setBorder(null);
		PWText.setBounds(94, 327, 200, 20);
		PWText.setBackground(c);
		PWText.setForeground(c1);
		PWText.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				PWText.setText("");
			}

		});

		//-----------------Button Login --------------------------	
		Login = new JButton();
		Join = new JButton();


		Login.setBounds(42, 410, 130, 41);
		Login.setIcon(new ImageIcon("src/Images/signIn.png"));
		Login.setPressedIcon(new ImageIcon("src/Images/signIn2.png"));
		Login.setBorderPainted(false); //버튼테두리 사용안함
		Login.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		Login.setIconTextGap(0);
		Login.setFocusPainted(false); //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
		Login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//		            login = (JButton) e.getSource();

				// 변수 선언

				String id = IDText.getText();
				String pw = PWText.getText();
				int result = 1;

				// 연결자 선언
				Connection conn = MakeConnection.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				// SQL문
				StringBuffer sb = new StringBuffer();
				sb.append("select * ");
				sb.append("from Library_Account ");
				sb.append("where ID = ? ");
				sb.append("and PASSWORD = ? ");

				// SQL실행
				try {

					pstmt = conn.prepareStatement(sb.toString());
					pstmt.setString(1, id);
					pstmt.setString(2, pw);

					rs = pstmt.executeQuery();

					if (rs.next()) {

						new Join();
						dispose();
					} else {
						result = 1;
						//						System.out.println("관리자에게 승인받은 계정만 로그인 할 수 있습니다. 관리자에게 문의하세요.");
						
							JOptionPane.showMessageDialog(IntroUI.this, "Sorry, Username or Password Error","Login Error!", JOptionPane.ERROR_MESSAGE);
							IDText.setText(" ");
							PWText.setText(" ");
						}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("오류 발생");
				} finally {
					try {
						if (pstmt != null) {
							pstmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		//-----------------Button Join --------------------------			

		Join.setBounds(186, 410, 130, 41);
		Join.setIcon(new ImageIcon("src/Images/signUP.png"));
		Join.setPressedIcon(new ImageIcon("src/Images/signUP2.png"));
		Join.setBorderPainted(false); //버튼테두리 사용안함
		Join.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		Join.setIconTextGap(0);
		Join.setFocusPainted(false);
		Join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj==Join) {
					new CreateAccount();
//					dispose();
				}else {
									}
						

			}
		});
		//		Join.addActionListener(this);

		InputJoin = new JTextField(20);
		//		InputJoin.setFont(new Font("Bowlby One SC",Font.BOLD,13));
		//		InputJoin.setBackground(Color.black);
		add(InputJoin);
		//--------------------컴포넌트에 담기----------------------	

		background.add(IDText);
		background.add(PWText);
        background.add(gif);
		background.add(Login);
		background.add(Join);
		scrollPane = new JScrollPane(background);
		add(scrollPane);
		setContentPane(scrollPane);

		setVisible(true);

	}

	public static void main(String[] args) {
		new IntroUI();

	}

}

