package model;

public class RatingDTO {
    private int id;
    private int writerId;   //회원의 번호값만 가지고 있음-> 이것만가지고 회원의 등급을 알아내야됨 
    private int movieId;
    private int rate;
    private String review;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getWriterId() {
        return writerId;
    }
    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }
    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
    
    
    public boolean equals(Object o) {
        if(o instanceof RatingDTO) {
            RatingDTO r = (RatingDTO)o;
            return id == r.id;
        }
        return false;
    }
    
    
    public RatingDTO() {
        id = 0;
        writerId = 0;
        movieId = 0;
        rate = 0;
        review = new String();
    }
    
    public RatingDTO(RatingDTO r) {
        id = r.id;
        writerId = r.writerId;
        movieId = r.movieId;
        rate = r.rate;
        review = new String(r.review);
    }

}
