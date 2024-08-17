import com.romanceResearcher.domain.User;
import com.romanceResearcher.service.UserService;

import java.sql.Date;
import java.util.Scanner;

public class Application {

    private static final Scanner sc = new Scanner(System.in);
    private static final UserService userService = UserService.getInstance();


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
                System.out.println("회원가입 정보를 입력해주세요.");
                System.out.print("이름 : ");
                String name = sc.nextLine();
                System.out.print("생년월일");
                String birth = sc.nextLine();
                String id = "";
                do {
                    System.out.print("ID : ");
                    id = sc.nextLine();
                } while(userService.isDuplicatedID(id));
                System.out.print("pw : ");
                String pw = sc.nextLine();
                System.out.print("email :  ");
                String email = sc.nextLine();
                System.out.print("휴대번호 (형식 : 000-0000-0000) : ");
                String phone = sc.nextLine();
                System.out.print("성별 (M : 남성, W : 여자) : ");
                char gender = sc.next().charAt(0);

                // 사진 추가는 회원가입을 하고 나서 회원 정보 수정에서 이루어지도록 하기

                // user 인스턴스에 정보 넣기
                User user = new User(name, birth, id, pw, email, phone, gender);

                // 만든 회원으로 회원가입 시키기
                userService.addUser(user);

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
