package util;
/*Scanner 클래스 변수로
 * 키보드 입력을 받을 때
 * 도움이 될 메소드를 모아둔 클래스
 */

import java.util.Scanner;

public class ScannerUtil {
    
    //1. 입력시 입력 안내 문구 출력을 담당할
    //   printMessage()
    private static void printMessage(String message) {
        System.out.println(message);
        System.out.println("> ");
    }
    
    //2. int 입력을 담당할 nextInt()
    public static int nextInt(Scanner scanner, String message) {
        printMessage(message);
        return scanner.nextInt();
    }
    
    //3. 특정 범위의 int입력을 담당할 nextInt()
    public static int nextInt(Scanner scanner, String message, int min, int max) {
        int num = nextInt(scanner, message);
        
        while( !(num>=min && num<=max)) {
            System.out.println("======================");
            System.out.println("잘못 입력하셨습니다.");
            System.out.println("======================");
            num = nextInt(scanner, message);
        }
        
        return num;
    }
    
    
    //4. 스캐너 버퍼메모리 버그를 내부적으로 해결하는
    //   nextLine()
    public static String nextLine(Scanner scanner, String message) {
        printMessage(message);
        String temp = scanner.nextLine();
        
        //String 클래스 변수의 isEmpty() 메소드는
        //해당 변수의 저장된 값이 아무것도 없으면 true,
        //값이 있으면 false를 리턴한다.
        if(temp.isEmpty()) { //temp에 공백,스페이스 등 아무거나 라도 있으면 true, 찐null이면 false를 리턴
            temp = scanner.nextLine();
        }
        
        return temp;
    
    }

}
