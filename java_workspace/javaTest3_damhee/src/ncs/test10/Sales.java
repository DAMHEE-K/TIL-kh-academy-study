package ncs.test10;

public class Sales extends Employee implements Bonus {
	public Sales() {}
	public Sales(String name, int number, String department, int salary) {
		super(name, number, department, salary);
	}
	
	/**
	 * pay의 120% 가
	 * 기존 salary에 더해진다
	 */
	@Override
	public void incentive(int pay) {
		double value = pay*1.2;
		this.setSalary((int)(this.getSalary() + value));
	}
	
	/**
	 * 세금을 계산해서 리턴한다.
	 * salary에 13% 적용한다
	 */
	@Override
	public double tax() {
		return this.getSalary()*0.13;
	}
	
	
	

}
