package instrument;

public class Trumpet{
	int volume = 1;
	
	public void setVolume(int vol){
		this.volume = vol;	
		System.out.println(this.volume);	
	}
	
	public void playMP3(){
		System.out.println("mp3가 재생되는 중...");	
	}
}
