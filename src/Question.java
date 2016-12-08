

public class Question {
	String[] answer = new String [30];
	
	public Question() {
		//0~9 직업, 10~19 인물, 20~29 가수
		
		answer[0]   = "소방사";
		answer[1] 	= "경찰";
		answer[2] 	= "대통령";
		answer[3] 	= "대학생";
		answer[4] 	= "축구선수";
		answer[5] 	= "야구선수";
		answer[6] 	= "변호사";
		answer[7] 	= "프로그래머";
		answer[8] 	= "디자이너";
		answer[9] 	= "의사";
		answer[10] 	= "류현진";
		answer[11] 	= "박지성";
		answer[12] 	= "김연아";
		answer[13] 	= "강동원";
		answer[14] 	= "유관순";
		answer[15] 	= "원빈";
		answer[16] 	= "강정호";
		answer[17] 	= "노준호";
		answer[18] 	= "김문기";
		answer[19] 	= "황길수";
		answer[20] 	= "소녀시대";
		answer[21] 	= "마마무";
		answer[22] 	= "걸스데이";
		answer[23] 	= "여자친구";
		answer[24] 	= "싸이";
		answer[25] 	= "나인뮤지스";
		answer[26] 	= "원더걸스";
		answer[27] 	= "에프엑스";
		answer[28] 	= "박정현";
		answer[29] 	= "먼데이키즈";
		
	}
	
	public String setQuestion() {
		int stage = (int)(Math.random()*30+1);
		String question = answer[stage];
		return question;
	}
	
	public boolean checkAnswer(int stage, String str) {	// 미사용	
		if (answer[stage] == str) {
			boolean check = true;
			return check;
		} else {
			boolean check = false;
			return check;
		}
	}
	
}
