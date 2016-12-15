package tests;

public class Student extends Person {
	
	@Override
	public void method1(){
		System.out.println("Student 1");
		super.method1();
	}
	
	@Override
	public void method2() {
		System.out.println("Student 2");
	}
	
	
	public static void main(String[] args) {
		Person u = new Undergrad();
		u.method1();
		
	}

}


