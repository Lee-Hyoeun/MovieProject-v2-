package viewer;

import java.util.Scanner;

import Controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

public class UserViewer {
    private UserController userController;
    private Scanner scanner;
    private UserDTO logIn;
    
    //네개 필드 추가
    private MovieViewer movieViewer;
    private TheaterViewer theaterViewer;
    private ShowViewer showViewer;
    private RatingViewer ratingViewer;
    
    private final int RANK_ADMIN = 1;
    private final int RANK_CRITIC = 2;
    
    //필드를 초기화할 생성자
    public UserViewer() {
        userController = new UserController();
        scanner = new Scanner(System.in);
       
    }
    
    //의존성 주입을 위한 셋터
    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
        this.movieViewer.setScanner(scanner); //이렇게 적어주면 일일히 만들어? 줄 필요 없다고,,
    }
    
    public void setTheaterViewer(TheaterViewer theaterViewer) {
        this.theaterViewer = theaterViewer;
    }
    
    public void setShowViewer(ShowViewer showViewer) {
        this.showViewer = showViewer;
    }
    
    public void setRatingViewer(RatingViewer ratingViewer) {
        this.ratingViewer = ratingViewer;
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
                    movieViewer.setLogIn(logIn);
                    
                    //두가지 경우, 1로그인한사람이 관리자일경우 2아닌경우
                    if(logIn.getRank() == RANK_ADMIN) {
                        //관리자 메뉴실행
                        showAdminMenu();
                        
                    } else {
                        //비관리자 메뉴 실행
                        showNonAdminMenu();
                        
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
        
        logIn = u; //UserDTO에 u 로그인 정보들어감    
    }
    
    
    //관리자용 메인화면
    //로그인이 관리자일 경우 나오는 인덱스 메소드
    private void showAdminMenu() {
        String message = new String("1.영화관리 2.극장관리 3.상영정보관리 4.로그아웃");
        
        while(true) {
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
            if(userChoice == 1) {
                // MovieViewer의 showAdminMenu() 실행 //클래스 필드 만들어서 의존성 주입하는 방식!
                movieViewer.showAdminMenu();
                
             } else if(userChoice == 2) {
                 //TheaterViewer의 showAdminMenu() 실행
                 theaterViewer.showAdminMenu(); //객체 지향의 강점. 여기서 잘몰라도 됨. 그냥 객체만 영끌해서 씀.
                 
             } else if(userChoice == 3) {
                 //ShowViewer의 showAdminMenu() 실행
                 showViewer.showAdminMenu();
                 
             } else {
                 System.out.println("로그아웃되셨습니다.");
                 logIn = null;
                 break;
             }
        }
    
    }
    
    //비관리자용 메인화면
    //로그인이 관리자가 아닌경우 나오는 인덱스 메소드
    private void showNonAdminMenu() {
        String message = new String("1.영화목록보기 2.극장목록보기 3.내정보보기 4.로그아웃");
        
        while(true) {
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
            if(userChoice == 1) {
                // MovieViewer의 printList() 실행 //클래스 필드 만들어서 의존성 주입하는 방식!
                movieViewer.printList();

                
             } else if(userChoice == 2) {
                 //TheaterViewer의 printList() 실행
                 theaterViewer.printList();
                 
             } else if(userChoice == 3) {
                 //개별 회원 정보 보기 메소드 실행
                 printOne(logIn.getId()); //로그인한 회원번호로 내정보보기 메소드를 실행
                 
             } else if(userChoice == 4) {
                 System.out.println("로그아웃되셨습니다.");
                 logIn = null; //널로 초기화            
                 //break; //로그아웃 널포인트로 에러날수 잇어서 아래와 같이 한 만약을 둬서 break실행
             }
            
            if(logIn == null) {
                break;
            }
            
            
            
        }
    }
    
    //3. 내 정보보기 메소드
    private void printOne(int id) {
        UserDTO u = userController.selectOne(id);
        
        //회원등급 숫자값을 문자로 바꾸는 코드
        String rank;
        
        if(u.getRank() == RANK_ADMIN) {
            rank = new String("관리자");
        } else if(u.getRank() == RANK_CRITIC) {
            rank = new String("평론가");
        } else {
            rank = new String("일반 회원");
        }
        
        
        System.out.println("===================");
        System.out.println(u.getNickname()+"님의 정보");
        System.out.println("===================");
        System.out.println("아이디: "+u.getUsername());
        System.out.println("닉네임: "+u.getNickname());
        System.out.println("회원등급: " + rank);
        //회원등급은 int숫자값으로 가지고 있음! 근데 위에서 if문으로 바꿔서 String으로 출력OK!
        System.out.println("===================");
        
        String message = new String("1.회원정보수정 2.회원탈퇴 3.뒤로가기");
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
        
        if(userChoice == 1) {
            //회원 정보 수정 메소드 실행
            update(id);
            
        } else if(userChoice == 2) {
            //회원 탈퇴 메소드 실행
            withdrawl(id);
        }
    }
    
    
    //회원 정보 수정 메소드
    private void update(int id) {
        UserDTO u = userController.selectOne(id);
        
        String message;
        
        message = new String("새로운 비밀번호를 입력해주세요.");
        u.setPassword(ScannerUtil.nextLine(scanner, message));
        
        message = new String("새로운 닉네임을 입력해주세ㅛ.");
        u.setNickname(ScannerUtil.nextLine(scanner, message));
        
        userController.update(u);
        printOne(id);
        
    }
    
    //탈퇴 메소드
    private void withdrawl(int id) {
        String message = new String("정말로 탈퇴? Y/N");
        String yesNo = ScannerUtil.nextLine(scanner, message);
        
        if(yesNo.equalsIgnoreCase("Y")) {
            userController.delete(id);
            logIn = null;// 로그인 객체를 없애기 널로 만들어서
            // RatingViewer의 deleteByWriterId() 실행
            //이 회원이 가진 평점 기록도 모두 삭제되도록!
            ratingViewer.deleteByWriterId(id);
            
            System.out.println("회원 탈퇴가 완료되었습니다.");
            
            
        } else {
            printOne(id);
        }
    }
    
    // 해당 회원 번호를 가진 회원의
    // 닉네임만 출력하는 메소드
    public void printNicknameById(int id) {
        UserDTO u = userController.selectOne(id);
        System.out.println(u.getNickname());
                
    }
    
    // 해당 회원번호를 가진 회원의
    // 회원 등급을 리턴하는 메소드
    public int selectRankById(int id) {
        return userController.selectOne(id).getRank();
    }
    
    

}
