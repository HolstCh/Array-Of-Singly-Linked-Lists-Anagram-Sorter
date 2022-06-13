import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

/**
 * FindAnagrams is the central processing class as it calls all methods to complete the process from the sortIntoLists() method.
 * The process consists of sorting data into separate ArrayLists.
 */
public class FindAnagrams
{
    private String[] firstList; // initial ordered 1D array that is passed (named listA in first method and are interchangeable)
    private TheLinkedList[] listB; // array of objects from implementation of a linked list class
    private static final ArrayList<String> anagramWords = new ArrayList<String>(); // anagram word list
    private static final ArrayList<String> notAnagramWords = new ArrayList<String>(); // not anagram word list
    private static final ArrayList<String> sortedLettersWords = new ArrayList<String>(); // sorted letter word list
    private static final ArrayList<String> categoriesList = new ArrayList<String>(); // category for each TheLinkedList object

    /**
     * @param firstList is a String array that is listA from CPSC319S21A2 class which is passed into this constructor.
     * Then, it is passed to sortIntoLists() which calls the rest of the methods in the program until the end of the process.
     */
    public FindAnagrams(String[] firstList)
    {
        this.firstList = firstList; // main method passes ListA here to organize data for ListB
        sortIntoLists(this.firstList); // calls sortIntoLists() for data organization
    }

    /**
     * @param listA is the String array from CPSC319S21A2. First, sortIntoLists() compares two words by length. If the same
     * length, the method calls bubbleSort() to check if the two words have the same sorted letters to verify that they are
     * anagrams. If a match, the method stores the anagrams and the sorted letter word to their corresponding ArrayLists.
     * After comparing all words, storeNoAnagrams() is called to store the words that do not have a match. Then, createListB()
     * is called to create TheLinkedList array of objects where each category corresponds to an object. The words that
     * correspond to a single category are inserted into a single TheLinkedList object. It follows that the method calls
     * organizeListB() to sort each category vertically within the array according to the first word in each TheLinkedList
     * object. Lastly, the method calls createOutputFile() to create the "output.txt" file and insert each word into the file
     * in vertical and horizontal alphabetical order as desired by assignment document.
     */
    public void sortIntoLists(String[] listA) // O(n^2)
    {
        // start left side of array and compare with right side sequentially (i always starts next to j, then j iterates to right side)
        for(int i = 0, j = i + 1; i <= listA.length - 2 && j <= listA.length - 1; j++)
        {
            if(listA[i].length() == listA[j].length())
            {
                String beforeLetters = bubbleSort(listA[i]); // word to the left side calls for sorted letter word reference
                String afterLetters = bubbleSort(listA[j]); // word to the right side calls for sorted letter word reference

                if(beforeLetters.equals(afterLetters)) // if same sorted letter word reference
                {
                    String beforeAnagram = listA[i]; // the original left side word from listA
                    String afterAnagram = listA[j]; // the original right side word from listA
                    storeAnagramWords(beforeAnagram, afterAnagram); // store anagrams into "anagramWords" ArrayList
                    storeSortedLetterWords(beforeLetters); // store sorted letter word into "sortedLetterWords" ArrayList
                }
            }
            if(j == (listA.length - 1)) // j iterates to end of right side each time
            {
                i++; // i moves over one position to compare with all words to the right until end of array
                j = i; // j = i + 1 because of for loop iteration afterwards (starting positions: i before j)
            }
        }
        storeNoAnagramWords(); // stores words with no matches into "notAnagramWords" ArrayList
        createListB(); // initializes array of TheLinkedList objects (listB) and inserts elements
        organizeListB(); // sorts TheLinkedList objects vertically (already sorted horizontally)
        createOutputFile(); // creates the output file and inserts the text in desired alphabetical format

    }

