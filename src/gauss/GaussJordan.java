

package gauss;


public class GaussJordan {

	private String inputString;
	private double[][] matrix;



	public GaussJordan(String s) {
		this.inputString = s.trim();
	}


	/**
	 * Parses the input string to populate other attributes of class.
	 */
	public void parseInput() {

		String lines[] = this.inputString.split(";");
		int width = lines.length;
		int height = lines[0].split(",").length;
		this.matrix = new double[width][height];

		for (int i=0; i<width; i++) {
			String cells[] = lines[i].split(",");
			for(int j=0; j<height; j++) {
				this.matrix[i][j] = Double.parseDouble(cells[j]);

			}
		}
	}
	
	
	public void produceSolution() {
		
		if (!this.validSolution()) {
			System.out.println("Invalid Solution!");
		}
		else 
			return;
		
	}

	/**
	 * Reduces the matrix to Reduced Row Echelon Form.
	 */
	public void toReducedRowEchelonForm() {
		int lead = 0;
		int rowCount = this.matrix.length;
		int colCount = this.matrix[0].length;
		
		for (int r = 0; r < rowCount; r++) {
			
			// if past last column, stop
			if (lead >= colCount)
				break;
			
			// set index that will go through column looking non-zero, starting with row below previous iteration
			int i = r;
			
			// if element in row i and and column lead is not zero, look further down column
			while (matrix[i][lead] == 0.0 ) {
				i = i + 1;
				
				// if at last row, move one column to right, and start looking down from row r again
				if (i == rowCount) {
					i = r;
					lead = lead + 1;
					
					// if bottom right element of array has been reach, stop
					if (lead == colCount)
						return; // TODO: break or return?
				}
			}
			
			// swap row r (where started) and i (where first non-zero element is found) if necessary
			if (r != i) {
				double[] temp = this.matrix[r];
				this.matrix[r] = this.matrix[i];
				this.matrix[i] = temp;
			}
			
			
			matrix[r] = scaleRowWithPivot(lead, matrix[r]);
			
			// eliminate other entries in same column by subtracting matrix[r]*number from its respective row
			for (int j = 0; j < rowCount; j++) {
				
				// do not eliminate row r itself
				if (j != r && matrix[j][lead] != 0.0) {
					matrix[j] = eliminateEntry(matrix[j][lead], matrix[r], matrix[j]);
				}
				
				System.out.println(this);
			}
			
			// increase column
			lead = lead + 1;
		}
		
	}
	
	/**
	 * Scales the row by dividing each element by the value of element at pivotIndex.
	 * 
	 * @param pivotIndex
	 * @param row
	 * @return
	 */
	private double[] scaleRowWithPivot(int pivotIndex, double[] row) {
		
		double pivotValue = row[pivotIndex];
		
		for (int i = pivotIndex; i < row.length; i++) {
			row[i] = row[i]/pivotValue;
			System.out.println("[scaleRowWithPivot] Scaled value = " + row[i]);
		}
		
		return row;
	}


	/**
	 * @param 
	 * @param es
	 * @param es2
	 * @return
	 */
	private double[] eliminateEntry(double coefficient, double[] pivotColumn, double[] elimColumn) {
		System.out.println("[eliminateEntry]: coefficient = " + coefficient);
		System.out.println("[eliminateEntry]: pivotColumn = " + pivotColumn);
		System.out.println("[eliminateEntry]: elimColumn = " + elimColumn);
		
		double[] result = new double[pivotColumn.length];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = elimColumn[i] - coefficient*pivotColumn[i];
			System.out.println("[eliminateEntry]: Entry = " + result[i]);
		}
		
		return result;
	}


	public boolean validSolution() {
		int i=0;
		
		while(i<this.matrix.length) {
			if (!validRow(matrix[i]))
				return false;
			i++;
		}
		return true;
		
	}


	/**
	 * 
	 * @return
	 */
	private boolean validRow(double[] row) {
		if (row[row.length-1] == 0.0) {
			System.out.println("Row ok!");
			return true;
		}
		
		for (int i=0; i<row.length-1; i++) {
			if (row[i] != 0.0) {
				System.out.println("Row ok!");
				return true;
			}
		}
		
		return false;
	}


	@Override
	public String toString() {
		String s = "";
		for (int i=0; i<this.matrix.length; i++) {
			for (int j=0; j<this.matrix[i].length; j++) {
				s += this.matrix[i][j] + ", ";
			}
			s += "\n";
		}
			
		return s;
	}
	
	/**
	 * Returns matrix.
	 * @return
	 */
	public double[][] getMatrix() {
		return this.matrix;
	}


	/**
	 * Returns occurrences of given character in given string.
	 * @param s string
	 * @param c character
	 * @return occurrences
	 */
	public static int countOccurences(String s, char c) {
		int counter = 0;
		for( int i=0; i<s.length(); i++ ) {
			if(s.charAt(i) == c)
				counter++;
		}	
		return counter;
	}

}
