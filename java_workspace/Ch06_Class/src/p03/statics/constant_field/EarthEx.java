package p03.statics.constant_field;

public class EarthEx {

	public static void main(String[] args) {
		Earth earth = new Earth();
		
		System.out.println("지구 반지름 : " + earth.EARTH_RADIUS);
		System.out.println("지구 표면적 : " + earth.EARTH_SURFACE_AREA);
		
		// earth.EARTH_RADIUS = 2000;
		System.out.println("지구 반지름 : " + Earth.EARTH_RADIUS);
		System.out.println("지구 표면적 : " + Earth.EARTH_SURFACE_AREA);
		
		Earth.age = 50.2;
		// static double age니까 age는 static 변수지만 final을 안붙여서 상수가 아니여서
		// 수정 가능
		System.out.println("지구 나이 : " + Earth.age + "억년");

	}

}
