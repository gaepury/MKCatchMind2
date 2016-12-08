import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame implements ActionListener {

	PrintWriter pw = null;
	
	String s;
	String[] split;
	boolean flag_login;
	BufferedReader br = null;

	// �ʿ��Ѱ� : ���̵�, ��й�ȣ ĭ, �α���, ȸ������ ��ư

	// �Է� ĭ
	JTextField ID = new JTextField();
	JPasswordField password = new JPasswordField(); // ### ��й�ȣ �Ⱥ��̰�

	// ��ư��
	JButton login = new JButton("�α���");
	JButton register = new JButton("ȸ������"); //###

	// �Է� ĭ �ǳ�
	JPanel input = new JPanel();

	// ��ư�� �ǳ�
	JPanel button1 = new JPanel();	//###�α���
	JPanel button2 = new JPanel();   //ȸ������
	String message = null;
	
	JLabel idLabel = new JLabel("ID�� �Է��ϼ���");
	JLabel passwordLabel = new JLabel("��й�ȣ�� �Է��ϼ���");

	// bwjassd
	UserInfo user;
	
	Register reg = null;
	WaitingRoom room = null;
	public LoginView(PrintWriter pw, UserInfo user) {
		try {
			this.user = user;
			room = new WaitingRoom(pw, user);
			reg = new Register();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// TODO Auto-generated constructor stub
		this.pw = pw;

		// �α��� ȭ�� �ش� �ϴ� �� ������.
		this.getContentPane().setBackground(new Color(236, 197, 0));
		this.setResizable(true);
		this.setBounds(1000, 400, 400, 400);
		this.getContentPane().setLayout(null);
		input.setLayout(null);
		button1.setLayout(null);
		button2.setLayout(null);

		input.setBounds(10, 10, 350, 200);
		button1.setBounds(10, 220, 350, 90);
		button2.setBounds(185, 220, 175, 90);

		ID.setBounds(10, 40, 330, 40);
		idLabel.setBounds(10,10,330,20);
		password.setBounds(10, 130, 330, 40);
		passwordLabel.setBounds(10, 100, 330, 20);
		login.setBounds(10, 10, 150, 60);
		register.setBounds(10, 10, 150, 60);
		//register.setBounds(60, 10, 40, 30);
		
		this.getContentPane().add(input, null);
		this.getContentPane().add(button1, null);
		this.getContentPane().add(button2, null);
		input.add(ID, null);
		input.add(password, null);
		input.add(idLabel, null);
		input.add(passwordLabel, null);
		button1.add(login, null);
		button2.add(register, null);
		//buttons.add(register, null);

		this.setVisible(true);
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		ID.addActionListener(this);
		password.addActionListener(this);
		login.addActionListener(this);
		register.addActionListener(this);
		//register.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		// login ��ư�� ������ ������ 100;id �޼����� �����ش�.
		if (ob == login) {

			try{   //###
			br = new BufferedReader(
					new FileReader("info.txt"));
			while ((s = br.readLine()) != null) {
				
							//�׷��� �� �� �����ͼ�.. ���ø����� �������� �� �ش�. �Ľ� ������ ������ \t �� ����
				split = s.split("\t");
				
				if(split[0].equals(ID.getText()) && split[1].equals(password.getText())){
					//if ���ȿ� �͸� �� �ǵ帲.
					flag_login = true;
					this.user.id = ID.getText();
					System.out.println(user.id);
					
					// waitingroom�� id�� �����������!
					room.id = user.id;
					// �޼��� �����
					message = CatchMindProtocol.LOGIN_SUCCESS+";" + user.id;
					System.out.println(message);
					// ������ ������..!
					room.init();
					try {
						pw.println(message);
						pw.flush();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}	
			}
			setVisible(false);
			}catch(IOException ex){
			JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� Ȯ���ϼ���");
				
			}
			if(!flag_login){
			JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� Ȯ���ϼ���");
			}

			// register(ȸ������ �������)
		}
		else if(ob == register){//###
			reg.init();
			
		}
	}
	
	
}
