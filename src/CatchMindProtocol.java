

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;


public class CatchMindProtocol implements Serializable{
	//사용자의 입장 퇴장등 종료 등과 관련된 프로토콜은 1의 자리수 사용(ex 방만들기)
	//대기방 채팅
	public static final int WAITING_ROOM_CHAT = 0;  
	//게임방 채팅
	public static final int GAME_ROOM_CHAT = 10;  
	//게임방에서  유저 리스트 추가
	public static final int ADD_USERLIST_IN_GAMEROOM=79;	
	//게임방의 인원이 가득찼는지
	public static final int IS_FULL_GAMEROOM=88;
	//로그인 성공
	public static final int LOGIN_SUCCESS = 100;  
	//대기방에서 게임방 리스트 추가
	public static final int ADD_ROOM_LIST = 110;  
	//게임방 입장
	public static final int JOIN_GAME_ROOM = 200;  
	//그림 그리기
	public static final int DRAW_PAINT = 300;  
	//게임방 만들기
	public static final int MAKE_GAME_ROOM = 400;  
	//게임 스타트
	public static final int GAME_START = 500;  
	//게임방 스톱워치 시작
	public static final int GAME_STOPWATCH =700;
	//게임방에서 방장이 나갔을때 방장 위임
	public static final int HEAD_GIVE = 777;
	//대기방 나가기
	public static final int EXIT_WATTING_ROM = 900;  
	//게임방 나가기
	public static final int EXIT_GAME_ROOM = 1000;
	//게임 방 지우기
	public static final int REMOVE_GAME_ROOM =1010;
	
	
	
	
}
