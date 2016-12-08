import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener {

	// 필요한것 : 아이디, 비밀번호 칸, 로그인, 회원가입 버튼
	
	
	BufferedWriter writer = null;
	PrintWriter printw = null;

	UserInfo user = null;
	// 입력 칸
	JTextField ID = new JTextField();
	JPasswordField pw = new JPasswordField();
	JPasswordField pwCmp = new JPasswordField();

	// 버튼들
	JButton ok = new JButton("회원가입");
	JButton quit = new JButton("나가기"); // ###
	// JButton register = new JButton();

	// 입력 칸 판넬
	JPanel input = new JPanel();
	// 버튼들 판넬
	JPanel button1 = new JPanel();
	JPanel button2 = new JPanel();
	String message = null;

	JLabel idLabel = new JLabel("ID를 입력하세요");
	JLabel pwLabel = new JLabel("비밀번호를 입력하세요");
	JLabel pwCmpLabel = new JLabel("비밀번호를 확인하겠습니다.");

	public Register(PrintWriter printw) throws Exception {
		// TODO Auto-generated constructor stub
		this.pw = pw;

	}

	public Register(PrintWriter printw, UserInfo user) {
		this.printw = printw;
		this.user = user;
	}

	public Register() {
		
		// TODO Auto-generated constructor stub
	}

	public void init() {

		// TODO Auto-generated constructor stub
		// this.pw = pw;

		// 로그인 화면 해당 하는 거 만들자.
		this.getContentPane().setBackground(new Color(236, 197, 0));
		this.setResizable(true);
		this.setBounds(1000, 400, 400, 400);
		this.getContentPane().setLayout(null);
		input.setLayout(null);
		button1.setLayout(null);
		button2.setLayout(null);

		input.setBounds(10, 10, 350, 210);
		button1.setBounds(10, 230, 175, 90);
		button2.setBounds(185, 230, 175, 90);

		ID.setBounds(10, 30, 330, 40);
		idLabel.setBounds(10, 10, 330, 20);

		pw.setBounds(10, 90, 330, 40);
		pwCmp.setBounds(10, 160, 330, 40);
		pwLabel.setBounds(10, 70, 330, 20);
		pwCmpLabel.setBounds(10, 140, 330, 20);
		ok.setBounds(10, 10, 150, 60);
		quit.setBounds(10, 10, 150, 60);
		// register.setBounds(60, 10, 40, 30);

		this.getContentPane().add(input, null);
		this.getContentPane().add(button1, null);
		this.getContentPane().add(button2, null);
		input.add(ID, null);
		input.add(pw, null);
		input.add(pwCmp, null);
		input.add(idLabel, null);
		input.add(pwLabel, null);
		input.add(pwCmpLabel, null);
		button1.add(ok, null);
		button2.add(quit, null);

		// buttons.add(register, null);

		this.setVisible(true);
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		ID.addActionListener(this);
		pw.addActionListener(this);
		pwCmp.addActionListener(this);
		ok.addActionListener(this);
		quit.addActionListener(this);
		// register.addActionListener(this);

	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		if(ob == ok)
		{
			if(ID.getText() == null || ID.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			}
			else
			{
				if(pw.getPassword().toString()==null||pwCmp.getPassword().toString()==null)
				{
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
				}
				if(!pw.getText().equals(pwCmp.getText()))
				{
					JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
				}
				else{
					try{
					    writer = new BufferedWriter(new FileWriter("C:\\Users\\kpyo\\workspace\\MKCatchMind2\\info.txt"));
						
					    writer.write(ID.getText()+"\t"+pw.getText());
					    writer.newLine(); // \r\n 써도 되고 newLine 함수를 사용해 다음라인으로 내려도 된다
					    writer.close();
					    
					}catch (FileNotFoundException ex) {
			            ex.printStackTrace();
			        } catch (IOException ep) {
			            ep.printStackTrace();
			        }
					
					dispose();
				}
//				else{
//					
				}
				
			}
		
		

		if(ob == quit){
			dispose();
		}
	}
}
	

	// Object ob = e.getSource();
	// // login 버튼을 누르면 서버에 100;id 메세지를 적어준다.
	// if (ob == login) {
	//
	// this.user.id = ID.getText();
	// System.out.println(user.id);
	//
	//
	// // waitingroom의 id도 설정해줘야지!
	// room.id = user.id;
	// // 메세지 만들기
	// message = "100;" + user.id;
	// System.out.println(message);
	// // 서버로 보내자..!
	// room.init();
	// try {
	// pw.println(message);
	// pw.flush();
	// } catch (Exception e2) {
	// // TODO: handle exception
	// }
	// // register(회원가입 누를경우)
	// }
	//
	//


