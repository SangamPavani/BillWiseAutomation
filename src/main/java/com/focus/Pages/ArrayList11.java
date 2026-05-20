package com.focus.Pages;

import java.util.ArrayList;

public class ArrayList11 {

	public static void main(String[] args) {
		ArrayList<Integer> n = new ArrayList<>();
		System.out.println(n.toString());

        // Checking if the ArrayList is empty
        boolean res = n.isEmpty();

        System.out.println("" + res);

        // Adding an element 
        // to the ArrayList
        n.add(21);

        // Checking again if the 
        // ArrayList is empty
        res = n.isEmpty();
        System.out.println("" + res);
	}

}
