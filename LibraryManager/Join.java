package LibraryManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//디비랑 연결해서 sql문으로 중복체크 				
//sb.append("select * ");
//sb.append("from LibraryAccount ");
//sb.append("where ID = ? ");
//버튼 하나 생성 중복체크
public class Join extends JFrame implements ActionListener{

	//멤버 변수---------------------------------------------------------------------
	JScrollPane BgPanel;	
	ImageIcon icon;
	JPanel Bg1,Bg2;
	JButton book,room;
	JLabel empty1,empty2;
	//생성자 초기화------------------------------------------------------------------
	public Join() {
		super("LibraryMenu");
		setBounds(800, 100,368, 598);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);

		icon = new ImageIcon("src/Images/Bg2.png");

		Bg1 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), -5, -15, 368, 598, null);
				setOpaque(false);
				super.paintComponent(g);

			}
		}; 
		Bg1.setLayout(null);
		add(Bg1);
		//라벨------------------------------------------------------------------------
		empty1 = new JLabel();
		empty1.setIcon(new ImageIcon("src/Images/emptyBtn_1.png"));
		empty1.setBounds(231, 157, 134, 176);
		empty1.setLayout(null); 

		empty2 = new JLabel();
		empty2.setIcon(new ImageIcon("src/Images/emptyBtn_2.png"));
		empty2.setBounds(-5, 331, 134, 176);
		empty2.setLayout(null);


		//버튼 ------------------------------------------------------------------------
		Icon bk = new ImageIcon("src/Images/downBtn.png"); 
		book = new JButton();
		book.setBounds(74,330, 289, 176);
		book.setIcon(bk);
		book.setLayout(null);
		book.addActionListener(this);

		Icon rm = new ImageIcon("src/Images/upBtn.png");
		room = new JButton();
		room.setBounds(0, 157,232,176);
		room.setIcon(rm);
		room.setLayout(null); 
		room.addActionListener(this);
		//UI위치 우선순위	

		Bg1.add(empty1);Bg1.add(empty2);
		Bg1.add(room);Bg1.add(book);
		BgPanel= new JScrollPane(Bg1);

		add(BgPanel);

		setContentPane(Bg1);



		setVisible(true);



	}

	public static void main(String[] args) {
		new Join();
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==room) {
			new SeatReservation();
			   dispose();
		}else if(obj==book){
			new BookList();
			  dispose();
		}

	}

}

