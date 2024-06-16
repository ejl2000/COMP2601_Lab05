package ca.bcit.lab6;

/**
 * This is a Diary Entry Exception class.
 *
 * @author Nancy Yang
 * @author Emma Lee
 *
 * @version 2.0
 */
public class DiaryEntry
{
    private String              date; //formatted as YYYY-MM-DD
    private String              content;

    private static final int    DATE_LENGTH;
    private static final int    YEAR_INDEX;
    private static final int    MONTH_INDEX;
    private static final int    DAY_INDEX;
    private static final int    MIN_YEAR;
    private static final int    MAX_YEAR;
    private static final int    MIN_MONTH;
    private static final int    MAX_MONTH;
    private static final int    MIN_DAY;
    private static final int    MAX_DAY;
    private static final int    SHORT_MONTH_DAYS;
    private static final int    FEBRUARY;
    private static final int    LEAP_YEAR_DIV_QUARTERLY;
    private static final int    LEAP_YEAR_DIV_CENTURY;
    private static final int    LEAP_YEAR_DIV_QUARTER_CENTURY;
    private static final int    MAX_FEB_DAYS;
    private static final int    LEAP_FEB_DAYS;
    private static final int    INVALID_PARTS_LENGTH;
    private static final int    APRIL;
    private static final int    JUNE;
    private static final int    SEPTEMBER;
    private static final int    NOVEMBER;
    private static final int    INVALID_LEAP_YEAR_VALUE;
    public static final String  FORBIDDEN_DIARY_CONTENT;

    static
    {
        FORBIDDEN_DIARY_CONTENT             = "bcit";
        DATE_LENGTH                         = 10;
        YEAR_INDEX                          = 0;
        MONTH_INDEX                         = 1;
        DAY_INDEX                           = 2;
        MIN_YEAR                            = 0;
        MAX_YEAR                            = 9999;
        MIN_MONTH                           = 1;
        MAX_MONTH                           = 12;
        MIN_DAY                             = 1;
        MAX_DAY                             = 31;
        SHORT_MONTH_DAYS                    = 30;
        FEBRUARY                            = 2;
        LEAP_YEAR_DIV_QUARTERLY             = 4;
        LEAP_YEAR_DIV_CENTURY               = 100;
        LEAP_YEAR_DIV_QUARTER_CENTURY       = 400;
        MAX_FEB_DAYS                        = 28;
        LEAP_FEB_DAYS                       = 29;
        INVALID_PARTS_LENGTH                = 3;
        APRIL                               = 4;
        JUNE                                = 6;
        SEPTEMBER                           = 9;
        NOVEMBER                            = 11;
        INVALID_LEAP_YEAR_VALUE             = 0;
    }

    /**
     * Constructs a diary entry object.
     *
     * @param   date                date of the diary entry.
     * @param   content             content of the diary entry.
     * @throws  DiaryEntryException if date or content is in invalid format.
     */
    public DiaryEntry(String date, String content) throws DiaryEntryException
    {
        if (!validateDate(date))
        {
            throw new DiaryEntryException("Invalid date format or values.");
        }
        validateContent(content);

        this.date       = date;
        this.content    = content;

    }

    /**
     * Gets date of the diary entry.
     *
     * @return date of diary entry.
     */
    public String getDate() throws DiaryEntryException
    {
        if (!validateDate(date))
        {
            throw new DiaryEntryException("Invalid date format or values.");
        }
        return date;
    }

    /**
     * Sets date of diary entry.
     *
     * @param   date                date of diary entry.
     * @throws  DiaryEntryException if date is null or not valid.
     */
    public void setDate(String date) throws DiaryEntryException
    {
        if (!validateDate(date))
        {
            throw new DiaryEntryException("Invalid date format or values.");
        }
        this.date = date;
    }

    /**
     * Gets content.
     *
     * @return content.
     */
    public String getContent()
    {
        return content;
    }

