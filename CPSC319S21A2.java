import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * CPSC319S21A2 is called from the command line, reads the input file, and passes listA to FindAnagrams class to be sorted
 */
public class CPSC319S21A2
{
    private static ArrayList<String> inputList = new ArrayList<String>(); // ArrayList for input storage from file (dynamic input)
    private static String[] listA; // Array to be sent to FindAnagrams class to be organized

    /**
     * @param args is a single command line argument which is a filename with text extension. Main method ensures there is one
     * command line argument. Then calls readInputFile() to read file and then sortAndConvert() to sort and convert inputList
     * into an array, which is listA. Finally, main method passes listA to the FindAnagrams class for data organization.
     */
    public static void main(String args[])
    {
        if(args.length != 1) // check if there is one command line arg
        {
            System.out.println("Error: please supply input filename as command line argument");
            System.exit(0);
        }
        else
        {
            readInputFile(args[0]); // pass filename with .txt extension
        }

        sortAndConvert(); // sort and convert to 1D array
        FindAnagrams anagramSolutions = new FindAnagrams(listA); // send listA to find and categorize anagrams and non anagrams
    }

    /**
     * @param fileName - is a String that is a text file name with .txt extension, readInputFile() has no return type.
     * It will receive the command line argument and assume that the file is in the correct directory.
     * It will then read the input file line by line and add each word to the ArrayList, inputList.
     */
    public static void readInputFile(String fileName)
    {
        Scanner reader = null; // allow for closing file in finally block after
        try
        {
            File inputFile = new File(fileName); // create file
            reader = new Scanner(inputFile); // open file for reading
            while(reader.hasNextLine())
            {
                inputList.add(reader.nextLine()); // add each word to ArrayList, inputList
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Error: Unable to open file, '" + fileName + "' not found");
        }
        finally
        {
            if(reader != null)
            {
                reader.close(); // close file
            }
        }
    }

    /**
     * SortAndConvert() has no return type. It will first sort each word in inputList alphabetically using the Collections class.
     * Then store each element into a String array named "listA"
     */
    public static void sortAndConvert()
    {
        Collections.sort(inputList); // sort words alphabetically in "inputList" ArrayList

        int length = inputList.size(); // length of ArrayList
        listA = new String[length];; // initialize listA with amount of elements in inputList

        int i = 0;
        for(String word : inputList) // store words in ListA from inputList (could also use ArrayList conversion method)
        {
            listA[i] = word;
            i++;
        }
    }

}
