package LibraryManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class BookShelf extends JFrame{
	
	JPanel bg;
	JScrollPane BgPan;
	JButton backBtn;
	ImageIcon icon ;

	public BookShelf() {
	   super("BookShelf");
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	      setBounds(800, 100, 368, 598);
	      setLayout(null);
	      setResizable(false);

	      // -------배경이 될 이미지 아이콘 생성
	      icon =  new ImageIcon("src/Images/Bookshelf.png");

	      // 배경 Panel(background) 생성을 만들고, ContentPane(주 내용창)으로 지정
	      bg = new JPanel() {

	         public void paintComponent(Graphics g) {
	            Dimension d = getSize();
	            g.drawImage(icon.getImage(), 0, -10, 368, 598, null);
	            setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
	            super.paintComponent(g);
	         }
	      };

	      bg.setLayout(null);
	      
	    backBtn = new JButton();
		backBtn.setBounds(141, 445, 86, 125);
		backBtn.setIcon(new ImageIcon("src/Images/shelfBackBtn.png"));
		backBtn.setPressedIcon(new ImageIcon("src/Images/shelfBackBtnPress.png"));
		backBtn.setBorderPainted(false); //버튼테두리 사용안함
		backBtn.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
		backBtn.setIconTextGap(0);
		backBtn.setFocusPainted(false); //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookList();
				dispose();
				
			}
		});
		add(backBtn);
	      
	   
			BgPan = new JScrollPane(bg);

			add(BgPan);
			bg.add(backBtn);

			setContentPane(bg);
	      setVisible(true);
	   
	}
	
	public static void main(String[] args) {
		new BookShelf();

	}

}
