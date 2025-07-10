package mvcproject.ABO.model;

/**
 * MVC에서 모델은 비즈니스 로직이 구현된 코드이므로 재사용 대상
 */
public class ABOManager {
	/**
	 * 입력한 혈액형에 대한 판단을 하는 메서드
	 */
	public String getAdvice(String abo) {
		String msg = null;
		
		if (abo != null) {
			if (abo.equals("A")){
				msg = "신중함";
			} else if(abo.equals("B")){
				msg = "개성강함";
			} else if(abo.equals("O")){
				msg = "외향적";
			} else if(abo.equals("AB")){
				msg = "이성적";
			}
		}
		return msg;
	}
}
