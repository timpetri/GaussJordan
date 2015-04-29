package gauss;

import javax.swing.JOptionPane;

public class Runner {

	
	
	public static void main(String[] args) {
		
		String instructions = "Enter the matrix as: 1,2,3;4,5,6;7,8,9";
		String input = JOptionPane.showInputDialog(instructions);
		
		GaussJordan gj = new GaussJordan(input);
		
		gj.parseInput();
		System.out.println(gj);
		
		gj.toReducedRowEchelonForm();
		System.out.println(gj);
		
//		if (gj.validSolution())
//			System.out.println("Valid!");
//		else
//			System.out.println("No solution!");
		
	}

}
