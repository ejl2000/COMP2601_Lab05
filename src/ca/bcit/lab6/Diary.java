package ca.bcit.lab6;

import java.io.*;
import java.util.Scanner;

/**
 * This is the Diary class.
 *
 * @author Nancy Yang
 * @author Emma Lee
 *
 * @version 2.0
 */
public class Diary
{
    private static final    String  DIARY_FILE                  = "diary.txt";
    public static final     String  SPLIT_DELIMITER             = "\\|";
    public static final     int     MAX_SPLIT_DIARY_ENTRY_LEN   = 2;
    public static final     int     DIARY_DATE_INDEX            = 0;
    public static final     int     DIARY_CONTENT_INDEX         = 1;

    /**
     * Adds diary entry to the txt file.
     *
     * @param   date date of the diary entry
     * @param   content content of the diary entry
     * @throws  DiaryEntryException if date or content is in invalid format
     */
    public void addEntry(String date, String content) throws DiaryEntryException
    {
        FileWriter writer;
        writer = null;

        try
        {
            writer = new FileWriter(DIARY_FILE, true);

            writer.write(date + "|" + content + System.lineSeparator());
        }
        catch(IOException e)
        {
            throw new DiaryEntryException("Error adding diary entry: " + e.getMessage());
        }
        finally
        {
            try
            {
                if(writer != null)
                {
                    writer.close();
                }
            }
            catch(IOException e)
            {
                System.out.println("can't close file writer" + e.getMessage());
            }
        }
    }


    /**
     * Views all diary entries in the diary.txt file.
     */
    public void viewAllEntries()
    {
        FileReader  reader;
        Scanner     scanner;

        reader      = null;
        scanner     = null;

        try
        {
            reader  = new FileReader(DIARY_FILE);
            scanner = new Scanner(reader);

            while(scanner.hasNextLine())
            {
                String      line;
                String[]    diaryEachLine;
                //String      diaryDate;
                String      diaryContentSplitLine;

                line                    = scanner.nextLine();
                diaryEachLine           = line.split(SPLIT_DELIMITER); // split each diary entry by the pipeline character
                //diaryDate               = diaryEachLine[DIARY_DATE_INDEX].trim();
                diaryContentSplitLine   = diaryEachLine[DIARY_CONTENT_INDEX].trim();

                if(diaryEachLine.length == MAX_SPLIT_DIARY_ENTRY_LEN)
                {
                    System.out.println("Content: " + diaryContentSplitLine);
                }
                if(diaryEachLine.length > MAX_SPLIT_DIARY_ENTRY_LEN)
                {
                    throw new IllegalArgumentException("Error in diary entry format.");
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Diary file not found.");
        }
        finally
        {
            if(scanner != null)
            {
                scanner.close();
            }
            if(reader != null)
            {
                try
                {
                    reader.close();
                }
                catch(IOException e)
                {
                    System.err.println("can't close file reader!");
                }
            }
        }
    }

    /**
     * Searches diary entry by date.
     *
     * @param date date of the retrieved diary entry.
     */
    public void searchEntriesByDate(String date)
    {
        FileReader  reader;
        Scanner     scanner;

        reader  = null;
        scanner = null;

        try
        {
            reader = new FileReader(DIARY_FILE);
            scanner = new Scanner(reader);

            while (scanner.hasNextLine())
            {
                String line;
                String[] diaryEachLine;

                line            = scanner.nextLine();
                diaryEachLine   = line.split(SPLIT_DELIMITER); // split each diary entry by the pipeline character

                if (diaryEachLine.length == MAX_SPLIT_DIARY_ENTRY_LEN)
                {
                    String diaryDate;
                    String diaryContentSplitLine;

                    diaryDate               = diaryEachLine[DIARY_DATE_INDEX].trim();
                    diaryContentSplitLine   = diaryEachLine[DIARY_CONTENT_INDEX].trim();

                    if (diaryDate.equals(date))
                    {
                        System.out.println("Content: " + diaryContentSplitLine);
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Diary file not found.");
        }
        finally
        {
            if (scanner != null)
            {
                scanner.close();
            }
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    System.err.println("can't close file reader!");
                }
            }
        }
    }
}

