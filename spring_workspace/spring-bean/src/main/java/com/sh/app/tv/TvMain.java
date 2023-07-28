package com.sh.app.tv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvMain {

	/**
	 * 빈(bean) - 스프링이 관리하는 자바 객체
	 * - xml
	 * - @Configuration
	 * - xml + @Component
	 */
	public static void main(String[] args) {
		String configLocation = "applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		System.out.println("======== 스프링 컨테이너 초기화 완료 ========");
	
		// 빈 가져오기
		Tv tv1 = (Tv) context.getBean("samsungTv"); // 꺼내올때 downCasting 필요
		tv1.powerOn();
		tv1.powerOff();
		
		// scope = prototype
		Tv tv2 = context.getBean(LgTv.class); // getBean 호출 시점에 객체 생성함
		tv2.powerOn();
		tv2.powerOff();
		
		Tv tv3 = context.getBean(LgTv.class);
		tv3.powerOn();
		tv3.powerOff();
		
		System.out.println(tv2 == tv3); // false
		
		// SamsungTv의 scope는 singleton이기 때문에, 같은 객체를 참조함
		Tv tv4 = context.getBean(SamsungTv.class);
		System.out.println(tv1 == tv4); // true
		
		RemoteControl rc = context.getBean(XiaomiRemocon.class);
		rc.channelTo(100);
	}

}
