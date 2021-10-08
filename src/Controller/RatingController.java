package Controller;

import java.util.ArrayList;

import model.RatingDTO;
import viewer.UserViewer;

public class RatingController {
    
    private ArrayList<RatingDTO> list;
    private int nextId;
    
    public RatingController() {
        list = new ArrayList<>();
        nextId = 1;
    }
    
    
    // 평점을 list에 추가하는 메소드
    //리스트에 추가만 하는 기능!
    public void add(RatingDTO r) {
        r.setId(nextId++);
        
        list.add(r);
    }
    
    //특정 영화의 평점 리스트를 리턴하는 메소드
    public ArrayList<RatingDTO> selectByMovieId(int movieId){
        ArrayList<RatingDTO> temp = new ArrayList<>();
        
        for(RatingDTO r : list) {
            if(r.getMovieId() == movieId) {
                temp.add(new RatingDTO(r));
            }
        }
        
        return temp;
    }
    
    
    //특정 회원 등급의 평점 리스트를 리턴하는 메소드
//    private int writerId;  //회원의 번호값만 가지고 있음-> 이것만가지고 회원의 등급을 알아내야됨 
    //파라미터로 받아오는 방법으로
    //userController와 RatingCoontroller의 중간다리 역할을 하는 메소드를
    //UserViewer에 작성 selectRankById 메소드
    //여기서 받아오면 아래 메소드에서 특정 회원 등급의 평점 리스트를 리턴
    public ArrayList<RatingDTO> selectByUserRank(UserViewer userViewer, int movieId, int rank){
        ArrayList<RatingDTO> temp = new ArrayList<>();
        
        for(RatingDTO r : list) {
            if(r.getMovieId() == movieId && userViewer.selectRankById(r.getWriterId() == rank)){
                temp.add(new RatingDTO(r));
            }
            
        }
        
        return temp;
    }

    
    
    //파라미터로 들어온 리스트의 평점 평균을 계싼하여 리턴하는 메소드
    
    //특정 영화의 평점을 모두 삭제하는 메소드
    
    //특정 작성자의 평점을 모두 삭제하는 메소드
    
    
    // 개별보기. 수정. 없음!
    // 삭제는 특정영화를 두가지 상황
    // 삭제하면 평점도 같이 사라질때. 특정작성자가 탈퇴해서 작성했던 평점도 같이 사라질때
    

}
