

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;


public class CatchMindProtocol implements Serializable{
	//������� ���� ����� ���� ��� ���õ� ���������� 1�� �ڸ��� ���(ex �游���)
	//���� ä��
	public static final int WAITING_ROOM_CHAT = 0;  
	//���ӹ� ä��
	public static final int GAME_ROOM_CHAT = 10;  
	//���ӹ濡��  ���� ����Ʈ �߰�
	public static final int ADD_USERLIST_IN_GAMEROOM=79;	
	//���ӹ��� �ο��� ����á����
	public static final int IS_FULL_GAMEROOM=88;
	//�α��� ����
	public static final int LOGIN_SUCCESS = 100;  
	//���濡�� ���ӹ� ����Ʈ �߰�
	public static final int ADD_ROOM_LIST = 110;  
	//���ӹ� ����
	public static final int JOIN_GAME_ROOM = 200;  
	//�׸� �׸���
	public static final int DRAW_PAINT = 300;  
	//���ӹ� �����
	public static final int MAKE_GAME_ROOM = 400;  
	//���� ��ŸƮ
	public static final int GAME_START = 500;  
	//���ӹ� �����ġ ����
	public static final int GAME_STOPWATCH =700;
	//���ӹ濡�� ������ �������� ���� ����
	public static final int HEAD_GIVE = 777;
	//���� ������
	public static final int EXIT_WATTING_ROM = 900;  
	//���ӹ� ������
	public static final int EXIT_GAME_ROOM = 1000;
	//���� �� �����
	public static final int REMOVE_GAME_ROOM =1010;
	
	
	
	
}
