import java.awt.Color;
import java.io.BufferedReader;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class ClientThread extends Thread {
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

		while (true) {
			try {
				line = br.readLine();
				System.out.println(line);
			} catch (Exception e) {
				// TODO: handle exception
			}

			// ��ũ������ ����
			StringTokenizer st = new StringTokenizer(line, ";");
			int num = Integer.parseInt(st.nextToken());

			// ��ū�� ���ؼ� switch �� ����
			switch (num) {
			case CatchMindProtocol.WAITING_ROOM_CHAT: // 0
			{
				// 0�� ä���̴�. ���� ��ũ���������� �ش� ���̵� ���´�
				String senderId = st.nextToken();
				String text = st.nextToken();
				lv.room.area.append(senderId + " : " + text + "\n");
				lv.room.area.setAutoscrolls(true);
				break;
			}
			case CatchMindProtocol.GAME_ROOM_CHAT: // 10
			{
				// 10�� ���ӹ� �ӿ��� ä�� �ϴ� ���̴�. 0�� �����̹Ƿ� �з��� �ʿ��ϴ�.
				String senderId = st.nextToken();
				String text = st.nextToken();
				String flag = st.nextToken();
				lv.room.gameRoom.area.append(senderId + " : " + text + "\n");
				lv.room.gameRoom.area.setAutoscrolls(true);
				// ���࿡ �����̸�
				if (flag.equals("1")) {
					JOptionPane.showMessageDialog(null, "������ ������ϴ�.");
					user.gameOn = false;
				}
				break;
			}
			// ���ӷ� ���� ����Ʈ �߰�.
			case CatchMindProtocol.ADD_USERLIST_IN_GAMEROOM: // 79
			{
				lv.room.gameRoom.userListField.removeAll();
				String tokenString = st.nextToken();
				StringTokenizer st1 = new StringTokenizer(tokenString, ",");
				while (st1.hasMoreTokens()) {
					lv.room.gameRoom.userListField.add(st1.nextToken());
				}
				break;
			}
			// �� �� á�� ��
			case CatchMindProtocol.IS_FULL_GAMEROOM: // 88
			{
				JOptionPane.showMessageDialog(null, "�� ���� ������ �� �����ϴ�.");
				lv.room.gameRoom.setVisible(false);
				break;
			}
			case CatchMindProtocol.LOGIN_SUCCESS: // �α��� ���� 100
			{
				// �α��� ��ư ��������,
				if (this.user.id.equals(st.nextToken())) {
					while (st.hasMoreTokens()) {
						lv.room.userList.add(st.nextToken());
					}
				} else {
					// ���� ���°Ÿ�, (���»���� �ڱ� �ڽ��̸�)
					lv.room.userList.removeAll();
					while (st.hasMoreTokens()) {
						lv.room.userList.add(st.nextToken());
					}
				}
				break;
			}
			case CatchMindProtocol.ADD_ROOM_LIST: // ���濡�� ���ӹ� ����Ʈ �߰� 110
			{
				// ����� �߰����־�� �ϴϱ�.
				String senderId = st.nextToken();
				// �ڿ��� ���� �� �� �����̴�.
				while (st.hasMoreTokens()) {
					lv.room.roomList.add(st.nextToken());
				}
				break;
			}
			case CatchMindProtocol.JOIN_GAME_ROOM: // ���� ����. 200
			{
				// ����Ʈ���� �ش� id ���ش�.
				String id = st.nextToken();
				// System.out.println("id : "+id);
				// System.out.println("this.id : "+this.user.id);

				if (st.hasMoreTokens()) {
					String roomName = st.nextToken();

					// lv.user�� �ѹ� �׽�Ʈ �غ����Ѵ�. ��� ���� ���� �����ϴ���..
					user.roomName = roomName;
				}
				lv.room.userList.remove(id);

				//
				/*
				 * if(id.equals(this.user.id))
				 * lv.room.gameRoom.userListField.removeAll();
				 * lv.room.gameRoom.userListField.add(id);
				 */
				break;
			}
			// ����Ʈ�� ���õȰ� �޾�����
			case CatchMindProtocol.DRAW_PAINT: // �׸� �׸��� 300
			{
				// �׸��� ������,
				int type = Integer.parseInt(st.nextToken());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int color_info = Integer.parseInt(st.nextToken());

				if (color_info == 0)
					lv.room.gameRoom.g.setColor(Color.white);
				else if (color_info == 1) {
					lv.room.gameRoom.g.setColor(Color.red);
				} else if (color_info == 2) {
					lv.room.gameRoom.g.setColor(Color.orange);
				} else if (color_info == 3) {
					lv.room.gameRoom.g.setColor(Color.yellow);
				} else if (color_info == 4) {
					lv.room.gameRoom.g.setColor(Color.green);
				} else if (color_info == 5) {
					lv.room.gameRoom.g.setColor(Color.blue);
				} else if (color_info == 6) {
					lv.room.gameRoom.g.setColor(Color.black);
				} else if (color_info == 7) {
					lv.room.gameRoom.g.setColor(Color.white);
				} 

				int w = x2 - x1;
				int h = y2 - y1;
				lv.room.gameRoom.gg.setStroke(lv.room.gameRoom.bs);
				lv.room.gameRoom.gg.setPaintMode();

				switch (type) {
					case 0:
						lv.room.gameRoom.g.drawLine(x1, y1, x2, y2);
						break;
					case 1:
						lv.room.gameRoom.g.drawOval(x1, y1, w, h);
						break;
					case 2:
						lv.room.gameRoom.g.drawRect(x1, y1, w, h);
						break;
					case 3:
						lv.room.gameRoom.g.drawLine(x1, y1, x2, y2);
						break;
					case 4:
						lv.room.gameRoom.canvas.repaint();
						break;
				}
				break;
			}
			// make �� ������
			case CatchMindProtocol.MAKE_GAME_ROOM: // �� �����. 400
			{
				// �� ���� ���
				String id = st.nextToken();
				System.out.println("id : " + id);
				System.out.println("this.id : " + this.user.id);
				lv.room.userList.remove(id);
				String roomName = st.nextToken();
				user.roomName = roomName;
				lv.room.roomList.add(roomName);

				if (id.equals(this.user.id)) {
					// ������ ���Ŵϱ� �渮��Ʈ�� �߰�.
					lv.room.gameRoom.userListField.add(id);
				}
				break;
			}
			case CatchMindProtocol.GAME_START: // ���ӽ����ϱ�.500
			{
				String id = st.nextToken();
				String roomName = st.nextToken();

				// ����
				String answer = st.nextToken();

				// ������
				String left1 = st.nextToken();
				String left2 = st.nextToken();
				String left3 = st.nextToken();

				String right1 = st.nextToken();
				String right2 = st.nextToken();
				String right3 = st.nextToken();

				if (id.equals(left1) || id.equals(right1)) {
					user.turn = 0;
				} else if (id.equals(left2) || id.equals(right2)) {
					user.turn = 1;
					// }
				} else if (id.equals(left3) || id.equals(right3)) {
					user.turn = 2;
				}

				if (user.roomName.equals(roomName)) {
					user.gameOn = true;
				}

				break;
			}
			case CatchMindProtocol.GAME_STOPWATCH: // �����ġ ����.700
			{
				String time = st.nextToken();
				int myTurn = Integer.parseInt(st.nextToken());

				int count = Integer.parseInt(time);

				int i_min = 0;
				String s_min = "";
				String s_sec = "";
				i_min = count / 60;
				s_min = String.format("%02d", i_min);
				s_sec = String.format("%02d", count % 60);
				lv.room.gameRoom.mm.setText(s_min);
				lv.room.gameRoom.ss.setText(s_sec);

				// if((Integer.parseInt(time)%30 == 0) // BY GIL
				// && (Integer.parseInt(time)/30 == 2-myTurn)){ // �� 60�� , �ѻ����
				// 30��
				// System.out.println("============================");
				// System.out.println("id : " +user.id + ", block");
				// System.out.println("============================");
				// user.block = true; // �׸��� ����, ������ ����Ǹ� �ٽ� false�� �ٲ���Ѵ�
				// }

				if ((Integer.parseInt(time) % 30 == 0) // BY GIL
						&& (Integer.parseInt(time) / 30 == 2)) { // 60��
					if (myTurn == 0) {
						JOptionPane.showMessageDialog(null, "�����ڴ� �׸��� �����ϴ�.");
						user.block = true;
					} else if (myTurn == 1) {
						user.block = false;
					} else if (myTurn == 2) {
						user.block = true;
					}

				} else if ((Integer.parseInt(time) % 30 == 0) // BY GIL
						&& (Integer.parseInt(time) / 30 == 1)) { // 30��

					if (myTurn == 0) {
						user.block = true;
					} else if (myTurn == 1) {
						JOptionPane.showMessageDialog(null, "30�ʰ� ����߽��ϴ�. ������ �׸� �� �����ϴ�.");
						user.block = true;
					} else if (myTurn == 2) {
						user.block = false;
					}
				} else if ((Integer.parseInt(time) % 30 == 0) // BY GIL
						&& (Integer.parseInt(time) / 30 == 0)) { // 0��
					
					if (myTurn == 0) {
						user.block = true;
					} else if (myTurn == 1) {
						user.block = true;
					} else if (myTurn == 2) {
						JOptionPane.showMessageDialog(null, "�ð��� ��� ����߽��ϴ�. ������ �׸� �� �����ϴ�.");
						user.block = true;
					}
					
					user.gameOn = false;
					
				}

				break;
			}
			// ���� �����ϴ� �κ�.
			case CatchMindProtocol.HEAD_GIVE: // ���� ���� 777
			{
				String newhost = st.nextToken();
				if (this.user.id.equals(newhost)) {
					this.user.host = true;
				} else {
					user.host = false;
				}
				break;
			}
			case CatchMindProtocol.EXIT_WATTING_ROM:// ���� ������
			{
				// ������ �޼���,
				// ������ ���
				String id = st.nextToken();
				lv.room.userList.remove(id);
				break;
			}
			case CatchMindProtocol.EXIT_GAME_ROOM: // ���ӹ� ������
			{
				// �Ѹ��� �ٽ� ���Ƿ� ��������.
				String id = st.nextToken();
				System.out.println(id);
				System.out.println(this.user.id);
				// �����ְ� �ƴ� ���� �濡�� ������ id�� �����ش�.!
				if (!id.equals(this.user.id) && lv.room.gameRoom != null)
					lv.room.gameRoom.userListField.remove(id);
				lv.room.userList.add(id);
				break;
			}
			// �� �����ִ� ����.
			case CatchMindProtocol.REMOVE_GAME_ROOM:// ���ӹ�
			{
				String roomName = st.nextToken();
				lv.room.roomList.remove(roomName);
				break;
			}
			case 1500: {
				String id = st.nextToken();
				String side = st.nextToken();

				if (side.equals("left")) {
					// ���� �� ���̵� �ٸ� �ʿ� �־��ٸ� right�ʿ� �־��ٸ�
					for (int i = 0; i < lv.room.gameRoom.rightUserField.getItemCount(); i++) {
						if (lv.room.gameRoom.rightUserField.getItem(i).equals(id)) {
							lv.room.gameRoom.rightUserField.remove(id);
						}
					}
					for (int i = 0; i < lv.room.gameRoom.leftUserField.getItemCount(); i++) {
						if (lv.room.gameRoom.leftUserField.getItem(i).equals(id)) {
							lv.room.gameRoom.leftUserField.remove(id);
						}
					}

					lv.room.gameRoom.leftUserField.add(id);
				}

				if (side.equals("right")) {
					for (int i = 0; i < lv.room.gameRoom.leftUserField.getItemCount(); i++) {
						if (lv.room.gameRoom.leftUserField.getItem(i).equals(id)) {
							lv.room.gameRoom.leftUserField.remove(id);
						}
					}
					for (int i = 0; i < lv.room.gameRoom.rightUserField.getItemCount(); i++) {
						if (lv.room.gameRoom.rightUserField.getItem(i).equals(id)) {
							lv.room.gameRoom.rightUserField.remove(id);
						}
					}
					lv.room.gameRoom.rightUserField.add(id);
				}

				break;
			}
			}// end of switch
		}
	}
}