    /**
     * @param previous is a String that represents the word that is to the left of "next" in listA
     * @param next is a String that represents the word that is to the right of "previous" in listA
     * storeAnagramWords checks if the anagram has been stored. If not, the word is stored in ArrayList, "anagramWords".
     */
    public void storeAnagramWords(String previous, String next)
    {
        if(anagramWords.contains(previous) == false) // if word doesn't exist
        {
            anagramWords.add(previous); // add to list
        }
        if(anagramWords.contains(next) == false) // if word doesn't exist
        {
            anagramWords.add(next); // add to list
        }
    }

    /**
     * storeNoAnagramWords checks the remaining words from String array firstList (listA) and if the word is not in the ArrayList,
     * "anagramWords", then the word is added to the ArrayList, "notAnagramWords". not anagram words are used for a reference when
     * creating the categories for each TheLinkedList object. They will be stored in ArrayList, "categoriesList", for reference.
     */
    public void storeNoAnagramWords()
    {
        for(int i = 0; i < firstList.length; i++) // iterate through original list (listA or firstList)
        {
            if(anagramWords.contains(firstList[i]) == false) // if word doesn't exist in "anagramWords"
            {
                notAnagramWords.add(firstList[i]); // add to "notAnagramWords"
            }
        }
    }

    /**
     * @param sortedLetters is a String that represents words formed by letters in alphabetical order to decipher which words
     * are anagrams. The method checks if the word is currently in the list. If not, the word is added to the ArrayList,
     * "sortedLetterWords". These words are used for a reference when creating the categories for each TheLinkedList object.
     * They will be stored in ArrayList, "categoriesList", for reference.
     */
    public void storeSortedLetterWords(String sortedLetters)
    {
        if(sortedLettersWords.contains(sortedLetters) == false) // if word doesn't exist
        {
            sortedLettersWords.add(sortedLetters); // add to "sortedLetterWords" list
        }
    }

    /**
     * createListB finds the size of the categories by summing ArrayLists, "sortedLetterWords" and "notAnagramWords".
     * Then, creates TheLinkedList array of objects, "listB". The method references each reference word from "categoriesList" and
     * checks each word from String array "firstList" (listA) to see which category the word corresponds to. The word will be added to a
     * TheLinkedList object in "listB" if the word matches with a reference sorted letter word in "sortedLetterWords" ArrayList
     * or if the reference word is a real word from "notAnagram" ArrayList.
     */
    public void createListB()
    {
        int sizeSortedLettersList = sortedLettersWords.size(); // get lengths of ArrayLists
        int sizeNotAnagramList = notAnagramWords.size();
        int totalLinkedLists = sizeSortedLettersList + sizeNotAnagramList; // amount of categories for object array size which is sum

        categoriesList.addAll(sortedLettersWords); // "categoriesList" ArrayList contains reference words from both ArrayLists
        categoriesList.addAll(notAnagramWords);
        Collections.sort(categoriesList); // sort "categoriesList"

        listB = new TheLinkedList[totalLinkedLists]; // instantiate object array with amount of categories
        for(int i = 0; i < listB.length; i++) // iterate through listB
        {
            listB[i] = new TheLinkedList(); // create single object for category
            String theCategory = categoriesList.get(i); // reference word from "sortedLetterWords" or "notAnagram" ArrayLists
            for(int j = 0; j < firstList.length; j++) // iterate through firstList (listA) which is original list passed to this class
            {
                String wordFromListA = firstList[j]; // real word from original list passed to this class
                String whichCategory = bubbleSort(wordFromListA); // sorted letter word reference call
                if(whichCategory.equals(theCategory) || theCategory.equals(wordFromListA)) // checks for sorted letter word or not anagram word
                {
                    listB[i].insertLast(wordFromListA); // inserts into "word" datamember of Node class inside correct TheLinkedList object category
                }
            }
        }
    }

