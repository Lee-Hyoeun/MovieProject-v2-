package viewer;

import java.util.Scanner;

import Controller.UserController;
import util.ScannerUtil;

public class UserViewer {
    private UserController userController;
    private Scanner scanner;
    
    //필드를 초기화할 생성자
    public UserViewer() {
        userController = new UserController();
        scanner = new Scanner(System.in);
        
    }
    
    //인덱스 화면
    public void showIndex() {
        String message = new String("1.회원가입  2.로그인  3.종료");
        while(true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if(userChoice == 1) {
                //회원가입 메소드 실행
                
            } else if(userChoice == 2) {
                //로그인 메소드 실행
                
            } else if(userChoice == 3) {
                //메시지 출력후 종료
                System.out.println("사용해주셔서 감사합니다.");
                scanner.close();
                break;
            }
        }
    }
    

}
