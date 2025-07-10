package mvcproject.color.model;

public class ColorManager {
	/**
	 * 입력한 색상에 대한 판단을 하는 메서드
	 */
	public String getAdvice(String color) {
		String msg = null;
		
		if (color.equals("redd")){
			msg = "빨";
		} else if(color.equals("blue")){
			msg = "파";
		} else if(color.equals("green")){
			msg = "초";
		} else if(color.equals("yellow")){
			msg = "노";
		}
		return msg;
	}
}
