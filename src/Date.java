package src;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Public Date class that creates a Date object with month, day, year
 * @author Arnold Nguyen, Sofia Juliani
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    /**
     * Creates an instance of Calendar object
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.year = calendar.get(Calendar.YEAR);
    }
    /**
     * From string input, creates Date object using StringTokenizer
     * @param date String in format MONTH/DAY/YEAR
     */
    public Date(String date) {
        StringTokenizer token = new StringTokenizer(date, "/");
        month = Integer.parseInt(token.nextToken());
        day = Integer.parseInt(token.nextToken());
        year = Integer.parseInt(token.nextToken());
    } //take “mm/dd/yyyy” and create a Date object

    /**
     * Determines the validity of a date.
     * checks for leap year edge case for february.
     * @return true if the date is valid, false otherwise
     */
    public boolean isValid()
    {
        if (this.month < 1 || this.month > 12 || this.day < 1 || this.day > 31) {
            return false;
        } else if ((this.month == 4 || this.month == 6 || this.month == 9
                || this.month == 11) && this.day >= 31)
        {
            return false;
        }
        if (this.month == 2 && this.day > 29) {
            return false;
        }
        if (this.month == 2 && this.day == 29){
            if (this.year % QUADRENNIAL == 0){
                if (this.year % CENTENNIAL == 0) {
                    if (this.year % QUATERCENTENNIAL == 0) {
                        return true;
                    }
                    else
                        return false;
                }
                else
                    return true;
            }
            else
                return false;
        }
        return true;
    }

    /**
     * Getter method for year
     * @return year of Date object
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Getter method for month
     * @return month of Date object
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Getter method for day
     * @return day of Date object
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Determines if two Date objects are equal to each other.
     * @param obj that takes in Date object
     * @return true if Date objects are equivalent to each other
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date){
            Date dateObj = (Date) obj;
            return Boolean.logicalAnd(dateObj.year == this.year,
                    Boolean.logicalAnd(dateObj.month == this.month,
                            dateObj.day == this.day));
        }
        return false;
    }

    /**
     * Compare with Date obj
     * @param obj the object to be compared.
     * @return will finish
     */
    @Override
    public int compareTo(Date obj){
        if (this.year > obj.year)
            return 1;
        else if (this.year < obj.year)
            return -1;
        else if (this.month > obj.month)
            return 1;
        else if (this.month < obj.month)
            return -1;
        else if (this.day > obj.day)
            return 1;
        else if (this.day < obj.day)
            return -1;
        return 0;
    }

    /**
     * toString method for Date class that lists the date
     * @return the Date to string form
     */
    @Override
    public String toString()
    {
        return month + "/" + day + "/" + year;
    }

    /**
     * static void main to test Date isValid() method
     * @param args pass in arguments as a String
     */
    public static void main(String[] args)
    {
        Date date1 = new Date("2/29/2003");
        if (!date1.isValid())
        {
            System.out.println("DOB invalid: " + date1 + " not a valid calendar date!");
        }
        Date date2 = new Date("4/31/2003");
        if (!date2.isValid())
        {
            System.out.println("DOB invalid: " + date2 + " not a valid calendar date!");
        }
        Date date3 = new Date("13/31/2003");
        if (!date3.isValid())
            System.out.println("DOB invalid: " + date3 + " not a valid calendar date!");

        Date date4 = new Date("3/32/2003");
        if (!date4.isValid())
            System.out.println("DOB invalid: " + date4 + " not a valid calendar date!");

        Date date5 = new Date("-1/31/2003");
        if (!date5.isValid())
            System.out.println("DOB invalid: " + date5 + " not a valid calendar date!");

        Date date6 = new Date("2/28/2004");
        if (date6.isValid())
            System.out.println("DOB Valid: " + date6 + " is a valid calendar date!");

        Date date7 = new Date("3/20/2003");
        if (date7.isValid())
            System.out.println("DOB Valid: " + date7 + " is a valid calendar date!");
    }

}