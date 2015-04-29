package gauss;

import javax.swing.JOptionPane;

public class Runner {



	public static void main(String[] args) {

		int i = JOptionPane.showConfirmDialog(null, "Would you like to enter a matrix?", "Quit? Y/N", JOptionPane.YES_NO_OPTION);

		while (i == 0) {
			
			System.out.println(i);
			String instructions = "Enter the matrix as: 1,2,3;4,5,6;7,8,9";
			String input = JOptionPane.showInputDialog(instructions);


			GaussJordan gj = new GaussJordan(input);

			gj.parseInput();
			System.out.println(gj);

			gj.toReducedRowEchelonForm();
			System.out.println(gj);
			
			JOptionPane.showMessageDialog(null, gj.toString());
			
			i = JOptionPane.showConfirmDialog(null, "Would you like to enter another matrix?", "Quit? Y/N", JOptionPane.YES_NO_OPTION);
		}
		
		System.exit(0);
	}
	

}
