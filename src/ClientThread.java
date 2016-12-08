import java.io.BufferedReader;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class ClientThread extends Thread{
	UserInfo user;
	BufferedReader br;
	LoginView lv;
	GameRoom gr = null;
	
	public ClientThread(BufferedReader br, LoginView lv) {
		// TODO Auto-generated constructor stub
		this.br = br;
		this.lv = lv;
		this.user = lv.user;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line = null;
		String[] token = null;
			
		while(true){
			try {
				line = br.readLine();
				System.out.println(line);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			// 토크나이져 생성
			StringTokenizer st = new StringTokenizer(line, ";");
			int num = Integer.parseInt(st.nextToken());
			
			// 토큰에 의해서 switch 문 실행
			switch(num){
			case CatchMindProtocol.WAITING_ROOM_CHAT : //0
			{
				// 0은 채팅이다. 다음 토크나이져에는 해당 아이디가 들어온다
				String senderId = st.nextToken();
				String text = st.nextToken();
				lv.room.area.append(senderId + " : " + text + "\n");
				lv.room.area.setAutoscrolls(true);
				break;
			}
			case CatchMindProtocol.GAME_ROOM_CHAT : //10
			{
				// 10은 게임방 속에서 채팅 하는 것이다. 0은 대기방이므로 분류가 필요하다.
				String senderId = st.nextToken();
				String text = st.nextToken();
				String flag = st.nextToken();
				lv.room.gameRoom.area.append(senderId + " : " + text + "\n");
				lv.room.gameRoom.area.setAutoscrolls(true);
				// 만약에 정답이면
				if(flag.equals("1")){
					JOptionPane.showMessageDialog(null, "문제를 맞췄습니다.");
					user.gameOn = false;
				}
				break;
			}
			//게임룸 유저 리스트 추가.
			case CatchMindProtocol.ADD_USERLIST_IN_GAMEROOM : //79 
			{
				lv.room.gameRoom.userListField.removeAll();
				String tokenString = st.nextToken(); 
				StringTokenizer st1 = new StringTokenizer(tokenString, ",");
				while(st1.hasMoreTokens()) {
					lv.room.gameRoom.userListField.add(st1.nextToken());
				}
				break;
			}
			// 방 꽉 찼을 때
			case CatchMindProtocol.IS_FULL_GAMEROOM : //88
			{	
				JOptionPane.showMessageDialog(null, "꽉 차서 입장할 수 없습니다.");
				lv.room.gameRoom.setVisible(false);
				break;
			}
			case CatchMindProtocol.LOGIN_SUCCESS : //로그인 성공 100
			{
					// 로그인 버튼 눌렀을때, 
					if(this.user.id.equals(st.nextToken())){
						while(st.hasMoreTokens()){
							lv.room.userList.add(st.nextToken());
						}
					}else{
						// 내가 들어온거면, (들어온사람이 자기 자신이면)
						lv.room.userList.removeAll();
						while(st.hasMoreTokens()){
							lv.room.userList.add(st.nextToken());
						}
					}
				break;
			}
			case CatchMindProtocol.ADD_ROOM_LIST : //대기방에서 게임방 리스트 추가 110
			{
				// 방들을 추가해주어야 하니까.
				String senderId = st.nextToken();
				// 뒤에는 이제 다 방 제목이다.
				while(st.hasMoreTokens()){
					lv.room.roomList.add(st.nextToken());
				}
				break;
			}
			case CatchMindProtocol.JOIN_GAME_ROOM : //게임 들어가기. 200
			{
				// 리스트에서 해당 id 빼준다.
				String id = st.nextToken();
//				System.out.println("id : "+id);
//				System.out.println("this.id : "+this.user.id);
				
				if(st.hasMoreTokens()){
					String roomName = st.nextToken();
					
					// lv.user랑 한번 테스트 해봐야한다. 모두 같은 값을 참조하는지..
					user.roomName = roomName;
				}
				lv.room.userList.remove(id);		
					
				//
				/*if(id.equals(this.user.id))
					lv.room.gameRoom.userListField.removeAll();
				lv.room.gameRoom.userListField.add(id);*/
				break;
			}
			// 페인트에 관련된거 받았을때
			case CatchMindProtocol.DRAW_PAINT :  //그림 그리기 300
			{
				// 그리기 했을때,
				int type = Integer.parseInt(st.nextToken());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int w = x2 - x1;
				int h = y2 - y1;
				lv.room.gameRoom.gg.setStroke(lv.room.gameRoom.bs);
				lv.room.gameRoom.gg.setPaintMode();
				
				switch(type){
				case 0 : lv.room.gameRoom.g.drawLine(x1, y1, x2, y2);break;
				case 1 : lv.room.gameRoom.g.drawOval(x1, y1, w, h);break;
				case 2 : lv.room.gameRoom.g.drawRect(x1, y1, w, h);break;
				case 3 : lv.room.gameRoom.g.drawLine(x1, y1, x2, y2);break;
				}
				break;
			}
			// make 룸 했을때 
			case CatchMindProtocol.MAKE_GAME_ROOM : //방 만들기. 400
			{
				// 방 만든 사람
				String id = st.nextToken();
				System.out.println("id : "+id);
				System.out.println("this.id : "+this.user.id);
				lv.room.userList.remove(id);
				String roomName = st.nextToken();
				user.roomName = roomName;
				lv.room.roomList.add(roomName);
				
				if(id.equals(this.user.id)){
					// 방장이 들어간거니까 방리스트에 추가.
					lv.room.gameRoom.userListField.add(id);
				}
				break;
			}
			case CatchMindProtocol.GAME_START : //게임시작하기.500
			{
				String id = st.nextToken();
				String roomName = st.nextToken();
				System.out.println("User.roomName은?? : " + user.roomName);
				System.out.println("지금 들어온 roomName은? : " + roomName);
				if(user.roomName.equals(roomName)){
					user.gameOn = true;
				}
				break;
			}
			case CatchMindProtocol.GAME_STOPWATCH : //스톱워치 시작.700
			{
				String time = st.nextToken();

				int count = Integer.parseInt(time);

				int i_min = 0;
				String s_min = "";
				String s_sec = "";
				i_min = count / 60;
				s_min = String.format("%02d", i_min);
				s_sec = String.format("%02d", count % 60);
				lv.room.gameRoom.mm.setText(s_min);
				lv.room.gameRoom.ss.setText(s_sec);
				break;
			}
			// 방장 위임하는 부분.
			case CatchMindProtocol.HEAD_GIVE : //방장 위임 777
			{
				String newhost = st.nextToken();
				if(this.user.id.equals(newhost)){
					this.user.host = true;
				}
				else{
					user.host = false;
				}
				break;
			}
			case CatchMindProtocol.EXIT_WATTING_ROM ://대기방 나가기
			{
				// 나가는 메세지,
				// 나가는 사람
				String id = st.nextToken();
				lv.room.userList.remove(id);
				break;
			}
			case CatchMindProtocol.EXIT_GAME_ROOM : //게임방 나가기
			{
				// 한명이 다시 대기실로 들어와진다.
				String id = st.nextToken();
				System.out.println(id);
				System.out.println(this.user.id);
				// 나간애가 아닌 경우는 방에서 나간애 id를 지워준다.!
				if(!id.equals(this.user.id) &&lv.room.gameRoom!=null)
					lv.room.gameRoom.userListField.remove(id);
					lv.room.userList.add(id);
				break;
			}
			// 방 지워주는 역할.
			case CatchMindProtocol.REMOVE_GAME_ROOM://게임바
			{
				String roomName = st.nextToken();
				lv.room.roomList.remove(roomName);
				break;
			}
			}// end of switch
		}
	}
}
