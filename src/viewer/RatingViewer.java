package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.RatingController;
import model.RatingDTO;
import model.UserDTO;
import util.ScannerUtil;

public class RatingViewer {
    private RatingController ratingController;
    
    //아래 세개는 setter로 의존성을 주입해줌
    private UserViewer userViewer;
    private Scanner scanner;
    private UserDTO logIn;
    
    private final int CATEGORY_ALL = 1; //전체 평점
    private final int RANK_CRITIC = 2; //평론가 평점
    private final int RATING_MIN = 1;
    private final int RATING_MAX = 5;
    
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

    //평점리스트를 보여주는 메소드
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
        userViewer.printNicknameById(r.getWriterId());
        
        // printf에서 출력된 글의 형식을 저장한 String 변수
        String format;
        
        //만약 파라미터 r의 review 필드가 비어있을 경우에는 일반 회원이므로 (일반 회원은 평론을 못남기니깐!)
        // ": %d\n"을 format에 저장하고
        //그외에는
        // ": %s - %d\n"을 format에 저장한다.
        
        if(r.getReview().isEmpty()) {
            format = new String(": %d\n");
            System.out.printf(format, r.getRate()); //파라미터가 하나

        } else {
            format = new String(": %s - %d\n");
            System.out.printf(format, r.getReview(), r.getRate()); //파라미터가 두걔!

        }

    }
    
    
    //등록하는 메소드
    public void add(int movieId) {
        RatingDTO r = new RatingDTO();
        
        r.setMovieId(movieId);
        r.setWriterId(logIn.getId());
        
        String message;
        message = new String("해당 영화의 평점을 입력해주세요.");
        r.setRate(ScannerUtil.nextInt(scanner, message, RATING_MIN, RATING_MAX));
        
        if(userViewer.selectRankById(logIn.getId()) == RANK_CRITIC) {
            message = new String("해당 영화의 평론을 입력해주세요.");
            r.setReview(ScannerUtil.nextLine(scanner, message));
        }
        
        ratingController.add(r);
    }
    
}
