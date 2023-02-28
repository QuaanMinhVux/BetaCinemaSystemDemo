package model;


public class Movie {
    
    
    private int MovieID;
    private String Title;
    private int Year;
    private String Image;// anh
    private String Description;//Noi dung film 
    private String Genere;//The loai
    private double rate;//Diem danh gia 

    public Movie() {
    }

    public Movie(int MovieID, String Title, int Year, String Image, String Description, String Genere, double rate) {
        this.MovieID = MovieID;
        this.Title = Title;
        this.Year = Year;
        this.Image = Image;
        this.Description = Description;
        this.Genere = Genere;
        this.rate = rate;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getGenere() {
        return Genere;
    }

    public void setGenere(String Genere) {
        this.Genere = Genere;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    
}