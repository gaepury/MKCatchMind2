import java.io.PrintWriter;

public class UserInfo {
	
	public String id;
	public String roomName;
	// 홀수팀, 짝수팀 입장 순서에 따라 구분할 예정.
	public int team;
	public PrintWriter pw;
	public boolean host;	// 방장인지 아닌지
	public int turn;	// 자기 차례인지 아닌지
	public boolean gameOn;	// 게임중인지 아닌지
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