    /**
     * organizeListB sorts listB in alphabetical order vertically. This is done by sorting the first words of each TheLinkedList
     * object, creating a shallow object array used for a temporary array to switch category positions in TheLinkedList
     * array, "listB". This is done in a way that is similar to switching Integer values in an array of Integers using a copy of the
     * array for comparisons and storing values.
     */
    public void organizeListB()
    {
        ArrayList<String> firstWords = new ArrayList<String>(); // ArrayList with the first word of every TheLinkedList object
        for(TheLinkedList object: listB) // iterate through all objects
        {
            firstWords.add(object.getHead().getWord()); // add first word from every TheLinkedList object
        }

        Collections.sort(firstWords); // sort first words

        TheLinkedList[] shallowArray = listB; // shallow copy listB
        for(int i = 0; i < listB.length; i++) // iterate through listB
        {
            String orderedReference = firstWords.get(i); // a reference word to decipher if in order since "firstWords" is in order
            if(!(orderedReference.equals(listB[i].getHead().getWord()))) // if first Node "word" datamember doesn't match "firstWords" element
            {
                for(int j = 0; j < shallowArray.length; j++) // iterate through copied array
                {
                    if(orderedReference.equals(shallowArray[j].getHead().getWord())) // if reference is the same as "word" in copied array element
                    {
                        TheLinkedList shallowObject = listB[i]; // temporary object for switching
                        listB[i] = shallowArray[j];  // switch object positions in both arrays
                        shallowArray[j] = shallowObject;
                    }
                }
            }
        }
    }

    /**
     * createOutputFile() creates "output.txt" file and then uses the PrintWriter class to put text in the file as if
     * it was being printed to the console. The method iterates through "listB" to print each Node "word" element on one line
     * from each TheLinkedList object.
     */
    public void createOutputFile()
    {
        PrintWriter outputWriter = null; // allow for closing in finally block
        try
        {
            File outputFile = new File("output.txt"); //create file
            outputWriter = new PrintWriter(outputFile); // open file
            for(int i = 0; i < listB.length; i++)
            {
                String line = listB[i].printAnagrams(); // call method which prints all Node elements in one TheLinkedList object
                if(i < (listB.length - 1))
                {
                    outputWriter.println(line); // new line until last line
                    System.out.println(line);
                }
                else
                {
                    outputWriter.print(line); // no new line after last line is printed
                    System.out.print(line);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(outputWriter != null)
            {
                outputWriter.close(); // close file
            }
        }
    }

    /**
     * @param input is a String that is a word from listA (firstList)
     * @return result is a String that is a sorted letter word
     * bubbleSort() is one of the simplest sort methods that works by "bubbling up" and moving the higher value letter in the alphabet to the
     * right side one pass at a time. This sort method is sufficient as most words do not contain many letters because the average
     * letters in the English language is approximately 5. Considering the time complexity is O(n^2), run time will not be effected to a
     * great extent unless most words have at least 10 letters which is double the average.
     */
    /*
        bubbleSort() was created with the guidance of the code used from:
        "Tutorial Codes" file BubbleSort.java located under Week - 3 on D2L's tab "Tutorials". The code for switching positions under the if statement
        is exactly the same and the format is similar with different comparisons being used in the for loops to compensate for the different if
        statement comparison. In other words, both for loop comparisons are not comparing the same integer value as in BubbleSort.java
     */
    public String bubbleSort(String input)
    {
        char[] letters = input.toCharArray(); // convert String to char[]

        for(int i = 0; i <= letters.length - 1; i++)  // iterate to and including last position to the right each pass
        {
            for(int j = 0; j <= letters.length - 2; j++) // iterate to and including second last position to the right for comparing
            {
                if(letters[j + 1] < letters[j]) // if right side letter is a lesser value than left side letter, "bubble up" and switch positions
                {
                    char temp = letters[j];
                    letters[j] = letters[j + 1];
                    letters[j + 1] = temp;
                }
            }
        }

        String result = String.valueOf(letters); // convert char[] to String
        return result;
    }
}
