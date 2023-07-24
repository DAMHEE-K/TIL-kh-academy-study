package ncs.test10;

public class Secretary extends Employee implements Bonus{
	public Secretary() {}
	public Secretary(String name, int number, String department, int salary) {
		super(name, number, department, salary);
	}
	
	/**
	 * pay의 80%가 인센티브임
	 */
	@Override
	public void incentive(int pay) {
		double value = pay*0.8;
		this.setSalary((int)(this.getSalary() + value));
	}
	
	/**
	 * 세금을 계산해서 리턴한다.
	 * salary에 13% 적용한다
	 */
	@Override
	public double tax() {
		return this.getSalary()*0.10;
	}

}
