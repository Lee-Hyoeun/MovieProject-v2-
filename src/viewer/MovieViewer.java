package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieController;
import model.MovieDTO;
import model.UserDTO;
import util.ScannerUtil;

public class MovieViewer {
    private MovieController movieController; // 따로 만들어서 주입하기보다 아래에 그냥 생성자 만들기
    private Scanner scanner;
    private UserDTO logIn;
    private RatingViewer ratingViewer;
    
    private final int RANK_ADMIN = 1;
    
    // 평점을 전체, 평론가, 일반관람객용으로 각 출력해줄 상수
    private final int CATEGORY_ALL = 1;
    private final int CATEGORY_CRITIC = 2;
    private final int CATEGORY_GENERAL = 3;


    public MovieViewer() {
        movieController = new MovieController();
    }    

    // 스캐너를 외부에서 받아서 처리할 수 있게 하는 세터메소드
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void setLogIn(UserDTO logIn) {
        this.logIn = logIn;
        
    }
    
    public void setRatingViewer(RatingViewer ratingViewer) {
        this.ratingViewer = ratingViewer;
    }


    public void showAdminMenu() {
        String message = new String("1.영화목록보기 2.새로운영화추가 3.뒤로가기");
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                // 영화목록보기 메소드 실행
                printList();

            } else if (userChoice == 2) {
                // 새로운영화추가 메소드 실행
                add();

            } else if (userChoice == 3) {
                System.out.println("메인 화면으로 이동 합니다.");
                break;
            }

        }

    }

    // 1.영화목록보기 메소드
    public void printList() {
        ArrayList<MovieDTO> list = movieController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 영화가 없습니다.");
        } else {
            System.out.println("-----------------");
            for (MovieDTO m : list) {
                System.out.printf("%d. %s\n", m.getId(), m.getTitle());
            }
            System.out.println("-----------------");

            String message = new String("상세보기할 영화의 번호나 뒤로가시려면 0을 입력해주세요.");
            int userChoice = ScannerUtil.nextInt(scanner, message);

            while (userChoice != 0 && movieController.selectOne(userChoice) == null) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }

            if (userChoice != 0) {
                // 영화 목록 중 개별 상세보기 보여줄 메소드 실행
                printOne(userChoice);
            }
        }

    }

    // 영화 목록 중 개별 상세보기 보여줄 메소드
    private void printOne(int id) {
        MovieDTO m = movieController.selectOne(id);

        System.out.println("------------------------");
        System.out.println("제목: " + m.getTitle());
        System.out.println("등급: " + m.getFilmGrade() + "세 이용가");
        System.out.println("------------------------");
        System.out.println(m.getSummary());
        System.out.println("------------------------");
        System.out.println();

        // 여기서 두가지 선택. 관리자 일떄 수정,삭제 가능. 관리자가 아니면 평점보고 각각에 기능이 생기도록
        // 일단 로그인한 사람이 누구인지 LogIn
//         위에   private UserDTO logIn;
//        public void setLogIn(UserDTO logIn2) {
//            this.logIn = null;
//            
//        }추가 해줘서 이제 앎.
        
        String message;
        
        if(logIn.getRank() == RANK_ADMIN) {
            //로그인한 회원이 관리자이므로
            //관리자용 코드 실행
            message = new String("1.수정 2.삭제 3.뒤로가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            
            if(userChoice == 1) {
                //영화 수정 메소드 실행
                update(id);
            } else if(userChoice == 2) {
                //영화 삭제 메소드 실행
                delete(id);
            } else if(userChoice == 3) {
                // 뒤로가기실행-> 영화목록보기로 돌아감
                // 영화목록보기 메소드 실행
                printList();
            }
        }else {//로그인한 회원이 관리자가 아니므로
            //비관리자용 코드 실행
            
            //전체 평점 출력 -> 메소드가 각각 어떻게 출력되고 그렇게 출력되는 이유는 알필요 없음(객체지향)
            // =>평점뷰어로 돌리기 여기서는 그냥 평점 출력만 볼수 잇도록!
            message = new String("1.전체평점출력 2.평론가평점출력 3.일반회원평점출력 4.평점등록 5.뒤로가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 5);
            
            if(userChoice == 1) {
                ratingViewer.printList(id, CATEGORY_ALL);
                
            } else if(userChoice == 2) {
                ratingViewer.printList(id, CATEGORY_CRITIC);
                                
            } else if(userChoice == 3) {
                ratingViewer.printList(id, CATEGORY_GENERAL);
                
            } else if(userChoice == 4) {
                
                
            } else if(userChoice == 5) {
                // 뒤로가기실행-> 영화목록보기로 돌아감
                // 영화목록보기 메소드 실행
                printList();
            }
            
            
        }
        
    }
    
    
    private void update(int id) {
        MovieDTO m = movieController.selectOne(id);
        
        String message;
        
        message = new String("새로운 제목을 입력해주세요.");
        m.setTitle(ScannerUtil.nextLine(scanner, message));
        
        message = new String("새로운 줄거리를 입력해주세요.");
        m.setSummary(ScannerUtil.nextLine(scanner, message));
        
        message = new String("새로운 등급을 입력해주세요.");
        
        movieController.update(m);
        printOne(id);
    }
    
    private void delete(int id) {
        String message = new String("정말로 삭제하시겠습니까? Y/N");
        String yesNo = ScannerUtil.nextLine(scanner, message);
        
        if(yesNo.equalsIgnoreCase("Y")) {
            movieController.delete(id);
            printList();
        }else {
            printOne(id);
        }
        
    }
    
    private void add() {
        MovieDTO m = new MovieDTO();
        
        String message;
        
        message = new String("제목을 입력해주세요.");
        m.setTitle(ScannerUtil.nextLine(scanner, message));
        
        message = new String("줄거리를 입력해주세요.");
        m.setSummary(ScannerUtil.nextLine(scanner, message));
        
        message = new String("등급을 입력해주세요.");
        
        movieController.insert(m);
    }


}
