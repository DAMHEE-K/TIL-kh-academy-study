package ncs.test3;

public class Tv implements Comparable{
	
	private String name;
	private int price;
	private String description;
	
	public Tv() {}
	public Tv(String name, int price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getPriceAsInteger() {
		return this.price;
	}
	
	@Override
	public String toString() {
		return  name + "  " + price + "  " + description  ;
	}

	// 가격 비교를 위한 compareTo 메소드
	@Override
	public boolean equals(Object obj) {
		return ((Tv)obj).getPriceAsInteger().equals(getPriceAsInteger());
	}

	@Override
	public int compareTo(Object o) {
		Tv t = (Tv)o;
		return t.getPrice() - getPrice();
	}
}
