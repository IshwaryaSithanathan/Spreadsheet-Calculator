# Redmart-Spreadsheet
Redmart Spread sheet calculator coding challenge

Programming  Question  –  Spreadsheet  Calculator    

A spreadsheet consists of a two8dimensional array of cells, labeled A1, A2, etc. Rows are identified using letters, columns by numbers. Each cell contains either an integer  its value or an expression. Expressions contain integers, cell references, and the operators '+', '8', '*', '/' with the usual rules of evaluation – note that the input is RPN and should be evaluated in stack order. 
  
Write a program in $Java or Scala   to read a spreadsheet from ‘stdin’,  evaluate the values of  all the cells, and write the output to ‘stdout’. 

 

The spreadsheet input is defined as follows: 

•	Line 1: two integers, defining the width and height of the spreadsheet  n, m    

•	n*m lines each containing an expression which is the value of the corresponding cell   cells enumerated in the order A1, A2, A<n>, B1, ...    


 
Your program must output its data in the same format, but each cell should be reduced to a single floating point value.  For example, we would expect the following expect to produce the indicated output:  


Input         | Expected Ouput
------------- | -------------
3	2           | 3	2
A2            | 20.00000
4 5 *         | 20.00000
A1            | 20.00000
A1 B2 / 2 +   | 8.66667
3             | 3.00000
39 B1 B2 * /  | 1.50000


The above example input visually looks like:  

|   |   1           |  2       |  3              |
|---|---------------|----------|-----------------|
|A  |   A2          | 4 5 *    | A1              |
|B  |A1  B2 / 2 +   | 3        | 39 B1 B2 * /    |


Comments:

•	Your program should detect cyclic dependencies in the input data, report these in a  
sensible manner, and exit with a non8zero exit code.  

•	You can assume that there are no more than 26 rows A to Z in the spreadsheet; however  there can be any number of columns;

•	We will evaluate output automatically, so formatting it correctly is very important.  There should be one output per line, lines organized in this order: A1, A2, ... A<n>, B1,  B2, ... B<n> ... When printing out the numbers, please use formatting as below:

o In Scala, use  “%.5f”  .format val   
o In Java, use String.format “%.5f”, val     

• If this all seems to be a bit too easy, further credit will be given for: 
o Extending the expression grammar to support negative numbers as inputs. 
o	Extending the expression grammar to include an increment or decrement  
operator  ++ or --    





  

