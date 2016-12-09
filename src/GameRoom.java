import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GameRoom extends JFrame implements MouseMotionListener, MouseListener, ActionListener {

	PrintWriter pw = null;
	JPanel PaintPanel = null;
	// JComboBox jComboBox = null;
	JPanel canvas = null;
	
	// ----- Problem ------
	JPanel problem=new JPanel();
	JLabel problem_text =new JLabel("    정답 : ");
	JLabel text_problem=new JLabel();
	
	
	
	// -------hint --------
	JPanel hint=new JPanel();
	JLabel hint_text =new JLabel("    힌트: ");
	JLabel text_hint=new JLabel();
	
	
	// ----- chatting -----
	JPanel chatting = new JPanel();
	JTextArea area = new JTextArea();
	JScrollPane jsP = new JScrollPane();
	JTextField text = new JTextField();

	// StopWatch
	// JTextArea watch = new JTextArea();

	// userlist
	List userListField = null;
	List leftUserField = null;
	List rightUserField = null;
	//item
	JButton item1 =new JButton("아이템1");
	JButton item2 =new JButton("아이템2");
	
	// JTextField userListField = new JTextField();
	// 게임시작/ 나가기 버튼
	JPanel buttons = null;
	JButton start = null;
	JButton exit = null;
	
	// 왼편 오른편
	JButton left = null;
	JButton right = null;
	
	// 색들
	JPanel colors = null;
	JButton white = null;
	JButton red = null;
	JButton orange = null;
	JButton yellow = null;
	JButton green = null;
	JButton blue = null;
	JButton black = null;
	JButton eraser = null;
	JButton ersAll = null; // ###

	JPanel tool = null;
	JButton line = null;
	JButton round = null;
	JButton square = null;
	JButton free = null;

	// 그래픽
	Graphics g = null;
	Graphics2D gg = null;

	// 선크기
	JTextField thickness = null;
	int lineThick = 1;

	// 선좌표
	Integer x1, y1, x2, y2;
	int w, h;
	int ox, oy;

	// 선 칼라
	Color lineColor = null;
	BasicStroke bs = null;

	// 콤보박스(그릴 도형의 종류)
	// JComboBox shape = null;
	int type = 0;
	int tmp = 0;

	// StopWatch
	JPanel stopwatch = null;
	// int count; // stopwatch count
	JLabel mm, ss, colon1;
	Font f1;

	// id
	String id = "";

	// User
	UserInfo user;

	public GameRoom() {
	
	}

	public GameRoom(PrintWriter pw) {
		// TODO Auto-generated constructor stub
		this.pw = pw;

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public GameRoom(PrintWriter pw, UserInfo user) {
		this.pw = pw;
		this.user = user;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	// init 함수에서 화면에 보여주자..! 뭐를? 게임방을..!
	// 우선 게임방은 채팅 할 수 있는 채팅 창, 게임이 보여지는 게임창, 들어와있는 유저들이 있는 유저들!
	public void init() {
		this.setLayout(null);
		this.setBounds(100, 100, 1000, 1000);
		canvas = new JPanel();
		canvas.setBorder(new LineBorder(new Color(0, 0, 0)));
		canvas.setBounds(190, 100, 670, 540);
		canvas.setBackground(Color.WHITE);
		canvas.setLayout(null);
		this.getContentPane().add(canvas, null);
		
		//문제 
		problem.setBounds(190,20,130,50);
		problem.setBorder(new LineBorder(new Color(0,0,0),2));
		problem.setLayout(new GridLayout(1,2));
		problem_text.setBounds(100,20,50,50);
		text_problem.setBounds(120,20,80,50);
		problem.add(problem_text);
		problem.add(text_problem);
		this.getContentPane().add(problem,null);
		
		//힌트
		hint.setBounds(730,20,130,50);
		hint.setBorder(new LineBorder(new Color(0,0,0),2));
		hint.setLayout(new GridLayout(1,2));
		hint_text.setBounds(100,20,50,50);
		text_hint.setBounds(120,20,80,50);
		hint.add(hint_text);
		hint.add(text_hint);
		this.getContentPane().add(hint,null);
		
		//Item 버튼
		item1.setBounds(110, 560, 80, 80);
		item1.addActionListener(this);
		item2.setBounds(110, 490, 80, 80);
		item2.addActionListener(this);
		this.getContentPane().add(item2, null);
		this.getContentPane().add(item1, null);
		// 채팅창 넣자
		chatting.setBounds(190, 640, 670, 500);
		chatting.setLayout(null);
		area.setBounds(0, 0, 570, 160);
		text.setBounds(0, 160, 570, 20);
		area.setEditable(false);
		jsP.setAutoscrolls(true);
		jsP.setBounds(0, 0, 570, 160);
		this.getContentPane().add(chatting, null);
		chatting.add(area, null);
		chatting.add(text, null);
		chatting.add(jsP, null);

		// // StopWatch 넣자.
		// watch.setBounds(0, 350, 100, 200);
		// this.getContentPane().add(watch, null);

		// 유저 리스트
		userListField = new List();
		userListField.setBounds(90, 640, 100, 180);
		this.getContentPane().add(userListField, null);

		// 왼쪽 유저 리스트
		leftUserField = new List();
		leftUserField.setBounds(0, 200, 100, 200);
		this.getContentPane().add(leftUserField, null);

		// 오른쪽 유저 리스트
		rightUserField = new List();
		rightUserField.setBounds(880, 200, 100, 200);
		this.getContentPane().add(rightUserField, null);

		// 버튼들 넣자
		buttons = new JPanel();
		exit = new JButton("나가기");
		start = new JButton("게임시작");

		// 왼편 오른편 버튼 넣자.
		left = new JButton("팀1");
		right = new JButton("팀2");
		this.getContentPane().add(left, null);
		this.getContentPane().add(right, null);

		left.setBounds(0, 100, 100, 100);
		right.setBounds(880, 100, 100, 100);

		buttons.setBounds(760, 640, 100, 180);
		buttons.setLayout(new GridLayout(2,1));
		exit.setBounds(0, 0, 100, 25);
		start.setBounds(50, 0, 100, 25);
		this.getContentPane().add(buttons, null);
		buttons.add(start);
		buttons.add(exit);

		jsP.getViewport().add(area, null);

		// ###그리기 도구
		tool = new JPanel();
		tool.setBorder(new LineBorder(new Color(255, 255, 255)));
		tool.setBounds(120, 190, 70, 280);
		tool.setLayout(new GridLayout(0, 1, 0, 0));
		this.add(tool);
		
		line = new JButton(new ImageIcon("line.png"));
		line.setBackground(Color.WHITE);
		line.addActionListener(this);
		tool.add(line);

		round = new JButton(new ImageIcon("circle.png"));
		round.setBackground(Color.WHITE);
		round.addActionListener(this);
		tool.add(round);

		square = new JButton(new ImageIcon("rect.png"));
		square.setBackground(Color.WHITE);
		square.addActionListener(this);
		tool.add(square);

		free = new JButton(new ImageIcon("pencil.png"));
		free.setBackground(Color.WHITE);
		free.addActionListener(this);
		tool.add(free);

		// 칼라들 집합
		colors = new JPanel();
		colors.setBorder(new LineBorder(new Color(255, 255, 255)));
		colors.setBounds(4, 464, 666, 73);
		colors.setLayout(new GridLayout(1, 0, 0, 0));
		canvas.add(colors);

		// White
		white = new JButton("");
		white.setBackground(Color.WHITE);
		white.addActionListener(this);
		colors.add(white);

		// Red
		red = new JButton("");
		red.setBackground(Color.RED);
		red.addActionListener(this);
		colors.add(red);

		// orange
		orange = new JButton("");
		orange.setBackground(Color.ORANGE);
		orange.addActionListener(this);
		colors.add(orange);

		// yellow
		yellow = new JButton("");
		yellow.setBackground(Color.YELLOW);
		yellow.addActionListener(this);
		colors.add(yellow);

		// green
		green = new JButton("");
		green.setBackground(Color.GREEN);
		green.addActionListener(this);
		colors.add(green);

		// Blue
		blue = new JButton("");
		blue.setBackground(Color.BLUE);
		blue.addActionListener(this);
		colors.add(blue);

		// Black
		black = new JButton("");
		black.setBackground(Color.BLACK);
		black.addActionListener(this);
		colors.add(black);

		// ###
		eraser = new JButton(new ImageIcon("eraser.png"));
		eraser.setBackground(new Color(255, 255, 225));
		eraser.addActionListener(this);
		colors.add(eraser);

		// ###
		ersAll = new JButton(new ImageIcon("eraserAll.png"));
		ersAll.setBackground(new Color(255, 255, 255));
		ersAll.addActionListener(this);
		colors.add(ersAll);

		stopwatch = new JPanel();
		stopwatch.setBorder(new LineBorder(new Color(0, 0, 0),3));
		stopwatch.setBounds(420, 30, 200, 70);
		stopwatch.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		colors.setLayout(new GridLayout(1, 0, 0, 0));
		f1 = new Font("바탕", Font.BOLD, 40);
		mm = new JLabel("00");
		colon1 = new JLabel(" : ");
		ss = new JLabel("00");
		mm.setFont(f1);
		ss.setFont(f1);

		stopwatch.add(mm);
		stopwatch.add(colon1);
		stopwatch.add(ss);
		this.getContentPane().add(stopwatch, null);

		// 선크기
//		thickness = new JTextField("1");
//		thickness.setBounds(0, 0, 150, 20);
//		this.getContentPane().add(thickness);

		// 액션리스너 설정
		text.addActionListener(this);
//		thickness.addActionListener(null);
		// shape = new JComboBox();
		// shape.addItemListener(new java.awt.event.ItemListener() {
		// public void itemStateChanged(java.awt.event.ItemEvent e) {
		// if (ItemEvent.SELECTED == e.getStateChange()) {
		// type = shape.getSelectedIndex();
		// }
		// }
		// });
		//
		// shape.addItem("선");
		// shape.addItem("원");
		// shape.addItem("사각");
		// shape.addItem("펜");
		// shape.setBounds(10, 200, 100, 50);
		// this.getContentPane().add(shape);

		setVisible(true);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				String msg = CatchMindProtocol.EXIT_GAME_ROOM + ";" + id + ";";
				try {
					pw.println(msg);
					pw.flush();
					setVisible(false);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		g = getGraphics();
		gg = (Graphics2D) g;
		System.out.println("gg" + gg);

		// float f[] = { 1, 0f };
		// bs = new BasicStroke(1, BasicStroke.CAP_ROUND,
		// BasicStroke.JOIN_MITER, 10f, f, 0f);

		float f[] = { 1, 0f };
		bs = new BasicStroke(5f);

		start.addActionListener(this);
		exit.addActionListener(this);
		left.addActionListener(this);
		right.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		// 마우스가 움직이기 시작하면 jTextField에서 텍스트를 가져와서 인티져로 바꾼담에 선 크기 결정
		// BasicStroke에 붓을 설정 해주는 거랑 비슷하게 이해!
		// lineThick = Integer.parseInt(thickness.getText());
		// float f[] = {lineThick, 0f};
		// bs = new BasicStroke(lineThick,
		// BasicStroke.CAP_ROUND,
		// BasicStroke.JOIN_MITER,
		// 10f,f,0f);

		// 게임 시작 했을 때만 할 수 있음
		if (user.gameOn == true && !user.block) {
			if ((e.getX() > 200 && e.getX() < 870) && (e.getY() > 43+88 && e.getY() < 505+88)) {

				gg.setStroke(bs);

				x1 = e.getX();
				y1 = e.getY();

				x2 = x1;
				y2 = y1;
				ox = x1;
				oy = y1;

				w = 0;
				h = 0;
			}

		} else {
			JOptionPane.showMessageDialog(null, "대기하십시오.");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	      // TODO Auto-generated method stub
	      
	      gg.setPaintMode();
	      if(((x1 > 200 && x1 < 870) && (y1 > 43+88 && y1 < 505+88))&&((x2 > 200 && x2 < 870) && (y2 > 43+88 && y2 < 505+88))){
	         w = x2-x1;
	         h = y2-y1;
	         switch(type){
	         case 0 : g.drawLine(x1, y1, x2, y2);break;
	         case 1 : g.drawOval(x1, y1, w, h);break;
	         case 2 : g.drawRect(x1, y1, w, h);break;
	         }   
	      }else{
	         return;
	      }
	      // 이제 서버로 정보들 보내줘야지..! 보니까 x1, y1, x2, y2 보내면 될거같다..! 
	      try {
	         System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
	         int color_info = getColorInfo();
	         String msg = "300;"+ id + ";" + type + ";" +x1+";"+y1+";"+x2+";"+y2+";" + color_info +";";
	         pw.println(msg);
	         pw.flush();
	      } catch (Exception e2) {
	         // TODO: handle exception
	      }
	   }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		ox = x2;
		oy = y2;

		x2 = e.getX();
		y2 = e.getY();
		
		if(((x1 > 200 && x1 < 870) && (y1 > 43+88 && y1 < 505+88))&&((x2 > 200 && x2 < 870) && (y2 > 43+88 && y2 < 505+88))){
	         switch(type){
	         case 0 : line(); break;
	         case 1 : oval(); break;
	         case 2 : rect(); break;
	         case 3 : pen(); break;
	         }   
	      }else{
	         return;
	      }
	}

	public void line() {
		g.setXORMode(Color.white);
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x1, y1, ox, oy);
	}

	public void oval() {
		g.setXORMode(Color.white);
		w = x2 - x1;
		h = y2 - y1;
		g.drawOval(x1, y1, w, h);
		g.drawOval(x1, y1, (ox - x1), (oy - y1));
	}

	public void rect() {
		g.setXORMode(Color.white);
		w = x2 - x1;
		h = y2 - y1;
		g.drawRect(x1, y1, w, h);
		g.drawRect(x1, y1, (ox - x1), (oy - y1));
	}

	public void pen() {
		g.drawLine(x1, y1, x2, y2);
		try {
			System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
			int color_info = getColorInfo();
			String msg = CatchMindProtocol.DRAW_PAINT + ";" + id + ";" + type + ";" + x1 + ";" + y1 + ";" + x2 + ";"
					+ y2 + ";" + color_info + ";";
			pw.println(msg);
			pw.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}

		x1 = x2;
		y1 = y2;

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		if (ob == text) {
			String msg = CatchMindProtocol.GAME_ROOM_CHAT + ";" + id + ";" + text.getText();
			text.setText("");
			try {
				pw.println(msg);
				pw.flush();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		} else if (ob == start) {
			// 게임 start 했다는거 보내줘야하는데 이거 우선 방장만 할 수 있게 만들어주자. 현재 유저 정보가 있을 것이다 방
			// 만들기 눌렀을때만 유저 정보를 바꿔주자.
			// input을 쓰레드로 받는다고 가정 하면 메세지 안에 방장 정보 바꾸는 것이 들어가 있어야 할 것이다. 이거 우선
			// 고민해보고 아직은 쓰레드 고민하지 말고 해보자.
			if (user.host == true) {
				// JOptionPane.showMessageDialog(null, "방장이 맞습니다.");
				// 이거 대신에 방장이 게임 시작했다는 메세지를 보내면 서버에서 해당 방에 들어있는 유저들의 게임중 상태를
				// true로 다 바꿔줘야함..!
				if (user.gameOn == false) {

					user.gameOn = true;

					String leftId = "";
					String rightId = "";
					// 맨 마지막에 세미콜론 없음 앞에는 ;있음 즉 ;문기;영현;와우 이런식
					for (int i = 0; i < leftUserField.getItemCount(); i++) {
						leftId = leftId + ";" + leftUserField.getItem(i);
					}
					for (int i = 0; i < rightUserField.getItemCount(); i++) {
						rightId = rightId + ";" + rightUserField.getItem(i);
					}

					String msg = CatchMindProtocol.GAME_START + ";" + id + ";" + user.roomName + leftId + rightId;
					try {
						pw.println(msg);
						pw.flush();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			} else { // 방장이 아니면
				JOptionPane.showMessageDialog(null, "방장이 아닙니다.");
			}
		} else if(ob==item1){
			String msg=CatchMindProtocol.ITEM_1 +";"+user.roomName+";";
			try {
				pw.println(msg);
				pw.flush();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		} else if (ob == left) {

			String msg = CatchMindProtocol.DICISION_TEAM + ";" + id + ";" + "left";
			try {
				pw.println(msg);
				pw.flush();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		} else if (ob == right) {

			String msg = CatchMindProtocol.DICISION_TEAM + ";" + id + ";" + "right";

			try {
				pw.println(msg);
				pw.flush();
			} catch (Exception e2) {
				// TODO: handle exception
			}

		} else if (ob == exit) {
			String msg = CatchMindProtocol.EXIT_GAME_ROOM + ";" + id + ";";
			JOptionPane.showConfirmDialog(null, "방을 나가시겠습니까?", "방 나가기", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			try {
				pw.println(msg);
				pw.flush();
				setVisible(false);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		} else if (ob == white) {
			type = tmp;
			bs = new BasicStroke(5f);
			g.setColor(Color.white);
		} else if (ob == red) {
			type = tmp;
			bs = new BasicStroke(5f);
			g.setColor(Color.red);
		} else if (ob == orange) {
			type = tmp;
			bs = new BasicStroke(5f);
			g.setColor(Color.orange);
		} else if (ob == yellow) {
			type = tmp;
			bs = new BasicStroke(5f);
			g.setColor(Color.yellow);
		} else if (ob == green) {
			type = tmp;
			bs = new BasicStroke(5f);
			g.setColor(Color.green);
		} else if (ob == blue) {
			type = tmp;
			bs = new BasicStroke(5f);
			g.setColor(Color.blue);
		} else if (ob == black) {
			type = tmp;
			bs = new BasicStroke(5f);
			g.setColor(Color.black);
		} else if (ob == eraser) {
			tmp = type;
			bs = new BasicStroke(10f);
			type = 3;
			g.setColor(Color.white);
		} else if (ob == ersAll) {
			canvas.repaint();
			String msg = CatchMindProtocol.DRAW_PAINT + ";" + id + ";" + 4 + ";" + 0 + ";" + 0 + ";" + 0 + ";" + 0 + ";"
					+ 8 + ";";
			pw.println(msg);
			pw.flush();
		} else if (ob == line) {
			type = 0;
			tmp = 0;
		} else if (ob == round) {
			type = 1;
			tmp = 1;
		} else if (ob == square) {
			type = 2;
			tmp = 2;
		} else if (ob == free) {
			type = 3;
			tmp = 3;
		}
	}

	int getColorInfo() {
		if (g.getColor() == Color.white) {
			return 0;
		} else if (g.getColor() == Color.red) {
			return 1;
		} else if (g.getColor() == Color.orange) {
			return 2;
		} else if (g.getColor() == Color.yellow) {
			return 3;
		} else if (g.getColor() == Color.green) {
			return 4;
		} else if (g.getColor() == Color.blue) {
			return 5;
		} else if (g.getColor() == Color.black) {
			return 6;
		} else if (g.getColor() == Color.white) {
			return 7;
		}
		return -1;

	}
	
	public static void main(String[] args) {
		new GameRoom().init();
	}
}
