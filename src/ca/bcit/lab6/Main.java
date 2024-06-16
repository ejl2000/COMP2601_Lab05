package ca.bcit.lab6;

import java.util.Scanner;

/**
 * This is the Main class, displays the text based menu.
 *
 * @author Nancy Yang
 * @author Emma Lee
 *
 * @version 2.0
 */
public class Main
{
    private static final String ADD_NEW_ENTRY_BUTTON                = "1";
    private static final String VIEW_ALL_ENTRIES_BUTTON             = "2";
    private static final String SEARCH_ENTRIES_BY_DATE_BUTTON       = "3";
    private static final String EXIT_BUTTON                         = "4";
    private static final String QUIT_SEARCH                         = "y";
    private static final int    INITIAL_YEAR_SPLIT_INDEX            = 0;
    private static final int    TERMINAL_YEAR_SPLIT_INDEX           = 4;
    private static final int    INITIAL_MONTH_SPLIT_INDEX           = 5;
    private static final int    TERMINAL_MONTH_SPLIT_INDEX          = 7;
    private static final int    INITIAL_DAY_SPLIT_INDEX             = 8;
    private static final int    TERMINAL_DAY_SPLIT_INDEX            = 10;


    /**
     * The main method of the program.
     *
     * @param args command line arguments; not used here.
     * @throws DiaryEntryException if the user input for date and diary content are invalid
     *
     */
    public static void main(final String[] args) throws DiaryEntryException
    {
        final Scanner scanner;
        final Diary   diary;

        scanner = new Scanner(System.in);
        diary   = new Diary();

        generalQueriesMenuSearch(scanner, diary);
    }
    public static void generalQueriesMenuSearch(final Scanner scanner, final Diary diary)
            throws DiaryEntryException
    {
        System.out.println("Personal Diary Management System:");
        System.out.println("1. Add New Entry");
        System.out.println("2. View all entries");
        System.out.println("3. Search entries by date");
        System.out.println("4. Exit");

        String userInput = scanner.next();

        if (userInput.equals(ADD_NEW_ENTRY_BUTTON))
        {
            System.out.println("Enter the date (YYYY-MM-DD):");

            String date;
            date = scanner.next();

            try
            {
                // date format needs to be converted into integers to be validated
                int year    = Integer.parseInt(date.substring(INITIAL_YEAR_SPLIT_INDEX, TERMINAL_YEAR_SPLIT_INDEX ));
                int month   = Integer.parseInt(date.substring(INITIAL_MONTH_SPLIT_INDEX, TERMINAL_MONTH_SPLIT_INDEX));
                int day     = Integer.parseInt(date.substring(INITIAL_DAY_SPLIT_INDEX, TERMINAL_DAY_SPLIT_INDEX)); //

                // validate the date data after they are converted into integers
                if (!DiaryEntry.validateDate(date))
                {
                    throw new DiaryEntryException("Invalid date format or values.");
                }

            scanner.nextLine();

            System.out.println("Enter your diary content:");

            String content;
            content = scanner.nextLine();

            if (!DiaryEntry.validateContent(content))
            {
                throw new DiaryEntryException("Invalid content.");
            }

            diary.addEntry(date, content);

            System.out.println("Diary entry added successfully.");

            }
            catch (NumberFormatException e)
            {
                throw new DiaryEntryException("Invalid date format or date range. Please double check date entered.");
            }

            System.out.println("Do you want to go back to the main menu? Enter 'y' for yes, any other key to exit search.");
            userInput = scanner.next();
        }
        if (userInput.equalsIgnoreCase(QUIT_SEARCH))
        {
            generalQueriesMenuSearch(scanner, diary); // goes back to the start of the menu search
        }
        if (userInput.equals(VIEW_ALL_ENTRIES_BUTTON))
        {
            diary.viewAllEntries();

            System.out.println("Do you want to go back to the main menu? Enter 'y' for yes, any other key to exit search.");
            userInput = scanner.next();
        }
        else if (userInput.equalsIgnoreCase(QUIT_SEARCH))
        {
            generalQueriesMenuSearch(scanner, diary); // goes back to the start of the menu search
        }
        if (userInput.equals(SEARCH_ENTRIES_BY_DATE_BUTTON))
        {
            System.out.println("Enter the date (YYYY-MM-DD) of search:");
            String date;
            date = scanner.next();

            diary.searchEntriesByDate(date);
            System.out.println("Do you want to go back to the main menu? Enter 'y' for yes, any other key to exit search.");
            userInput = scanner.next();
        }
        else if (userInput.equalsIgnoreCase(QUIT_SEARCH))
        {
            generalQueriesMenuSearch(scanner, diary); // goes back to the start of the menu search
        }
        if (userInput.equals(EXIT_BUTTON))
        {
            System.out.println("Exiting... Goodbye!");
        }
        else
        {
            System.out.println("Exiting... Goodbye!");
        }
    }
}
