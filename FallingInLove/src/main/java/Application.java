import com.romanceResearcher.service.UserService;

import java.util.Scanner;

public class Application {

    private static final Scanner sc = new Scanner(System.in);
    private static final UserService userService = new UserService();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int action = 0;

        // 첫 로그인 또는 회원가입 선택 화면
        System.out.println("=====저희 만년설에 오신걸 환영합니다=====");
        while (true) {
            System.out.print("로그인 또는 회원 가입을 해주세요(로그인 : 1, 회원 가입 : 2, 프로그램 종료 : 3) : " );
            action = chooseNo();
            if (action == 1) {
                // 로그인 화면 이동
                System.out.print("ID : ");
                String userId = sc.nextLine();
                System.out.print("PW : ");
                String password = sc.nextLine();

                userService.loginUser(userId, password);

            } else if (action == 2) {
                // 회원 가입 화면 이동


            } else if (action == 3){
                // 프로그램 종료
                System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
                return;
            } else {
                // 다시 입력하게 하기
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }

        }

    }

    private static int chooseNo() {
        return sc.nextInt();
    }

}
