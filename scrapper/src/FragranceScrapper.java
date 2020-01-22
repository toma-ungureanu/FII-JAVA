import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Random;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

/**
 * @author Toma-Florin Ungureanu
 */
public class FragranceScrapper
{
    public enum NOTES
    {
        Aldehyde, Powdery, Animalic, Musk, Aquatic, Herbacious, Beverages, Citric,
        Earthy, Grain, Floral, Fruity, Gourmandy, Mossy, Green, Resinous,
        Leather, Textile, Synthetic, Amber, Oriental, Balsamic, Mineral, Spicy, Tobbaco,
        Woody, Smoky, Tea
    }

    public enum SEASONS
    {
        Spring, Summer, Autumn, Winter
    }

    public enum OCCASIONS
    {
        Work, Interview, Date, Party, Social, OffDuty
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        String gender = "man";
        int maxPage = 0;
        String fileString = new String();
        String url = new String();
        if(gender.equals("man"))
        {
            maxPage = 51;
            fileString = "manFragrances.txt";
            url = "http://www.basenotes.net/fragrancedirectory/" +
                    "?gender=m&launch1=1920&launch=2&avail=in+production&p=";
        }

        else if(gender.equals("woman"))
        {
            maxPage = 56;
            fileString = "womanFragrances.txt";
            url = "http://www.basenotes.net/fragrancedirectory/" +
                    "?gender=f&launch1=1920&launch=2&avail=in+production&p=";
        }

        else if(gender.equals("shared"))
        {
            maxPage = 56;
            fileString = "sharedFragrances.txt";
            url = "http://www.basenotes.net/fragrancedirectory/" +
                    "?gender=s&launch1=1920&launch=2&avail=in+production&p=";
        }


        BufferedWriter writer = new BufferedWriter(new FileWriter(fileString, true));
        for (int page = 1; page <= maxPage - 1; page++)
        {
            Document doc = Jsoup.connect(url + page)
                    .timeout(6000).get();

            //Perfume and search
            Elements tables = doc.getElementsByTag("tbody");

            //remove the scraped search
            tables.remove(0);

            Element perfumeScrapedData = tables.get(0);

            Elements perfumeNamesAndBrands = new Elements();
            int noOfChildren = perfumeScrapedData.childNodeSize();

            for (int child = 0; child < noOfChildren; child += 2)
            {
                perfumeNamesAndBrands.add(perfumeScrapedData.child(child));
            }


            perfumeNamesAndBrands.remove(20);
            perfumeNamesAndBrands.remove(40);
            if(page != 51)
            {
                perfumeNamesAndBrands.remove(60);
            }
            Gson gson = new Gson();
            for (Element perfumeData : perfumeNamesAndBrands)
            {
                perfumeData.attributes().remove("valign");
                perfumeData.child(0).attributes().remove("style");
                perfumeData.child(0).attributes().remove("align");
                perfumeData.child(0).attributes().remove("width");
                perfumeData.child(0).child(0).attributes().remove("href");
                perfumeData.child(1).attributes().remove("style");
                perfumeData.child(0).child(0).child(0).attributes().remove("border");
                perfumeData.child(1).child(0).child(0).attributes().remove("href");
                perfumeData.child(1).child(0).child(0).child(1).remove();
                perfumeData.child(1).child(0).child(0).child(0).remove();

                if (perfumeData.child(1).children().size() > 1)
                {
                    perfumeData.child(1).child(1).remove();
                }

                FragranceModel fragranceModel = new FragranceModel();
                fragranceModel.setImage(perfumeData.child(0).child(0).child(0).attributes().get("src"));
                if (fragranceModel.getImage().contains("http://www.basenotes.net/photos/products/"))
                {
                    StringBuilder stb = new StringBuilder(fragranceModel.getImage());
                    stb.setCharAt(41, '3');
                    fragranceModel.setImage(stb.toString());
                } else
                {
                    StringBuilder stb = new StringBuilder(fragranceModel.getImage());
                    stb.setCharAt(32, '3');
                    fragranceModel.setImage(stb.toString());
                }

                String strippedName = perfumeData.child(1).child(0).child(0).child(0).toString()
                        .replace("<br>", "")
                        .replace("small", "")
                        .replace("<", "")
                        .replace(">", "")
                        .replace("/", "")
                        .replace("by", "")
                        .substring(2);

                strippedName = strippedName.substring(0, strippedName.length() - 7);
                fragranceModel.setBrand(strippedName);

                String fullData = perfumeData.child(1).child(0).child(0).text();
                int length = fullData.length() - strippedName.length() - 11;
                if (length > 0)
                {
                    fragranceModel.setName(fullData.substring(0, length));
                }
                setRandomData(fragranceModel, writer, gson);
            }
        }
        writer.close();
    }

