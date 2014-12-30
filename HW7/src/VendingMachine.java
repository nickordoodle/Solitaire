
public class VendingMachine {
	
	int numslots;
	int maxperslot;
	double cash;
	
	String[] slot;//product names of each slot(index).
	int[] slotQuantity;//quantity of each item according to the slot(index).  This works because in any given slot, only one product is present,
					   //so no 2d is needed.  However, a 2d array would be helpful in a different sense.
	double[] price;//price of each item according to its slot(index)
	
	
	VendingMachine(int numslots, int maxperslot, double cash){//Takes the number of slots in the vending machine, the maximum number of items 
														      //that can fit into any one slot, and the amount of cash in 
															  //the machine initially. This is the constructor method.
		
		slot = new String[numslots];														
		slotQuantity = new int[numslots];//this array can only be as long as numslots available
		price = new double[numslots];
		
		
		for (int i = 0; i < slotQuantity.length; i++){
			slotQuantity[i] = 0;//sets every index equal to 0
			
		}
		
	
		this.numslots = numslots;
		this.maxperslot = maxperslot;
		this.cash = cash;
		
	
		
	}
	
	
	
	void setProduct(int slot, String product, double price){//Make the given slot hold items of the specified kind of product, sold for the given price. 
															//The initial quantity of the product in this slot should be zero. If the slot already held 
								//another kind of product, the old product should be removed from this slot.
		
		if (slot >= 0 && slot <= numslots){
			slotQuantity[slot] = 0;
			this.slot[slot] = product;	
		}
		else{
			
			return;//error condition
			
		}
		
		if(price >= 0){
			
			this.price[slot] = price;
			
		}
		else{
			
			return;//error condition
			
		}
	
		
	}
	

	void restockProduct(String product, int quantity){
			
		int openSpace = 0;
		
		if (quantity < 1 || quantity > (numslots * maxperslot)){
			return;
		}
		
		for (int i = 0; i < slot.length; i++){
			
			if (slot[i] == product){
				
				openSpace += (maxperslot - slotQuantity[i]);
				
			}
			
			
			
		}
		
		if (openSpace >= quantity){// if we have enough space to fit quantity, change values accordingly
		
			int numUsed = 0;
	
			for (int k = 0; k < slot.length; k++){
				
				
				
				if (slot[k] == product && slotQuantity[k] <= maxperslot){//checks for product name, then it makes sure it is not full
					
					if (slotQuantity[k] != maxperslot){
					
						if ((slotQuantity[k] + quantity) <= maxperslot){//adds quantity to given slot
							slotQuantity[k] += quantity;
							break;
							
						}
						else if ((slotQuantity[k] + quantity) > maxperslot ){
							
							numUsed = maxperslot - slotQuantity[k];//this will be the number that goes into the next slot with the same product
							slotQuantity[k] = maxperslot;//fills up this slot
							quantity -= numUsed;//adjust to what is remaining
							
						}
					}
						
	
				}
				
				
			}
		}
		else{
			
			return;
			
		}
		
		}
		
		double getCashOnHand(){//Return the amount of cash now in the vending machine (this amount should increase whenever an item is purchased).
			return cash;
			
		}
		
		int getQuantity(int slot){//Return the number of items in the given slot.
			
			if (slot >= 0 && slot <= numslots){
			
				return slotQuantity[slot];
				
			}
			else{
				
				return 0;
				
			}
			
		}
		
		int getQuantity(String product){//Return the total number of items of the specified kind of product that are in the vending machine. 
										//Remember that this product may be in more than one slot. 
										//If the product is not in the vending machine at all, simply return zero.
			
			int totalQuantity = 0;
			int numTimesFound = 0;
			
			for (int i = 0; i < slot.length; i++){
				
				if(slot[i] == product){//if true, product is located in index "i".
					
					numTimesFound++;
					
					
					totalQuantity += slotQuantity[i];//only add to total if we find a specific product
					
				}
				
				
				
			}
			
			if (numTimesFound > 0)
				return totalQuantity;
			else
				return 0;
			
		}
		
		boolean buyItem(int slot){// Attempt to buy one item from the given slot. Return true if successful.
								  //Must add cash to cash in vending machine
			if(slot >= 0 && slot <= numslots){//first check if its valid slot
				if (slotQuantity[slot] > 0){//see if item is in stock
					
					slotQuantity[slot]--;//if so, we can buy item so subtract one from the slot
					cash += price[slot];//and add the appropriate amount of cash to vending machine
					return true;//valid transaction
				}
				else{
					return false;//if not in stock, then we cant buy item
				}
			}
			else{
				return false;//if slot entered is not in valid domain, we can't buy item
			}
				
			
			
		}
		
	
		
	}
	
	



