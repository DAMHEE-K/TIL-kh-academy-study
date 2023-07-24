package ncs.test9;

public class PlaneTest {
	public static void main(String[] args) {
	Airplane airplane = new Airplane("L747", 1000);
	Cargoplane cargoplane = new Cargoplane("C40",1000);
	System.out.println("Plane       fuleSize");
	System.out.println("--------------------");
	System.out.printf("%s           %d\n",airplane.getPlaneName(),airplane.getFuelSize());
	System.out.printf("%s            %d\n",cargoplane.getPlaneName(),cargoplane.getFuelSize());
	
	System.out.println("100 운항");
	airplane.flight(100);
	cargoplane.flight(100);
	
	System.out.println("Plane       fuleSize");
	System.out.println("--------------------");
	System.out.printf("%s           %d\n",airplane.getPlaneName(),airplane.getFuelSize());
	System.out.printf("%s            %d\n",cargoplane.getPlaneName(),cargoplane.getFuelSize());
	
	System.out.println("200 주유");
	airplane.refuel(200);
	cargoplane.refuel(200);
	System.out.println("Plane       fuleSize");
	System.out.println("--------------------");
	System.out.printf("%s           %d\n",airplane.getPlaneName(),airplane.getFuelSize());
	System.out.printf("%s            %d\n",cargoplane.getPlaneName(),cargoplane.getFuelSize());
	
	
	}
}
