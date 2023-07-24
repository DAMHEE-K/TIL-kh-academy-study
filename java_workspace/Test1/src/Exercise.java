import java.util.Arrays;

public class Exercise {
	public static void main(String[] args) {
		
		String hobby = "운동,등산,게임";
		String[] hobbies = hobby.split(",");
		
		System.out.println(hobby);
		System.out.println(Arrays.toString(hobbies));
	}
}
