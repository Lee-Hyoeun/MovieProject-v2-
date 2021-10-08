package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieController;
import model.MovieDTO;
import util.ScannerUtil;

public class MovieViewer {
    private MovieController movieController; // 따로 만들어서 주입하기보다 아래에 그냥 생성자 만들기
    private Scanner scanner;

    public MovieViewer() {
        movieController = new MovieController();
    }

    // 스캐너를 외부에서 받아서 처리할 수 있게 하는 세터메소드
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
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
        // 일단 로그인한 사람이 
    }

}
