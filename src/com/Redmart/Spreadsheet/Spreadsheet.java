package com.Redmart.Spreadsheet;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


/**
 * @author Mani
 *
 */
public class Spreadsheet {
	
	public class SheetCell{
		Double value;
		boolean IsCurrentEvaluation;
		boolean IsEvaluated;
		String cellContent;
		
		public SheetCell(String cellContent){
			
			this.cellContent = cellContent;
			this.IsCurrentEvaluation = false;
			this.IsEvaluated = false;
		}
	}
	
	SheetCell[][] sheetCells;
	private int sizeX;
	private int sizeY;
	
	
	//recursively evaluate a cell if expression evaluate it.
	// check to see if cycle occurred using HashSet.
	// return value when evaluation completed.
	private Double evaluateCell(SheetCell sheetCell,Set<SheetCell> currentEvaluationStack) {
		// TODO Auto-generated method stub
		 if(currentEvaluationStack == null)
		 {
			 currentEvaluationStack = new LinkedHashSet<SheetCell>();
		 }
		
		if(sheetCell.IsEvaluated)
		{
          // do nothing. Just return the value.
		} else if(!sheetCell.IsEvaluated && !currentEvaluationStack.contains(sheetCell))
		{
			currentEvaluationStack.add(sheetCell);

	        
			String[] fields = sheetCell.cellContent.split(" ");
			
	        Stack<Double> operands = new Stack<Double>();

		        for(int i=0;i<fields.length;i++) {
		            String s = fields[i];
		            
		            if      (s.equals("+")) operands.push(operands.pop() + operands.pop());
		            else if (s.equals("*")) operands.push(operands.pop() * operands.pop());
		            else if (s.equals("/")){
		         
		            	double divisor = operands.pop();
		               	double dividend = operands.pop();
		               	
		            	operands.push( dividend / divisor);
		            } 
		            else if (s.equals("-")){ 
		            	double subtractor = operands.pop();
		               	double subtractee = operands.pop();
		               	
		            	operands.push( subtractee - subtractor);
		            
		            }
		            else if (isNumber(s)) operands.push(Double.parseDouble(s));
		            else {
		            	SheetCell anotherCell = getCell(s);
		            	operands.push(evaluateCell(anotherCell,currentEvaluationStack));
		            }
		        }
		        
		        sheetCell.value = operands.pop();
		        sheetCell.IsEvaluated = true;


		} else {
			System.out.println("Cycle Occurred while evaluating Cell Value "+sheetCell.cellContent);
			System.out.println("Loop trace:  ");
			for(SheetCell loopCell:currentEvaluationStack)
			{
				System.out.println(" cell with content : "+loopCell.cellContent+ " ->");
			}
			System.exit(1);
		}
		
		return sheetCell.value;
	}
	
	
	// Helper method
	private SheetCell getCell(String s) {
		// TODO Auto-generated method stub
		 try {
		int x = (int)s.charAt(0) % 65;
		int y = Integer.parseInt(s.substring(1,s.length()))-1;
		return sheetCells[x][y];
		 }catch (NumberFormatException e) {
			 System.out.println("Data format error occurred while evaluating Cell" + s);
				System.exit(1);
			  }
		return null;
		
	}
	
	

	 // Helper method
		private static boolean isNumber(String s) {
			 try {
				    Double.parseDouble(s);
				    return true;
				  }
				  catch (NumberFormatException e) {
				    // s is not numeric
				    return false;
				  }
		}

		// Populate cell values into the SpreadSheet
		private static void populateCellValues(Spreadsheet spreadSheet) {
			// TODO Auto-generated method stub		
			try
			{
				
//			File dataFile = new File("src/test.txt");
//			Scanner sc = new Scanner(dataFile);
			Scanner sc = new Scanner(System.in);	
			
			spreadSheet.sheetCells= null;

			String[] fields = null;
			int[] size = new int[2];
			if (sc.hasNextLine()) {
				fields = sc.nextLine().split(" ");

				if (fields.length != 2) {
					throw new IllegalArgumentException("Invalid Size");
				} else {
					for (int i = 0; i < fields.length; i++)
						size[i] = Integer.parseInt(fields[i]);
					spreadSheet.sheetCells = new SheetCell[size[1]][size[0]];
					spreadSheet.sizeY = size[0];
					spreadSheet.sizeX = size[1];
				}

			}

			int rowIndex = 0,colIndex = 0,cellCount=0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.isEmpty())
					break;
				spreadSheet.sheetCells[rowIndex][colIndex] = spreadSheet.new SheetCell(line);
					cellCount++;
				colIndex++;
				if(colIndex==spreadSheet.sizeY)
				{
					colIndex = 0;
					rowIndex++;
				}
			}
			
			if (cellCount != size[0]*size[1])
				throw new IllegalArgumentException("No of cells doesn't match the given size");
			}
			catch(Exception e){
		    	System.out.println("Error occurred in while reading values");
		    	System.exit(1);
		    }
		}
	
	
	public static void main(String[] args){

		  try{

		final long startTime = System.currentTimeMillis();
		
		Spreadsheet spreadSheet = new Spreadsheet();
		
		populateCellValues(spreadSheet);
		
		for (int i = 0; i < spreadSheet.sizeX; i++) {
			for (int j = 0; j < spreadSheet.sizeY; j++) {
				spreadSheet.evaluateCell(spreadSheet.sheetCells[i][j],null);
			}
		}
		System.out.println(spreadSheet.sizeY+" "+spreadSheet.sizeX);
		for (int i = 0; i < spreadSheet.sizeX; i++) {
			for (int j = 0; j < spreadSheet.sizeY; j++) {
//				System.out.println(spreadSheet.sheetCells[i][j].value);
				if(i==spreadSheet.sizeX-1 && j==spreadSheet.sizeY-1)
					System.out.printf("%.5f", spreadSheet.sheetCells[i][j].value);
				else
				System.out.printf("%.5f%n", spreadSheet.sheetCells[i][j].value);
			}
		}
		
		final long endTime = System.currentTimeMillis();

	//	System.out.println("Total execution time: " + (endTime - startTime));
  }catch(Exception e){
  	System.out.println("Error occurrend in main:"+e.getMessage());
  }
	}
		

}

