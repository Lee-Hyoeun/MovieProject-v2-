package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.RatingController;
import model.RatingDTO;
import model.UserDTO;

public class RatingViewer {
    private RatingController ratingController;
    
    //아래 세개는 setter로 의존성을 주입해줌
    private UserViewer userViewer;
    private Scanner scanner;
    private UserDTO logIn;
    
    private final int CATEGORY_ALL = 1;
    
    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setLogIn(UserDTO logIn) {
        this.logIn = logIn;
    }

    
    public void deleteByWriterId(int writerId) {
        ratingController.deleteByWriterId(writerId);
        
    }

    
    public void printList(int movieId, int category) {
        ArrayList<RatingDTO> list;
        
        if(category == CATEGORY_ALL) {
            list = ratingController.selectByMovieId(movieId);
        } else {
            list = ratingController.selectByUserRank(userViewer, movieId, category);
        }
        
        System.out.println("-------------------------------");
        if(list.isEmpty()) {
            System.out.println("해당 영화의 평점은 아직 없습니다.");
        } else {
            System.out.println("평점: " + ratingController.calculateAverage(list));
            
            for(RatingDTO r : list) {
                //평점 출력 메소드 실행
                printOne(r);
            }
        }
        System.out.println("-------------------------------");
        
    }
    
    
    private void printOne(RatingDTO r) {
        //평론가는 "닉네임: 평론 - 점수"의 형식으로 출력이 되고
        //일반 회원은 "닉네임: 점수"로 출력 되게 코드를 작성해보자
        
        
    }
}
