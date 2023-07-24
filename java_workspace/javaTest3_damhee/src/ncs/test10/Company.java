package ncs.test10;

public class Company {
	
	public static void main(String[] args) {
		Employee[]employees = {
				new Secretary("Hilery", 1, "secretary", 800),	
				new Sales("Clinten", 2, "sales", 1200)
		};
		System.out.println("Name     Department  Salary");
		System.out.println("----------------------------");
		
		// for문 이용한 객체 정보 출력
		for(int i=0; i<employees.length; i++) {
			System.out.printf("%-8s  %-10s  %-5d\n" ,employees[i].getName(), 
					employees[i].getDepartment(), employees[i].getSalary());
		}
		
		// 모든 객체에 인센티브 100 씩 지급한 급여를 계산하고 다시 객체의 salary에 넣는다 .
		System.out.println("인센티브 100 지금");
		((Secretary)(employees[0])).incentive(100);
		System.out.println(employees[0].getSalary());
		((Sales)(employees[1])).incentive(100);
		
		
		System.out.println("Name     Department  Salary    Tax");
		System.out.println("----------------------------");
		
		// for문 이용한 객체 정보 출력
		for(int i=0; i<employees.length; i++) {
			System.out.printf("%-8s  %-10s  %-5d   %.1f\n" ,employees[i].getName(), 
					employees[i].getDepartment(), employees[i].getSalary(),
					employees[i].tax());
		}
		
	}
}
