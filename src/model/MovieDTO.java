package model;

//영화와 관련!

public class MovieDTO {
    private int id;
    private String title;
    private String summary;
    private int filmGrade;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public int getFilmGrade() {
        return filmGrade;
    }
    public void setFilmGrade(int filmGrade) {
        this.filmGrade = filmGrade;
    }
    
    public boolean equals(Object o) {
        if(o instanceof MovieDTO) {
            MovieDTO m = (MovieDTO)o;
            return id == m.id;
            
        }
        return false;
    }
    
    

}
