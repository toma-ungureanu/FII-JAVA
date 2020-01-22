package app;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.util.*;

public class LocaleExplorer
{
    private final String baseName = "resources.Messages";
    private Locale locale;
    private ResourceBundle resourceBundle;

    public void run()
    {
        setLocale("ro-RO");
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.print(message("prompt"));
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String[] params = command.trim().split("\\s+");
            switch (params[0])
            {
                case "locales":
                    displayLocales();
                    break;
                case "set":
                    setLocale(params[1]);
                    break;
                case "info":
                    localeInfo();
                    break;
                default:
                    System.out.println(message("invalid"));
            }
        }
    }

    private String message(String key, String... arguments)
    {
        String pattern = resourceBundle.getString(key);
        return new MessageFormat(pattern).format(arguments);
    }

    private void setLocale(String languageTag)
    {
        this.locale = Locale.forLanguageTag(languageTag);
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
        message("locale.set", languageTag);
    }

    private void displayLocales()
    {
        System.out.println(message("locales"));


    }

    private void localeInfo()
    {
        message("locale.set");

        //country
        System.out.println("Country: " + locale.getCountry());

        //language
        System.out.println("Language: " + locale.getLanguage());

        //currency
        Currency currency = Currency.getInstance(locale);
        System.out.println("Currency: " + currency);

        //week days
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        String[] weekDays = dateFormatSymbols.getWeekdays();

        System.out.print("Week Days: ");
        for(int week = 0; week < weekDays.length; week++)
        {
            System.out.print(weekDays[week]);
            System.out.print(", ");
        }

        System.out.println();

        //months
        String[] months = dateFormatSymbols.getMonths();
        System.out.print("Months: ");
        for(int month = 0; month < months.length; month++)
        {
            System.out.print(months[month]);
            System.out.print(", ");
        }
        System.out.println();

        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.JAPAN);
        Date today = new Date();
        System.out.printf("%s%n", dateFormat.format(today));
    }

    public static void main(String args[])
    {
        new LocaleExplorer().run();
    }
}