    private static void setRandomData(FragranceModel fragranceModel, BufferedWriter writer, Gson gson)
            throws IOException
    {
        int quantity = 0;
        Random random = new Random();
        int price = random.nextInt(1000) + 100;
        String randomNotes = getRandomNotes();
        String randomDate = getRandomDate();
        String randomSeason = getRandomSeason().toString();
        String randomOccasion = getRandomOccasion().toString();

        for (int i = 0; i < 4; i++)
        {
            if (i == 0)
            {
                quantity = 50;
                fragranceModel.setPrice(Math.round(price * 0.75));
            } else if (i == 1)
            {
                quantity = 100;
                fragranceModel.setPrice(Math.round(price));
            } else if (i == 2)
            {
                quantity = 150;
                fragranceModel.setPrice(Math.round(price * 1.20));
            } else if (i == 3)
            {
                quantity = 200;
                fragranceModel.setPrice(Math.round(price * 1.40));
            }
            fragranceModel.setQuantity(quantity);
            fragranceModel.setNotes(randomNotes);
            fragranceModel.setReleaseDate(randomDate);
            fragranceModel.setSeason(randomSeason);
            fragranceModel.setOccasion(randomOccasion);
            writeData(fragranceModel, writer, gson);
        }
    }

    private static String getRandomDate()
    {
        Faker faker = new Faker();
        Date randomDate;
        LocalDate localDate;
        float random = new Random().nextFloat();

        if (random < 0.2)
        {
            randomDate = faker.date().birthday(30, 99);
            localDate = new java.sql.Date(randomDate.getTime()).toLocalDate();
        } else
        {
            randomDate = faker.date().birthday(0, 29);
            localDate = new java.sql.Date(randomDate.getTime()).toLocalDate();
        }

        return localDate.toString();
    }

    private static String getRandomNotes()
    {
        float random = new Random().nextFloat();
        String notes = new String();
        int count = 0;

        if(random < 0.2)
        {
            count = 5;
        }

        if(random > 0.2 && random < 0.3)
        {
            count = 4;
        }

        if(random > 0.3 && random < 0.5)
        {
            count = 3;
        }

        if(random > 0.5)
        {
            count = 2;
        }


        for(int i = 0; i < count; i++)
        {
            String actualNote = getRandomNote().toString();
            while(notes.contains(actualNote))
            {
                actualNote = getRandomNote().toString();
            }

            if(i == count - 1)
            {
                notes += actualNote;
            }
            else
            {
                notes += actualNote + ", ";
            }

        }

        return notes;
    }

    private static NOTES getRandomNote()
    {
        Random random = new Random();
        return NOTES.values()[random.nextInt(NOTES.values().length)];
    }

    private static OCCASIONS getRandomOccasion()
    {
        Random random = new Random();
        return OCCASIONS.values()[random.nextInt(OCCASIONS.values().length)];
    }

    private static SEASONS getRandomSeason()
    {
        Random random = new Random();
        return SEASONS.values()[random.nextInt(SEASONS.values().length)];
    }

    private static void writeData(FragranceModel fragranceModel, BufferedWriter writer, Gson gson) throws IOException
    {
        writer.write(gson.toJson(fragranceModel));
        writer.write("\n");
    }
}
