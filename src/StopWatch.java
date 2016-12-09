import java.io.PrintWriter;
import java.util.ArrayList;

public class StopWatch extends Thread{
	
	ArrayList<UserInfo> userAl;
	ArrayList<RoomInfo> roomAl;
	
	String roomName;
	int time;
	public StopWatch(ArrayList<UserInfo> userAl, ArrayList<RoomInfo> roomAl, String roomName) {
		// TODO Auto-generated constructor stub
		this.userAl = userAl;
		this.roomName = roomName;
		this.roomAl = roomAl;
		time = 60;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String msg = "";
		
		
		
		while(true)
		{
			// �濡 �ִ� �ֵ����� msg�� ��� �����ִµ�..
			// ������ �Ѹ��� ������ break �������.. 
			String answer=null;
			synchronized(roomAl){
				for(int i=0; i<roomAl.size(); i++){
					if(roomAl.get(i).roomName.equals(roomName)){
						
						answer=roomAl.get(i).answer;
						// �ش� ���� �̸��� �Ȱ��� ���� ã������ �� �ش� ���� ã������
						if(roomAl.get(i).userAl.size() != 6){
							
							time=0;
							for(int j=0; j<roomAl.get(i).userAl.size(); j++){
								roomAl.get(i).userAl.get(j).gameOn = false;
							}
								
						}
					}
				}
			}
			
			synchronized(userAl){
				for(int i=0; i<userAl.size(); i++){
					if(userAl.get(i).roomName.equals(roomName)){
						userAl.get(i).gameOn = true;
						
						msg = CatchMindProtocol.GAME_STOPWATCH+"" + ";" + time + ";" + userAl.get(i).turn+";"+answer;
						System.out.println(msg);
						PrintWriter pw = userAl.get(i).pw;
						pw.println(msg);
						pw.flush();
					}
				}
			}
			try {
				time = time -1;
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(time==-1){
				
				break;
			}
		}
	}
}