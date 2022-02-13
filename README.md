# WordTrackerBST

Author: David, Saurav, Yisong, Nevyn

How to run our 

1, Download and extract our folder
2, Open the command prompt and navigate to where our assignment was extracted to in the command prompt
3, Copy a version of the files you want to test into the res folder of our assignment
4, The command line parameters for our word tracker program
	* -pf to print in alphabetic order all words along with the corresponding list of files in which the words occur
	* -pl to print in alphabetic order all words along with the corresponding list of files and numbers of the lines in which the word occur
	* -po to print in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words
	* -f to redirect the report in the previous step to a file

Information,

This java class is used to to read text files and collect and stores all the unique words it finds. 
The BST will store information from mulitple text files and track the occurrence of each word in a file and the 
line that it was on. It will then be stored in a list for each word object in the BST. 

This program also produces an output file to generate reports using the iterators that are built into the BST.

Reference:
http://www2.hawaii.edu/~esb/2010spring.ics211/TreeIterator.java.html
https://www.baeldung.com/java-stream-filter-lambda
