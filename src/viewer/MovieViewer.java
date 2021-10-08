package viewer;

import java.util.Scanner;

import Controller.MovieController;

public class MovieViewer {
    private MovieController movieController; //따로 만들어서 주입하기보다 아래에 그냥 생성자 만들기
    private Scanner scanner;
    
    public MovieViewer() {
        movieController = new MovieController();
    }
    
    //스캐너를 외부에서 받아서 처리할 수 있게 하는 세터메소드
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    

    public void showAdminMenu() {
        String message = new String("1.영화목록보기 2.새로운영화추가 3.뒤로가기");
        while(true) {
            
        }

        
    }

    public void printList() {
        // TODO Auto-generated method stub
        
    }

}
