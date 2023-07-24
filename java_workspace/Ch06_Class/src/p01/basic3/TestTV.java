package p01.basic3;

public class TestTV {

	public static void main(String[] args) {

		TV tv1 = new TV();
		tv1.setChannel(30);
		tv1.setVolume(3); // 기본 객체 생성시 on == false 이므로, 변경 불가능
		
		tv1.turnOn();
		tv1.setChannel(30);
		tv1.setVolume(3);
		tv1.printTVInformation();
		
		tv1.turnOff();
		
		TV tv2 = new TV();
		tv2.turnOn();
		tv2.channelUp();
		tv2.channelUp();
		tv2.volumeUp();
		tv2.channel = 150; // channel field값을 직접 수정하는 문제가 발생 
						   // => encapsulation(캡슐화)로 해결 가능
		tv2.printTVInformation();
		tv2.turnOff();
		

	}

}
