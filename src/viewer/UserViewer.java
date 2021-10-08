package viewer;

import java.util.Scanner;

import Controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

public class UserViewer {
    private UserController userController;
    private Scanner scanner;
    private UserDTO logIn;
    
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
                register();
                
            } else if(userChoice == 2) {
                //로그인 메소드 실행
                logIn();
                
                //정상적으로 로그인 됬는지 아닌지 체크
                if(logIn != null) {
                    //두가지 경우, 1로그인한사람이 관리자일경우 2아닌경우
                    if(logIn.getRank() == RANK_ADMIN) {
                        //관리자 메뉴실행
                        
                    } else {
                        //비관리자 메뉴 실행
                        
                    }
                }
                
                
            } else if(userChoice == 3) {
                //메시지 출력후 종료
                System.out.println("사용해주셔서 감사합니다.");
                scanner.close();
                break;
            }
        }
    }
    
    //회원가입 메소드
    
    private void register() {
        String message;
        
        message = new String("사용하실 아이디를 입력해주세요.");
        String username = ScannerUtil.nextLine(scanner, message);
        
        while(userController.validate(username)) { //유저네임이 중복이면 거절
            System.out.println("해당 아이디는 사용하실 수 없습니다.");
            message = new String("사용하실 아이디를 입력하시거나 뒤로 가시려면 x를 입력하세요.");
            username = ScannerUtil.nextLine(scanner,message);
            
            if(username.equalsIgnoreCase("X")) { //x 누르면 뒤로가기
                break;
            }
        }
        
        //while문 끝났을떄 가능한 두가지 x가아닌경우, x일경우
        if(!username.equalsIgnoreCase("X")) { //x대소문자 상관없이 아닐때
            message = new String("사용하실 비번을 입력하세요.");
            String password = ScannerUtil.nextLine(scanner, message);
            
            message = new String("사용하실 닉네임을 입력하세요.");
            String nickname = ScannerUtil.nextLine(scanner, message);
            
            UserDTO u = new UserDTO();
            u.setUsername(username);
            u.setPassword(password);
            u.setNickname(nickname);
            
            userController.insert(u);
            
        }
    }
    
    private void logIn() {
        String message;
        message = new String("아이디를 입력하세요.");
        String username = ScannerUtil.nextLine(scanner, message);
        
        message = new String("비번을 입력하세요.");
        String password = ScannerUtil.nextLine(scanner, message);
        
        UserDTO u = userController.auth(username, password); //로그인 인증을 받는 메소드에 파라미터대입
        
        while(u == null) { //로그인 인증값이 없는 경우. 로그인 실패
            System.out.println("로그인 정보를 다시 확인해주세요.");
            message = new String("아이디를 입력하시거나 뒤로 가시려면 x를 입력하세요.");
            username = ScannerUtil.nextLine(scanner, message);
            
            if(username.equalsIgnoreCase("x")) { //로그인 안하고 그냥 x
                break;//break 문으로 그냥 빠져나오기                
            }
            
            message = new String ("비밀번호를 입력해주세요");
            password = ScannerUtil.nextLine(scanner, message);
            u = userController.auth(username, password);
            
        }
    
    
    
    }
    

}
