import java.util.ArrayList;

public class RoomInfo {
	
	// 최대 몇명인지.
	int capacity;
	String roomName;
	boolean gameOn;
	String answer;
	ArrayList<UserInfo> userAl;

	// 방장이 방만들 때는 무조건 1팀이라서 1을 가지고 시작.
	int team1_count = 1;
	int team2_count = 0;
	
	public RoomInfo() {
		// TODO Auto-generated constructor stub
		capacity = 0;
		gameOn = false;
		roomName = "";
		answer = "노준호";
		userAl = new ArrayList();
		
	}
}
