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
	// ----- chatting -----
	JPanel chatting = new JPanel();
	JTextArea area = new JTextArea();
	JScrollPane jsP = new JScrollPane();
	JTextField text = new JTextField();

	// StopWatch
//	JTextArea watch = new JTextArea();

	// userlist
	List userListField = null;

	// JTextField userListField = new JTextField();
	// ���ӽ���/ ������ ��ư
	JPanel buttons = null;
	JButton start = null;
	JButton exit = null;

	// ����
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

	// �׷���
	Graphics g = null;
	Graphics2D gg = null;

	// ��ũ��
	JTextField thickness = null;
	int lineThick = 1;

	// ����ǥ
	Integer x1, y1, x2, y2;
	int w, h;
	int ox, oy;

	// �� Į��
	Color lineColor = null;
	BasicStroke bs = null;

	// �޺��ڽ�(�׸� ������ ����)
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

	// init �Լ����� ȭ�鿡 ��������..! ����? ���ӹ���..!
	// �켱 ���ӹ��� ä�� �� �� �ִ� ä�� â, ������ �������� ����â, �����ִ� �������� �ִ� ������!
	public void init() {
		this.setLayout(null);
		this.setBounds(100, 100, 1000, 1000);
		canvas = new JPanel();
		canvas.setBorder(new LineBorder(new Color(0, 0, 0)));
		canvas.setBounds(190, 12, 674, 541);
		canvas.setBackground(Color.WHITE);
		canvas.setLayout(null);
		this.getContentPane().add(canvas, null);

		// ä��â ����
		chatting.setBounds(100, 600, 600, 500);
		chatting.setLayout(null);
		area.setBounds(0, 0, 500, 100);
		text.setBounds(0, 160, 500, 20);
		area.setEditable(false);
		jsP.setAutoscrolls(true);
		jsP.setBounds(0, 0, 500, 160);
		this.getContentPane().add(chatting, null);
		chatting.add(area, null);
		chatting.add(text, null);
		chatting.add(jsP, null);

//		// StopWatch ����.
//		watch.setBounds(0, 350, 100, 200);
//		this.getContentPane().add(watch, null);

		// ���� ����Ʈ
		userListField = new List();
		userListField.setBounds(0, 0, 100, 100);
		this.getContentPane().add(userListField, null);

		// ��ư�� ����
		buttons = new JPanel();
		exit = new JButton("������");
		start = new JButton("���ӽ���");

		buttons.setBounds(650, 600, 300, 300);
		buttons.setLayout(null);
		exit.setBounds(150, 0, 150, 150);
		start.setBounds(0, 0, 150, 150);
		this.getContentPane().add(buttons, null);
		buttons.add(exit);
		buttons.add(start);

		jsP.getViewport().add(area, null);

		// ###�׸��� ����
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

		// Į��� ����
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
		stopwatch.setBorder(new LineBorder(new Color(0, 0, 0)));
		stopwatch.setBounds(0, 150, 100, 50);
		stopwatch.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

		colors.setLayout(new GridLayout(1, 0, 0, 0));
		f1=new Font("����",Font.BOLD,25);
		mm=new JLabel("00");
		colon1=new JLabel(" : ");
		ss=new JLabel("00");
		mm.setFont(f1);
		ss.setFont(f1);
		
		stopwatch.add(mm);
		stopwatch.add(colon1);
		stopwatch.add(ss);
		this.getContentPane().add(stopwatch, null);

		// ��ũ��
		thickness = new JTextField("1");
		thickness.setBounds(0, 0, 150, 20);
		this.getContentPane().add(thickness);

		// �׼Ǹ����� ����
		text.addActionListener(this);
		thickness.addActionListener(null);
		// shape = new JComboBox();
		// shape.addItemListener(new java.awt.event.ItemListener() {
		// public void itemStateChanged(java.awt.event.ItemEvent e) {
		// if (ItemEvent.SELECTED == e.getStateChange()) {
		// type = shape.getSelectedIndex();
		// }
		// }
		// });
		//
		// shape.addItem("��");
		// shape.addItem("��");
		// shape.addItem("�簢");
		// shape.addItem("��");
		// shape.setBounds(10, 200, 100, 50);
		// this.getContentPane().add(shape);

		setVisible(true);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				String msg = CatchMindProtocol.EXIT_GAME_ROOM+";" + id + ";";
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

		// ���콺�� �����̱� �����ϸ� jTextField���� �ؽ�Ʈ�� �����ͼ� ��Ƽ���� �ٲ۴㿡 �� ũ�� ����
		// BasicStroke�� ���� ���� ���ִ� �Ŷ� ����ϰ� ����!
		// lineThick = Integer.parseInt(thickness.getText());
		// float f[] = {lineThick, 0f};
		// bs = new BasicStroke(lineThick,
		// BasicStroke.CAP_ROUND,
		// BasicStroke.JOIN_MITER,
		// 10f,f,0f);

		// ���� ���� ���� ���� �� �� ����
		if (user.gameOn == true) {
			if ((e.getX() > 200 && e.getX() < 870) && (e.getY() > 43 && e.getY() < 505)) {

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
			JOptionPane.showMessageDialog(null, "����Ͻʽÿ�.");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if ((e.getX() > 200 && e.getX() < 870) && (e.getY() > 43 && e.getY() < 505)) {
			w = x2 - x1;
			h = y2 - y1;
			gg.setPaintMode();
			switch (type) {
			case 0:
				g.drawLine(x1, y1, x2, y2);
				break;
			case 1:
				g.drawOval(x1, y1, w, h);
				break;
			case 2:
				g.drawRect(x1, y1, w, h);
				break;
			}
		}

		// ���� ������ ������ ���������..! ���ϱ� x1, y1, x2, y2 ������ �ɰŰ���..!
		try {
			System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
			String msg = CatchMindProtocol.DRAW_PAINT+";" + id + ";" + type + ";" + x1 + ";" + y1 + ";" + x2 + ";" + y2 + ";";
			pw.println(msg);
			pw.flush();
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if ((e.getX() > 200 && e.getX() < 870) && (e.getY() > 43 && e.getY() < 505)) { // ###
			ox = x2;
			oy = y2;

			x2 = e.getX();
			y2 = e.getY();

			if ((e.getX() > 200 && e.getX() < 870) && (e.getY() > 43 && e.getY() < 505)) {// ###
				switch (type) {
				case 0:
					line();
					break;
				case 1:
					oval();
					break;
				case 2:
					rect();
					break;
				case 3:
					pen();
					break;
				}
			}
		} else { // ###
			x1 = null;
			y1 = null;
			x2 = null;
			y2 = null;
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
			String msg = CatchMindProtocol.DRAW_PAINT+";" + id + ";" + type + ";" + x1 + ";" + y1 + ";" + x2 + ";" + y2 + ";";
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
			String msg = CatchMindProtocol.GAME_ROOM_CHAT+";" + id + ";" + text.getText();
			text.setText("");
			try {
				pw.println(msg);
				pw.flush();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		} else if (ob == start) {
			// ���� start �ߴٴ°� ��������ϴµ� �̰� �켱 ���常 �� �� �ְ� ���������. ���� ���� ������ ���� ���̴� ��
			// ����� ���������� ���� ������ �ٲ�����.
			// input�� ������� �޴´ٰ� ���� �ϸ� �޼��� �ȿ� ���� ���� �ٲٴ� ���� �� �־�� �� ���̴�. �̰� �켱
			// ����غ��� ������ ������ ������� ���� �غ���.
			if (user.host == true) {
				// JOptionPane.showMessageDialog(null, "������ �½��ϴ�.");
				// �̰� ��ſ� ������ ���� �����ߴٴ� �޼����� ������ �������� �ش� �濡 ����ִ� �������� ������ ���¸�
				// true�� �� �ٲ������..!
				if (user.gameOn == false) {

					user.gameOn = true;

					String msg = CatchMindProtocol.GAME_START+";" + id + ";" + user.roomName + ";";
					try {
						pw.println(msg);
						pw.flush();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			} else { // ������ �ƴϸ�
				JOptionPane.showMessageDialog(null, "������ �ƴմϴ�.");
			}
		} else if (ob == exit) {
			String msg = CatchMindProtocol.EXIT_GAME_ROOM+";" + id + ";";
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
			g.setColor(new Color(255, 255, 255));
		} else if (ob == ersAll) {
			canvas.repaint();
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

}
