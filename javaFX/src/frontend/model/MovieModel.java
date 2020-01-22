package frontend.model;

import java.sql.Date;

/**
 * @author Toma-Florin Ungureanu
 */
public class MovieModel
{
    private String title, genre, language, webPage;
    private Integer budget, duration;
    private Long profit;
    private Date releaseDate;
    private Float rating;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getWebPage()
    {
        return webPage;
    }

    public void setWebPage(String webPage)
    {
        this.webPage = webPage;
    }

    public Integer getBudget()
    {
        return budget;
    }

    public void setBudget(Integer budget)
    {
        this.budget = budget;
    }

    public Long getProfit()
    {
        return profit;
    }

    public void setProfit(Long profit)
    {
        this.profit = profit;
    }

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public Float getRating()
    {
        return rating;
    }

    public void setRating(Float rating)
    {
        this.rating = rating;
    }

    public MovieModel(){}

    public MovieModel(String title, String genre, String language, String webPage, Integer budget, Long profit,
                      Integer duration, Date releaseDate, Float rating)
    {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.webPage = webPage;
        this.budget = budget;
        this.profit = profit;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }
}
