package LibraryManager;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import d20200117.MakeConnection;

public class BookList extends JFrame{

	Vector<Vector> out;
	Vector<String> in,title;
	Connection conn = MakeConnection.getConnection();

	Statement stat;
	JPanel bg;
	JScrollPane BgPan;
	DefaultTableModel model;
	JButton btn,returnBtn,rentalBtn,bookListBtn,mainBtn;
	JTextField search;
	JLabel text;

	ImageIcon icon ;


	public BookList() {
		super("BookList");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 500);
		setLayout(null);
		setResizable(false);
		icon = new ImageIcon("src/Images/tableBg.png");

		bg = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, 420, 500, null);
				setOpaque(false);
				super.paintComponent(g);

			}
		}; 
		bg.setLayout(null);
		//버튼 설정  
		btn = new JButton();
		btn.setBounds(252, 310, 111, 45);
		btn.setIcon(new ImageIcon("src/Images/searchBtn.png"));
		btn.setPressedIcon(new ImageIcon("src/Images/searchBtnPress.png"));
		btn.setBorderPainted(false); //버튼테두리 사용안함
		btn.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		btn.setIconTextGap(0);
		btn.setFocusPainted(false); //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
		add(btn);
		
		rentalBtn= new JButton();
		rentalBtn.setBounds(88, 375, 58, 90);
		rentalBtn.setIcon(new ImageIcon("src/Images/rentalBtn.png"));
		rentalBtn.setPressedIcon(new ImageIcon("src/Images/rentalPressBtn.png"));
		rentalBtn.setBorderPainted(false); //버튼테두리 사용안함
		rentalBtn.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		rentalBtn.setIconTextGap(0);
		rentalBtn.setFocusPainted(false); //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
		add(rentalBtn);

		returnBtn = new JButton();
		returnBtn.setBounds(148, 375, 58, 90);
		returnBtn.setIcon(new ImageIcon("src/Images/returnBtn.png"));
		returnBtn.setPressedIcon(new ImageIcon("src/Images/returnBtnPress.png"));
		returnBtn.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		returnBtn.setIconTextGap(0);
		returnBtn.setFocusPainted(false); //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
		add(returnBtn);

		

		bookListBtn = new JButton();
		bookListBtn.setBounds(208, 375, 58, 90);
		bookListBtn.setIcon(new ImageIcon("src/Images/listBtn.png"));
		bookListBtn.setPressedIcon(new ImageIcon("src/Images/listBtnPress.png"));
		bookListBtn.setBorderPainted(false); //버튼테두리 사용안함
		bookListBtn.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		bookListBtn.setIconTextGap(0);
		bookListBtn.setFocusPainted(false); //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
		bookListBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookShelf();
				dispose();
				
			}
		});
		add(bookListBtn);

		mainBtn= new JButton();
		mainBtn.setBounds(268, 375, 58, 90);
		mainBtn.setIcon(new ImageIcon("src/Images/mainBtn.png"));
		mainBtn.setPressedIcon(new ImageIcon("src/Images/mainBtnPress.png"));
		mainBtn.setBorderPainted(false); //버튼테두리 사용안함
		mainBtn.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		mainBtn.setIconTextGap(0);
		mainBtn.setFocusPainted(false); //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
		mainBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(mainBtn)) {
					int result = JOptionPane.showConfirmDialog(null, "Main으로 돌아가시겠습니까?","Main",
							JOptionPane.YES_NO_OPTION);
					new Join();
					dispose();

				}}});
		add(mainBtn);


		//버튼 설정end
		//전체 레이아웃 크기 설정 

		text = new JLabel();
		//		text.setIcon(new ImageIcon("src/Images/tableBg.png"));
		text.setBounds(15, 310, 240, 41);
		text.setLayout(null);
		//		add(text);
		bg.add(text);
		Color c = new Color(48, 164, 187);

		search = new JTextField("SERACH HERE",50);
		search.setFont(new Font("Bowlby One",Font.BOLD,10));
		search.setBounds(10, 310, 240, 41);
		search.setLayout(null);
		search.setBackground(c);
		search.setForeground(Color.white);
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				search.setText("");
			}
		});
		bg.add(search);

		title = new Vector<String>();
		out = new Vector<Vector>();
		title.add("BOOKNUM");
		title.add("TITLE");
		title.add("AUTHOR");
		title.add("PUBLISHER");
		title.add("COUNT");
		title.add("HIREDATE");



		model = new DefaultTableModel(out,title);

		JTable table = new JTable(model);
		Color c2= new Color(254, 221, 199);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setBackground(c2);
		//		table.setOpaque(false);

		//테이블 레이아웃 크기 설정 
		JScrollPane scollPan = new JScrollPane(table);
		scollPan.setBounds(10, 10, 400, 300);
		add(scollPan,BorderLayout.CENTER);
		bg.add(text);bg.add(bookListBtn);
		bg.add(mainBtn);bg.add(rentalBtn);
		bg.add(returnBtn);
		bg.add(scollPan);bg.add(btn);

		BgPan= new JScrollPane(bg);

		add(BgPan);

		setContentPane(bg);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					stat.close();
					conn.close();

					setVisible(false);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		getData();

		setVisible(true);

	}

	public static void main(String[] args) {
		new BookList();   


	}   
	public void getData() {
		try {

			Connection conn = MakeConnection.getConnection();
			stat = conn.createStatement();
			String query = "select * from BOOKLIST";
			ResultSet rs = stat.executeQuery("select BOOKNUM,TITLE,AUTHOR,PUBLISHER,COUNT,HIREDATE from BOOKLIST");

			ResultSetMetaData rsm = rs.getMetaData();
			//         DefaultTableModel model = new DefaultTableModel(title,0){
			//            public boolean isCellEdittable (int i, int c) {
			//               if (c>=0) {
			//                  return false;   
			//               }else {
			//                  return true;   
			//               }
			//            }
			//         };
			//JTable테이블에 데이터 집어넣기 
			while(rs.next()) {
				in = new Vector<String>();
				for(int i = 1; i <=rsm.getColumnCount(); i++) {
					in.add(rs.getString(i));
				} 
				out.add(in);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}   

}
