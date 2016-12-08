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

	// 필요한것 : 아이디, 비밀번호 칸, 로그인, 회원가입 버튼

	// 입력 칸
	JTextField ID = new JTextField();
	JPasswordField password = new JPasswordField(); // ### 비밀번호 안보이게

	// 버튼들
	JButton login = new JButton("로그인");
	JButton register = new JButton("회원가입"); //###

	// 입력 칸 판넬
	JPanel input = new JPanel();

	// 버튼들 판넬
	JPanel button1 = new JPanel();	//###로그인
	JPanel button2 = new JPanel();   //회원가입
	String message = null;
	
	JLabel idLabel = new JLabel("ID를 입력하세요");
	JLabel passwordLabel = new JLabel("비밀번호를 입력하세요");

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

		// 로그인 화면 해당 하는 거 만들자.
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
		// login 버튼을 누르면 서버에 100;id 메세지를 적어준다.
		if (ob == login) {

			try{   //###
			br = new BufferedReader(
					new FileReader("info.txt"));
			while ((s = br.readLine()) != null) {
				
							//그렇게 한 줄 가져와서.. 스플릿으로 조각조각 내 준다. 파싱 기준은 공백인 \t 로 하자
				split = s.split("\t");
				
				if(split[0].equals(ID.getText()) && split[1].equals(password.getText())){
					//if 문안에 것만 안 건드림.
					flag_login = true;
					this.user.id = ID.getText();
					System.out.println(user.id);
					
					// waitingroom의 id도 설정해줘야지!
					room.id = user.id;
					// 메세지 만들기
					message = CatchMindProtocol.LOGIN_SUCCESS+";" + user.id;
					System.out.println(message);
					// 서버로 보내자..!
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
			JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요");
				
			}
			if(!flag_login){
			JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요");
			}

			// register(회원가입 누를경우)
		}
		else if(ob == register){//###
			reg.init();
			
		}
	}
	
	
}
