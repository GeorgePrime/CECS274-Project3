/**
 * Practice problem generating random integers
 * @author George Vargas
 *
 */
public class CECS274Project3 {

	public CECS274Project3() {

	}

	public static void main(String[] args) {
		System.out.println("Unrounded number "+getRandomNumber());
		System.out.println("Rounded number "+getRandomNumber2());
	}
	public static double getRandomNumber(){
	    double x = (Math.random() * 6) + 1;
	    return x;
	}
	public static double getRandomNumber2(){
	    double x = (int)(Math.random() * 6) + 1;
	    return x;
	}
}
