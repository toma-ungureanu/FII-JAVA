package backend.object_oriented_model;

import frontend.model.MovieModel;

import java.sql.Date;

/**
 * @author Toma-Florin Ungureanu
 */
public class AppMovie
{
    private Integer budget, duration;
    private String language, title, genre, webPage;
    private Date launchDate;
    private Long profit;
    private Float rating;

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

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

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

    public String getWebPage()
    {
        return webPage;
    }

    public void setWebPage(String webPage)
    {
        this.webPage = webPage;
    }

    public Date getLaunchDate()
    {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate)
    {
        this.launchDate = launchDate;
    }

    public Float getRating()
    {
        return rating;
    }

    public void setRating(Float rating)
    {
        this.rating = rating;
    }

    public AppMovie(Integer budget, Long profit, Integer duration, String language, String title, String genre,
                    String webPage, Date launchDate, Float rating)
    {
        this.budget = budget;
        this.profit = profit;
        this.duration = duration;
        this.language = language;
        this.title = title;
        this.genre = genre;
        this.webPage = webPage;
        this.launchDate = launchDate;
        this.rating = rating;
    }

    public AppMovie(MovieModel movieModel)
    {
        setWebPage(movieModel.getWebPage());
        setLanguage(movieModel.getLanguage());
        setLaunchDate(movieModel.getReleaseDate());
        setGenre(movieModel.getGenre());
        setTitle(movieModel.getTitle());
        setBudget(movieModel.getBudget());
        setDuration(movieModel.getDuration());
        setProfit(movieModel.getProfit());
        setRating(movieModel.getRating());
    }
}
