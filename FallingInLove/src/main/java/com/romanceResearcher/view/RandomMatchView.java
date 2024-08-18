package com.romanceResearcher.view;

import com.romanceResearcher.domain.FirstMatch;
import com.romanceResearcher.domain.SecondMatch;
import com.romanceResearcher.domain.User;
import com.romanceResearcher.service.MatchService;
import com.romanceResearcher.service.SecondMatchService;
import com.romanceResearcher.service.UserService;

import java.util.List;
import java.util.Scanner;

// 랜덤 매칭 화면
public class RandomMatchView {

    private final User user; // 개인 유저
    private final MatchService matchService; // 개인 유저에 대한 Match 서비스
    private final UserService userService;
    private final SecondMatchService secondMatchService;
    private final Scanner sc = new Scanner(System.in);

    public RandomMatchView(User user) {
        this.user = user;
        this.matchService = MatchService.getInstance();
        this.userService = UserService.getInstance();
        this.secondMatchService = SecondMatchService.getInstance();
    }


    // 소개팅 UI
    public void datingUI() {
        // 1. 랜덤 소개팅 하기
        // 2. 사용자에게 호감 보낸 상대 프로필 조회
        // 3. 사용자가 호감 보낸 상대 프로필 조회
        while (true) {
            System.out.println("===== 소개팅 화면입니다. =====");
            System.out.println("1 : 랜덤 소개팅 하기");
            System.out.println("2 : 본인에게 호감 보낸 상대 프로필 조회하기"); // 본인에게 호감 보낸 상대 프로필 조회하기
            System.out.println("3 : 본인이 호감 보낸 상대 프로필 조회하기"); // 본인이 호감 보낸 상대 프로필 조회
            System.out.println("4 : 서로 호감있는 소개팅 조회하기");
            System.out.println("5 : 이전 화면으로 돌아가기");
            System.out.print("메뉴 선택 : ");

            try {
                int action = sc.nextInt(); // 입력받은 번호를 action 변수에 저장
                switch (action) {
                    case 1: randomDatingView(); break; // 랜덤 매칭 기능 호출
                    case 2: showReceiveSignalfromPartnerView(user); break; // 사용자에게 호감 보낸 상대 프로필 조회
                    case 3: showSendSignaltoPartnerView(user); break; // 사용자가 호감 보낸 상대 프로필 조회
                    case 4: showSecondMatchesView(); break; // 쌍방 호감 기록 조회
                    case 5: return; // 메소드 종료
                    default :
                        System.out.println("번호를 잘 못 입력하였습니다.");
                }
            } catch (Exception e) {
                System.out.println("메뉴 숫자를 입력해주세요.");
                sc.next(); // 버퍼 내 잘못된 입력값 버리기
            }

        }


    }

    // 랜덤 소개팅 view
    public void randomDatingView() {
        sc.nextLine();
        List<User> randomPartners = userService.findMatchingPartner(user);
        User randomDatingPartner = null;
        while (true) {
            int randomPartnerId = (int) (Math.random() * randomPartners.size());
            randomDatingPartner = randomPartners.get(randomPartnerId);
            System.out.println(randomDatingPartner.showUserInfo());
            System.out.println("1 : 호감 보내기");
            System.out.println("2 : 다음 상대 보기");
            System.out.println("3 : 소개팅 종료하기");

            try {
                int action = sc.nextInt(); // 입력받은 번호를 action 변수에 저장
                switch (action) {
                    case 1: matchService.sendSignal(user, randomDatingPartner); continue; // 호감 보내기 -> 호감 보낸 기록을 생성해서 저장해야됌
                    case 2: continue; // 다음 상대 보기
                    case 3: return; // 메소드 종료
                    default :
                        System.out.println("번호를 잘 못 입력하였습니다.");
                }
            } catch (Exception e) {
                System.out.println("숫자를 입력해주세요.");
                sc.next(); // 버퍼 내 잘못된 입력값 버리기
            }
        }
    }

    // 본인에게 호감 보낸 상대 프로필 조회
    private void showReceiveSignalfromPartnerView(User user) {
        List<FirstMatch> firstMatches = matchService.getReceiveSignalFromPartner(user);
        for (FirstMatch firstMatch : firstMatches) {
            System.out.println(firstMatch);
        }
        if (firstMatches.size() == 0) {
            System.out.println("비어 있습니다.");
            return;
        }

        // 한명씩 보면서 호감 표시하기
        showEachFirstMatchView(firstMatches);
    }

    // 본인이 호감 보낸 상대 프로필 조회
    private void showSendSignaltoPartnerView(User user) {
        List<FirstMatch> firstMatches = matchService.showSendSignaltoPartner(user);
        for (FirstMatch firstMatch : firstMatches) {
            System.out.println(firstMatch);
        }

        if (firstMatches.size() == 0) {
            System.out.println("비어 있습니다.");
        }

    }

    private void showEachFirstMatchView(List<FirstMatch> firstMatches) {
        sc.nextLine();
        while (true) {
            System.out.print("호감 표시를 하시겠어요?(하나씩 보여줍니다.) (YES or NO) : ");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                for (FirstMatch firstMatch : firstMatches) {
                    System.out.println(firstMatch);
                    System.out.println("호감 : 1, 삭제 : 2, 다음 사람 보기 : 3, 뒤로 가기 : 4");
                    int response = sc.nextInt();
                    switch (response) {
                        case 1 : secondMatchService.addSecondMatch(firstMatch); break; // 호감 표시
                        case 2 : matchService.deleteMyFirstMatch(firstMatch); break; // 삭제
                        case 3 : break; // 다음 사람 보기
                        case 4 : return; // 뒤로 가기
                    }
                }
                break;
            } else if (answer.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }

    // 쌍방 호감 기록 조회
    private void showSecondMatchesView() {
        List<SecondMatch> mySecondMatchList = secondMatchService.showMySecondMatches(user);

        if (mySecondMatchList.size() == 0) {
            System.out.println("비어 있습니다.");
        }

        showEachSecondMatchView(mySecondMatchList);

    }

    // SecondMatch 하나씩 보면서 매칭 수락 여부 묻는 view
    private void showEachSecondMatchView(List<SecondMatch> mySecondMatchList) {
        while (true) {
            System.out.print("하니씩 조회하면서 매칭 버튼을 누르시겠어요? (YES or NO) : ");
            String response = sc.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                for (SecondMatch secondMatch : mySecondMatchList) {
                    System.out.println(secondMatch);
                    System.out.println("매칭 수락 : 1, 매칭 삭제 : 2, 다음 매칭 보기 : 3, 뒤로 가기 : 4");
                    int answer = sc.nextInt();

                    switch (answer) {
                        case 1: secondMatchService.acceptedSecondMatch(secondMatch); break;
                        case 2: secondMatchService.deleteSecondMatch(secondMatch); break;
                        case 3: break;
                        case 4: return;
                    }
                }
            } else if (response.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }

        }
    }
}
