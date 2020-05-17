package LibraryManager;



import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.Timer;

public class SeatReservation extends JFrame implements ActionListener {
   JScrollPane scrollPane;
   JPanel backGround, seatPan;
   JLabel gif;
   JButton seat[] = new JButton[12];// 열람실 좌석 표시
   ImageIcon icon, btn, textGif,seatGif;
   Boolean[] status = new Boolean[seat.length]; // 각 버튼의 상태 true-빈자리 false-사용
   JButton backBtn;
   Timer timerSec = new Timer();

   int hour;
   int min;
   int sec;

   public SeatReservation() {
      super("SeatReservation");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setBounds(800, 100, 368, 598);
      setLayout(null);
      setResizable(false);

      // -------배경이 될 이미지 아이콘 생성
      icon =  new ImageIcon("src/Images/ReservationBg.png");

      // 배경 Panel(background) 생성을 만들고, ContentPane(주 내용창)으로 지정
      backGround = new JPanel() {

         public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(icon.getImage(), -5, -30, 368, 598, null);
            setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
            super.paintComponent(g);
         }
      };

      backGround.setLayout(null);
      // 초기 열람실 상태 : 모두 빈자리
      for(int i =0; i<status.length; i++ )
         status[i] = true;  //boolean true 로 좌석 빈자리를 생성 



      //버튼 패널 부착(for문)GridLaout사용
      seatGif = new ImageIcon("src/Images/duck.gif");
      GridLayout grid = new GridLayout(4, 3);
      Color c = new Color(41, 47, 71);
      seatPan = new JPanel();
      seatPan.setBounds(1, 119, 360, 360);
      seatPan.setBackground(c);
      grid.setVgap(5);
      grid.setHgap(5);
      seatPan.setLayout(grid);
      backGround.add(seatPan);
      //버튼 이미지 등록
      seat = new JButton[12];
      // 포문으로 각각의 버튼 이미지를 생성
      for (int i = 0; i < seat.length; i++) {
         btn = new ImageIcon("src/Images/seat1.png");
         seat[i] = new JButton();
         seat[i].setIcon(btn);

         // seatPan패널에 포문에 seat[i]를 컴포넌트에 담아줌

         Container c1 = getContentPane();


         c1.add(seat[i]);
         seatPan.add(seat[i]);
         // 감지기
         seat[i].addActionListener(this);

      }
      backBtn = new JButton();
      backBtn.setBounds(56, 495, 250, 54);
      backBtn.setIcon(new ImageIcon("src/Images/backBtn.png"));
      backBtn.setPressedIcon(new ImageIcon("src/Images/backBtnPress.png"));
      backBtn.setBorderPainted(false); //버튼테두리 사용안함
      backBtn.setContentAreaFilled(false); //버튼 내용영역 채움 사용안함
      backBtn.setIconTextGap(0);
      backBtn.setFocusPainted(false); //버튼이 선택(focus)되었을때 생기는 테두리 사용안함
      backBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
           dispose();
            new Join();


         }
      });
      backGround.add(backBtn);

      // 라벨(gif)
      textGif = new ImageIcon("src/Images/textGif.gif");
      gif = new JLabel();
      gif.setBounds(0, 0, 368, 115);
      gif.setIcon(textGif);
      gif.setLayout(null);
      add(gif);
      backGround.add(gif);

      // 컴포넌트 담기
      //      seatPan.add(gif);
      scrollPane = new JScrollPane(backGround);
      add(scrollPane);
      setContentPane(scrollPane);

      setVisible(true);

   }


   @Override
   public void actionPerformed(ActionEvent e) {
      //자리가 비어 있으면 
      for(int i =0; i<seat.length; i++) {

         if(status[i]==true) {
            if(e.getSource().equals(seat[i])) {
               int result = JOptionPane.showConfirmDialog(null, "빈자리입니다\n예약하시겠습니까?","좌석선택 ",
                     JOptionPane.YES_NO_OPTION);

               if(result==0) {
                  //                  JOptionPane.showConfirmDialog(null, "사용시간은 3시간 입니다 ");
                  JOptionPane.showMessageDialog(null, "사용시간은 30초 입니다");
                  seat[i].setIcon(seatGif);
                  status[i] = false;

                  hour = 0;//시
                  min = 0;//분
                  sec = 60;//초
//                  timerSec.cancel();// 타이머 객체 종료
//                  timerSec = new Timer();// 타이머 객체 다시 생성
                  timerSec.scheduleAtFixedRate(new TimerTask() { 

                     public  void run() {// 실행할 구문     

            
                     int choice = JOptionPane.showConfirmDialog(null, "연장 하시겠습니까?", "연장/퇴실",
                           JOptionPane.YES_NO_OPTION);
                     if (choice == 0) {
                        JOptionPane.showMessageDialog(null, "30초 연장되었습니다.");
                     } else if (choice == 1) {
                        JOptionPane.showMessageDialog(null, "연장 취소");
                        for(int i = 0; i<seat.length; i++) {
                        seat[i].setIcon(btn);
                        status[i] =true;
                        }
                     }
               
               }
                  }, 30000, 30000);
            }//자리가 사용중이면 
            }
            }
            else if(status[i]==false) {

            if(e.getSource().equals(seat[i])) {
               int result = JOptionPane.showConfirmDialog(null, "반납 하시겠습니까?","연장/퇴실 ",
                     JOptionPane.YES_OPTION);
            if(result==0) {
                  JOptionPane.showConfirmDialog(null, "이용해 주셔서 감사합니다.  ","", JOptionPane.YES_NO_OPTION);
                  timerSec.cancel();
                  timerSec = new Timer();
                  seat[i].setIcon(btn);
                  status[i] =true;

               }else if (result == 1) {
                  int choice = JOptionPane.showConfirmDialog(null, "연장 하시겠습니까?", "연장/퇴실",
                        JOptionPane.YES_NO_OPTION);
                  
                  if (choice == 0) {
                     
                     JOptionPane.showMessageDialog(null, "30초 연장되었습니다.");
                     timerSec.scheduleAtFixedRate(new TimerTask() {
                        
                        @Override
                        public void run() {
                        
                           int choice = JOptionPane.showConfirmDialog(null, "연장 하시겠습니까?", "연장/퇴실",
                                 JOptionPane.YES_NO_OPTION);
                           if (choice == 0) {
                              JOptionPane.showMessageDialog(null, "30초 연장되었습니다.");
                           } else if (choice == 1) {
                              JOptionPane.showMessageDialog(null, "연장 취소");
                           
                           }
                        }
                     }, 30000, 30000);
                  } else if (choice == 1) {
                     JOptionPane.showMessageDialog(null, "연장 취소");
                     timerSec.cancel();
                     timerSec = new Timer();
                     seat[i].setIcon(btn);
                     status[i] =true;
                  }
               }
            }
         }
      }
   }

   
   

   public static void main(String[] args) {
      new SeatReservation();

   }

   
}


