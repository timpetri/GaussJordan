package gauss;

import javax.swing.JOptionPane;

public class Runner {

	
	private static double[][] problem1 = {
		// x = 1, y = 2, z = 3
		{ 1,  2, 3, 14 },  // 1x + 2y + 3z = 14
		{ 1, -1, 1,  2 },  // 1x - 1y + 1z = 2
		{ 4, -2, 1,  3 }   // 4x - 2y + 1z = 3
	};

	private static double[][] problem2 = {
		// x = 1, y = 2, z = 3
		{ 2,  3, -4, -4 },  // 2x + 3y - 4z = -4
		{ 1, -2,  1,  0 },  // 1x - 2y + 1z =  0
		{-1,  1,  2,  7 }   // -1x + 1y + 2z = 7
	};


	public static void main(String[] args) {
		
		GaussJordan gj = new GaussJordan(problem1);
		System.out.println(gj);
		gj.toReducedRowEchelonForm();
		System.out.println(gj);
		System.out.println(gj.produceSolutionString());
		
		gj = new GaussJordan(problem2);
		System.out.println(gj);
		gj.toReducedRowEchelonForm();
		System.out.println(gj);
		System.out.println(gj.produceSolutionString());
		

		System.exit(0);
	}



	public void properUI() {
		
		// for entering multiple matrices
		int enterManually = 0;

		do {	

			String instructions = "Enter the matrix as: 1,2,3;4,5,6;7,8,9";
			String input = JOptionPane.showInputDialog(instructions);

			GaussJordan gj = new GaussJordan(input);

			// deal with input string
			gj.parseInput();

			// display matrix
			JOptionPane.showMessageDialog(null, gj.toString());

			// run Gauss Jordan elimination algorithm
			gj.toReducedRowEchelonForm();

			// display RRE matrix
			JOptionPane.showMessageDialog(null, gj.toString());

			// display solution
			JOptionPane.showMessageDialog(null, gj.produceSolutionString());

			// new matrix?
			enterManually = JOptionPane.showConfirmDialog(null, "Would you like to enter another matrix?", "Quit? Y/N", JOptionPane.YES_NO_OPTION);

		} while (enterManually == 0);
		
		return;
	}

}