    /**
     * Sets content.
     *
     * @param   content diary content
     * @throws  DiaryEntryException if content is null, blank, or contains the word "bcit"
     */
    public void setContent(String content) throws DiaryEntryException
    {
        validateContent(content);
        this.content = content;
    }

    /**
     * Validates diary content so that it is not null, blank, or contains the word bcit.
     *
     * @param   content diary content
     * @return  true if content is valid, false if not.
     *
     * @throws  DiaryEntryException throws an exception if diary content is null, blank, or contains the word bcit.
     */
    public static boolean validateContent(String content) throws DiaryEntryException
    {
        if(content == null)
        {
            throw new DiaryEntryException("Content cannot be null");
        }
        else if(content.isBlank())
        {
            throw new DiaryEntryException("Content cannot be blank");
        }
        else if(content.toLowerCase().contains(FORBIDDEN_DIARY_CONTENT.toLowerCase()))
        {
            throw new DiaryEntryException("Content cannot contain word" + FORBIDDEN_DIARY_CONTENT);
        }
        return true;
    }

    /**
     * Validates date.
     *
     * @param date date
     * @return true if date is valid; false if not.
     */
    public static boolean validateDate(final String date)
    {
        if (date == null)
        {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        if (date.length() != DATE_LENGTH)
        {
            throw new IllegalArgumentException("Date format must be exactly 10 characters (YYYY-MM-DD).");
        }

        String[] parts = date.split("-");

        if (parts.length != INVALID_PARTS_LENGTH)
        {
            throw new IllegalArgumentException("Date must consist of three parts separated by hyphens.");
        }

        final int year    = Integer.parseInt(parts[YEAR_INDEX]);
        final int month   = Integer.parseInt(parts[MONTH_INDEX]);
        final int day     = Integer.parseInt(parts[DAY_INDEX]);

        // Validate year
        if (year < MIN_YEAR || year > MAX_YEAR)
        {
            throw new IllegalArgumentException("Year must be between " + MIN_YEAR + " and " + MAX_YEAR + ".");
        }

        // Validate month
        else if (month < MIN_MONTH || month > MAX_MONTH)
        {
            throw new IllegalArgumentException("Month must be between " + MIN_MONTH + " and " + MAX_MONTH + ".");
        }

        // Validate day based on month
        else if (day < MIN_DAY || day > MAX_DAY)
        {
            throw new IllegalArgumentException("Day must be between " + MIN_DAY + " and " + MAX_DAY + ".");
        }

        // Check for months with 30 days
        else if ((month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) && day > SHORT_MONTH_DAYS)
        {
            throw new IllegalArgumentException("Day must be between 1 and " + SHORT_MONTH_DAYS + " for months with 30 days.");
        }

        // Check for February month days if it is in a leap year
        else if (month == FEBRUARY)
        {
            if (isLeapYear(year))
            {
                if (day > LEAP_FEB_DAYS)
                {
                    throw new IllegalArgumentException("Day must be between 1 and " + LEAP_FEB_DAYS + " for February in a leap year.");
                }
            }
            else
            {
                if (day > MAX_FEB_DAYS)
                {
                    throw new IllegalArgumentException("Day must be between 1 and " + MAX_FEB_DAYS + " for February.");
                }
            }
        }

        return true; // Date is valid and passed all checks
    }


    /**
     * Checks if the given year is a leap year.
     *
     * @param   year the year to check
     * @return  true if the year is a leap year, false otherwise
     */
    private static boolean isLeapYear(final int year)
    {
        if(year % LEAP_YEAR_DIV_QUARTERLY != INVALID_LEAP_YEAR_VALUE)
        {
            return false;
        }
        else if (year % LEAP_YEAR_DIV_CENTURY != INVALID_LEAP_YEAR_VALUE)
        {
            return true;
        }
        else
        {
            return year % LEAP_YEAR_DIV_QUARTER_CENTURY == INVALID_LEAP_YEAR_VALUE;
        }
    }
}

