import java.io.PrintWriter;

public class UserInfo {
	
	public String id;
	public String roomName;
	// Ȧ����, ¦���� ���� ������ ���� ������ ����.
	public int team;
	public PrintWriter pw;
	public boolean host;	// �������� �ƴ���
	public int turn;	// �ڱ� �������� �ƴ���
	public boolean gameOn;	// ���������� �ƴ���
	public boolean block;
	public StopWatch sw;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
		this.id = "";
		roomName = "";
		team = -1;
		pw = null;
		host = false;
		turn = -1;
		block = false;
		sw = null;
	}
	
	public UserInfo(PrintWriter pw) {
		// TODO Auto-generated constructor stub
		this.id = "";
		roomName = "";
		team = -1;
		this.pw = pw;
		host = false;
		turn = -1;
		block = false;
		sw = null;
	}
	
}
