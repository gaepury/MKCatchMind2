

public class Question {
	String[] answer = new String [30];
	
	public Question() {
		//0~9 ����, 10~19 �ι�, 20~29 ����
		
		answer[0]   = "�ҹ��";
		answer[1] 	= "����";
		answer[2] 	= "�����";
		answer[3] 	= "���л�";
		answer[4] 	= "�౸����";
		answer[5] 	= "�߱�����";
		answer[6] 	= "��ȣ��";
		answer[7] 	= "���α׷���";
		answer[8] 	= "�����̳�";
		answer[9] 	= "�ǻ�";
		answer[10] 	= "������";
		answer[11] 	= "������";
		answer[12] 	= "�迬��";
		answer[13] 	= "������";
		answer[14] 	= "������";
		answer[15] 	= "����";
		answer[16] 	= "����ȣ";
		answer[17] 	= "����ȣ";
		answer[18] 	= "�蹮��";
		answer[19] 	= "Ȳ���";
		answer[20] 	= "�ҳ�ô�";
		answer[21] 	= "������";
		answer[22] 	= "�ɽ�����";
		answer[23] 	= "����ģ��";
		answer[24] 	= "����";
		answer[25] 	= "���ι�����";
		answer[26] 	= "�����ɽ�";
		answer[27] 	= "��������";
		answer[28] 	= "������";
		answer[29] 	= "�յ���Ű��";
		
	}
	
	public String setQuestion() {
		int stage = (int)(Math.random()*30+1);
		String question = answer[stage];
		return question;
	}
	
	public boolean checkAnswer(int stage, String str) {	// �̻��	
		if (answer[stage] == str) {
			boolean check = true;
			return check;
		} else {
			boolean check = false;
			return check;
		}
	}
	
}
