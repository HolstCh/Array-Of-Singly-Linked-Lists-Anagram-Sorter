# Singly-Linked-List
Java implementation of a 1D array and multiple singly linked lists to find and sort anagrams. The assignment document is: [Assignment_2.pdf](Assignment_2.pdf)

"CPSC319S21A2.java" is the main class and should be executed with the command line argument which is
 the filename including the ".txt" extension with the text file in the same folder location as the JAVA files.
 CPSC319S21A2 will store all words in an ArrayList for dynamic input and then convert it in a String array named "listA".
 It will then pass "listA" to the FindAnagrams class to sort into separate ArrayLists. FindAnagrams class
 is where most of the process takes place as it calls all methods from a method called sortIntoLists(). Each word is compared with
 the others in "listA" by length and then by the result of a bubble sort method which returns a sorted letter word String.
 If two words each have the same sorted letter word, then each are an anagram and are added to the "anagramWords" ArrayList.
 The ArrayLists will store anagrams, not anagrams, and sorted letter words corresponding to the name of the ArrayList.
 The not anagrams list is called "notAnagramWords" and the sorted letter words list is called "sortedLetterWords".
 Then, it will combine the not anagrams and the sorted letter words into an ArrayList called "categoriesList".
 This list is used as a reference for all words in "listA" to decide which words go into which TheLinkedList object in
 the array of objects, "listB". Each TheLinkedList object refers to a single "categoriesList" element where all words that correspond
 to the category are stored in Nodes. Lastly, the output file is created to display the results in vertical and
 horizontal alphabetical order. The output file will be created in the same folder where the input file and JAVA files are
 located. The documentation and inline comments offer extra details.
