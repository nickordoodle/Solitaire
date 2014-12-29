package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/*WHAT HAPPENS TO POINTERS WHEN YOU SWAP NODES?
	 * DO I NEED TO UPDATE DECKREAR WHEN I SWAP CARDS?
	 * DO I CALL THE FOUR METHODS IN GETKEY METHOD?
	 * I AM GETTING THE SAME KEY WHEN I TEST THE PROGRAM BUT IT ALMOST GIVES THE RIGHT ANSWER
	 * WHY ISNT MY PROGRAM PRINTING ANYTHING?
	 * WHEN YOU SET A NODE EQUAL TO ANOTHER NODE, DOES THE NEXT VALUE CHANGE?/
	 * 
	 * 
	 * CHANGE DATA VALUES!!!
	 * UPDATE DECKREAR
	 * FIND INFINITE LOOP
	 * 
	 * 
	 * ONLY UPPERCASE LETTERS? ASSUME ALL LETTERS ARE UPPERCASE
	 * JOKERS AT BOTH ENDS FOR TRIPLE CUT? DO NOTHING
	 * JOKERS NEXT TO EACH OTHER IN THE BEGINNING
	 * JOKERS NEXT TO EACH OTHER AT THE END
	 * LAST CARD IS 28, USE 27 BUT JUST RETURN SINCE CARDS ARE ALREADY BEFORE THE LAST CARD?
	 * SAME GOES WITH 27, SHOULDNT NOTHING HAPPEN IN THIS CASE?
	 * DO WE RESET CURR VALUE?!?!? OR KEEP IT WHERE IT IS!
	 * 
	 * 
	 * 
	 * FOR GETKEY, IF IT HITS 27 OR 28, DO WE USE THE FIRST CARD AS THE COUNT AGAIN?
	 */
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;

	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	
	public void printValues(){
		
		CardNode curr = deckRear;
		int count = 0;
		
		
		do{
			curr = curr.next;
		
			if((curr.cardValue == 27 || curr.cardValue == 28 )&& count == 0 ){
			
				System.out.print("&&& " + curr.cardValue + "| ");

				count++;
				
			}else if((curr.cardValue == 27 || curr.cardValue == 28) && count > 0 ){
				
				System.out.print(curr.cardValue  + " &&& ");

				
			}else{
				System.out.print(curr.cardValue + "| ");
			}
			
			
			
	
			
			
		}while(curr != deckRear);
		
		System.out.println("\n" + "The deckRear is: " + deckRear.cardValue);
        System.out.println();	
	}
	
	
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		
		
		CardNode temp = new CardNode();
		CardNode prev = deckRear;
		CardNode curr = deckRear.next;
		
		
		do{
			if (prev.cardValue == 27){//case where JokerA is located at deckRear;
				temp.cardValue = prev.cardValue;
				prev.cardValue = curr.cardValue;
				curr.cardValue = temp.cardValue;
				
				return;
				
				
			}
			
			else if(curr.cardValue == 27){
				temp.cardValue = curr.cardValue;
				curr.cardValue = curr.next.cardValue;
				curr.next.cardValue = temp.cardValue;
				
				System.out.println("JokerA: After");
				printValues();
				return;
				
			}
			else{
				prev = prev.next;
				curr = curr.next;
			}
			
			
			
			
		}while(curr != deckRear);

	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    
		
		CardNode curr = deckRear.next;
		CardNode prev = deckRear;
		CardNode temp = new CardNode();
		

		System.out.println("JOKER B: BEFORE");
		printValues();
		if (prev.cardValue == 28 && prev == deckRear){//case for joker being the last card in list
			temp.cardValue = prev.cardValue;
			prev.cardValue = curr.cardValue;
			curr.cardValue = curr.next.cardValue;
			curr.next.cardValue = temp.cardValue;
			
			deckRear = prev;
			
			
			//printValues();
			
			return;
		}
		
		do{
			
			
			if(prev.cardValue == 28){//regular case
				
				temp.cardValue = prev.cardValue;
				prev.cardValue = curr.cardValue;
				curr.cardValue = curr.next.cardValue;
				curr.next.cardValue = temp.cardValue;
				
				System.out.println("JOKER B: AFTER");
				printValues();
				
				return;
				
			}
		
			
			else{
				
				prev = prev.next;
				curr = curr.next;
				
			}
			
			
		}while(curr != deckRear);
		
		System.out.println("JOKER B: AFTER");
		printValues();
		

	}
	
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		/*CASES
		 * 
		 * Jokers next to each other 5, 3, 27, 28, 20
		 * Joker(s) at end of list 2, 27, 5, 28
		 * Jokers in middle 5, 10, 27, 3, 25, 28, 9, 11
		 * Jokers at both ends
		 * 
		 * 27 ,28 4, 10, 15, 13, 7
		 * 10, 15, 13, 7, 27, 28
		 */
		
		
		CardNode curr = deckRear.next;
		CardNode prev = deckRear;
		CardNode temp = new CardNode();
		CardNode firstJoker = new CardNode();
		CardNode secondJoker = new CardNode();
		CardNode beforeFirstJoker = new CardNode();
		CardNode firstCard = deckRear.next;
		int count = 0;
		
		System.out.println("TRIPLE CUT: BEFORE");
		printValues();
		if((curr.cardValue == 27 || curr.cardValue == 28) && (prev.cardValue == 27 || prev.cardValue == 28)){//case where jokers are at both ends
			deckRear = prev;
			return;//CHECK THIS CASE!!!
			
		}
		else if((curr.next.cardValue == 27 || curr.next.cardValue == 28) && (curr.cardValue == 27 || curr.cardValue == 28)){//jokers next to each other in beginning
			return;

		}//CASE FOR JOKERS NEXT TO EACH OTHER AT END
		
		do{
			if( ( curr.next == deckRear && (curr.next.cardValue == 27 || curr.next.cardValue == 28) && (prev.next.cardValue == 28 || prev.next.cardValue == 27)) ){
				
				deckRear = prev;
				return;
				
			}
			if ((curr.cardValue == 27 || curr.cardValue == 28) && count == 0){
				firstJoker = curr;
				beforeFirstJoker = prev;//finds card before 1st joker
				
				temp = deckRear;
				deckRear = firstCard;//temp to avoid garbage collection
				temp.next = firstJoker;
				
				
				count++;
				
			}
			else if((curr.cardValue == 27 || curr.cardValue == 28) && count != 0){
				secondJoker = curr;
				
				deckRear = secondJoker.next;
				secondJoker.next = firstCard;
				temp = deckRear;
				deckRear = firstJoker;
				beforeFirstJoker.next = temp;
				deckRear = beforeFirstJoker;
				
				
				
				break;

			}
			
			curr = curr.next;
			prev = prev.next;
				
			
			
			
		}while(curr != deckRear.next);
		
		
		System.out.println("TRIPLECUT: AFTER");
		printValues();
		

	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		
		CardNode curr = deckRear.next;
		CardNode prev = deckRear;
		CardNode first = deckRear.next;
		CardNode last = new CardNode();
		CardNode temp = new CardNode();
		int numCardsToMove = 0;
		int count = 0;
		
		System.out.println("COUNTCUT: BEFORE");
		printValues();
		
		if(deckRear.cardValue == 28 || deckRear.cardValue == 27){//the former turns count into 27, but 27 will return the same thing
			return;
		}else{
			numCardsToMove = deckRear.cardValue;
			

		}
		 
		do{
			
			if(count < numCardsToMove){
				
				count++;
				if(count == numCardsToMove){
					temp = deckRear;
					deckRear = first;
					last = curr;
					temp.next = last.next;		
					
				}
				
				
				
			}
			
			
			
			
			if(curr == temp){
				
				deckRear = last.next;
				prev.next = first;
				last.next = curr;
				
				deckRear = temp;
				
				
				break;
			}
			
			curr = curr.next;
			prev = prev.next;
			
			
		}while(curr != deckRear.next);
		
		System.out.println("COUNTCUT: AFTER");
		printValues();
		

		
	}
	
        /**
         * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
         * counts down based on the value of the first card and extracts the next card value 
         * as key, but if that value is 27 or 28, repeats the whole process until a value
         * less than or equal to 26 is found, which is then returned.
         * 
         * @return Key between 1 and 26
         */
	int getKey() {
		
		
		
		
		jokerA();//call four methods
		jokerB();
		tripleCut();
		countCut();
		
		CardNode curr = new CardNode();
		curr = deckRear.next;
		CardNode front = new CardNode();
		front = deckRear.next;
		int count;
		boolean found = false;
		int key = 0;
		
		if (front.cardValue == 28){//gets a key
			
			count = 27;
		}
		else{
			count = front.cardValue;
		}
		System.out.println(front.cardValue);
		
		
		printList(deckRear);
		
		do{	

			count--;
			
			if (count == 0){
				
				if(curr.next.cardValue != 27 && curr.next.cardValue != 28){//if true, we are done
					System.out.println("got here");
					key = curr.next.cardValue;
					found=true;

				}
				else{//DO WE RESET CURR VALUE?!?!? OR KEEP IT WHERE IT IS!
					
					jokerA();//otherwise, repeat process
					jokerB();
					tripleCut();
					countCut();
					
					curr = deckRear.next;
					front = deckRear.next;
					if(front.cardValue == 28){
						count = 27;
					}
					else{
						count = front.cardValue;
					}
					
				}
				
		
			}else{

				curr = curr.next;
				

					
				
				
			}
			
			
				
	}while(found==false);
		
		System.out.println("The key is now: " + key);	
		return key;
		
		
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	// 7   16   5   8   8   15   26   9   14   23   12   15   25   3   1
	public String encrypt(String message) {	
		
		message = message.replaceAll("\\s+","");
		char c;
		char newLetter;
		int newNumLetter;
		int key;
		String encMessage = "";
		
		for (int i = 0; i < message.length(); i++){//changes message to only include uppercase letters
			
			c = message.charAt(i);
			key = getKey();
			
			System.out.print(key + " ");
			
			if ((int)c >= 65 && (int)c <= 90){//checks for uppercase letters
				
				if((int)c + key > 90){
					newNumLetter = (int)c + key;
					newNumLetter -= 26;
					
				}else{
					newNumLetter = (int)c + key;
					
				}
			
				newLetter = (char)newNumLetter;
				encMessage += newLetter;
				
				
			}
			
		}
		
		System.out.println("Encryption is done");
		return encMessage;
		
		
		
	    
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		
		char c;
		int key;
		char newLetter;
		int newNumLetter;
		String encMessage = "";
		
		
		for (int i = 0; i < message.length(); i++){
			
			c = message.charAt(i);
			key = getKey();
			
			System.out.println(key);
			
			if((int)c <= key){
				newNumLetter = (int)c + 26;
				newNumLetter -= key;
				
			}
			else{
				newNumLetter = (int)c - key;
			}
			
			
			newLetter = (char)newNumLetter;
			System.out.print(newLetter);
			System.out.print(" " + newNumLetter);
			System.out.println();
			
			encMessage += newLetter;
				
				
		}
			

		System.out.println("Decryption is done");

		return encMessage;
		
	
	}
}
