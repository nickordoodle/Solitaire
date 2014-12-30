public class vmd
{
	public static void main(String[] args)
	{
		//boolean success;

		// vending machine w/ 20 slots, 10 items maximum per slot, $5 cash on hand
		/*VendingMachine v = new VendingMachine(20, 10, 5.00);
		v.setProduct(0, "Cheesy Poofs", 0.75);
		v.setProduct(1, "Red Bull", 1.25);
		v.setProduct(2, "Cheesy Poofs", 0.75);
		v.restockProduct("Cheesy Poofs", 8);
		v.restockProduct("Cheesy Poofs", 10);
		v.restockProduct("Red Bull", 7);
		v.restockProduct("Cheesy Poofs", 5); // This one should not work
	    
		//v.setProduct(2, "Red Bull", 1.0);//now 2 = 0;
		
		success = v.buyItem(0);//first slot goes from full(10) and subtracts one so 9
		System.out.println(success); // should print "true"

		System.out.println(v.getCashOnHand()); // should print "5.75"
		
		System.out.println(v.getQuantity(0)); // should print "9"
		System.out.println(v.getQuantity("Cheesy Poofs")); // should print "12"*/
		
		
		String str1 = "j";
		String str2 = "t";
		
		System.out.println(str1.compareTo(str2));
		
	}
}