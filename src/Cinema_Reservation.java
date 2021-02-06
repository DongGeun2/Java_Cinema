import java.util.Scanner;

public class Cinema_Reservation {

    private final int CANCLE = 3;           //취소 메뉴 선택시 상수
    private final int SELECT = 2;           //조회 메뉴 선택시 상수
    private final int ROW = 4; // (행)
    private final int COL = 5; // (열)
    private String[][] seat; // 좌석 번호 배열
    private String userChoice;
    private String[][] seat2; // 예매 번호 배열

    private Scanner scanner;
    
    Cinema_Reservation(){
        seat = new String[ROW][COL];
        seat2 = new String[ROW][COL];
        scanner = new Scanner(System.in);
        userChoice = new String();
    }
    
    
    
    // 배열 자리 만들기 >> 빈 자리면 각 배열에 0이라고 넣어줌.
    private void seatMake() {
        for(int i = 0; i < seat.length; i++) {
            for(int j = 0; j < seat[i].length; j++) {
                this.seat[i][j] = "0";
            }
        }
    }

    private int menuChoice(){
        System.out.println("*********************************");
        System.out.println("**********영화예매 시스템**********");
        System.out.println("*********************************");

        System.out.println(" 1. 예매하기");
        System.out.println(" 2. 예매조회");
        System.out.println(" 3. 예매취소");
        System.out.println();


        int menu=0;     //메뉴 선택 변수

        while(true){
            try {       // 정수가 아닌 다른 값이 들어왔을때 처리하기 위한 try/catch
                menu =  scanner.nextInt();
                scanner.nextLine();
                if (menu < 1 || menu > 3) {     //만약 메뉴 번호에서 벗어났을때 처리
                    System.out.println("1~3의 번호만 입력해주세요.");
                }
                else{                           //올바른 값을 입력하면 빠져나간다.
                    break;
                }
            }
            catch(Exception e){
                System.out.println("번호만 입력해 주세요.");
                scanner.nextLine();   //  =>  nextLine()이 없으면 왜인지 모르겠으나 무한루프에 걸림.
            }
        }
        return menu;            //선택한 메뉴 번호를 리턴

    }
    // 최종 메뉴 함수
    public void menuCase(){
        seatMake();    //  =>   배열 자리 만들기
        while(true) {
            switch (menuChoice()) {
                case 1: {

                    //메뉴 1번 좌석 예매
                    presentSeat();
                    break;
                }
                case 2: {
                    //메뉴 2번 좌석 예매 '조회'
                    System.out.println(SeatCheck(SELECT));

                    break;
                }
                case 3: {

                    //메뉴 3번 좌석 예매 '취소'
                    System.out.println(SeatCheck(CANCLE));

                }
            }
        }
    }
    
    // 배열의 번호가 0이면 빈자리 1이면 예약석.
    private void seatGetNumber() {
        for(int i = 0; i < seat.length; i++) {
            for(int j = 0; j< seat[i].length; j++) {
                System.out.printf("[%s]",this.seat[i][j].equals("0")?(i+1) + "-" + (j+1):"예매");
            }
            System.out.println();
        }
        
    }
    

    
    // 예약 번호 들어오면 seat 배열 값 1로 바꿔주고 seat2 배열 값에 예약 번호 넣어줌.
    private void userChoiceGetSeatNumber() {
        for(int i = 0; i < seat.length; i++) {
            for(int j = 0; j < seat[i].length; j++) {
                String numEqul = (i+1) + "-" + (j+1); 
                if(seat[i][j].equals("1") && numEqul.equals(userChoice)) {
                    System.out.println("이미 예매 되었습니다.\n\n");
                    break;
                }else if (seat[i][j].equals("0") && numEqul.equals(userChoice)){
                    System.out.println("예매 가능합니다. 예매하시겠습니까?");
                    System.out.println("네(1), 아니요(2), 초기화면(0)중 하나를 입력해주세요.");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // 텍스트 " " 오류 해결
                    if(choice == 1) {
                        this.seat[i][j] = numEqul.equals(this.userChoice)?"1":"0";
                        this.seat2[i][j] = numEqul.equals(this.userChoice)?""+(int)(Math.random()*99999999):"null";
                        System.out.println("예매가 완료 되었습니다.");
                        System.out.printf("예매한 좌석번호:[%s-%s]/ 예매번호:[%s]\n",i+1,j+1,this.seat2[i][j]);
                        System.out.println();
                        return;
                    }else if(choice == 2) {
                        System.out.println("취소 하였습니다. 다시 예약하세요.\n\n");
                        presentSeat();
                    }else if(choice == 0) {
                        break;
                    }
               }
            }
        }
    }
    
    // 메뉴 1번
    private void presentSeat() {
        while(true) {
            System.out.println("*******좌석 현황*******");
            seatGetNumber();
            System.out.println("---------------------");
            System.out.println("좌석을 선택해주세요. 예)1-1");
            System.out.println("이미 예약된 좌석은 \"예매\"라고 표시됩니다.");
            userChoice = scanner.nextLine();
            while(!userChoice.contains("-")) {
                System.out.println("좌석 번호를 다시 입력해주세요");
                userChoice = scanner.nextLine();
            }
            userChoiceGetSeatNumber();
            break;
        }
    }

    //메뉴 2번

    //예매번호 존재 확인 함수
    private String SeatCheck(int select_cancle){        //Select 인지 Cancle인지 받아온다.

        System.out.println("예매번호를 입력해주세요.");
        String numEqul=scanner.nextLine();              //예매번호 입력

        //예매번호를 저장하는 배열에서 찾는다.
        for(int i = 0; i < seat2.length; i++) {
            for (int j = 0; j < seat2[i].length; j++) {
                if(numEqul.equals(seat2[i][j])){        //예매번호 배열에 사용자가 입력한 값이 존재하면
                    if(select_cancle==2){               //조회메뉴(2)를 눌렀을때
                        return "고객님이 예매하신 좌석은 "+(i+1)+"-"+(j+1)+"입니다.\n\n";
                    }
                    else{                               //취소메뉴(3)를 눌렀을때
                        return cancleSeatResult(i, j);  //해당 인덱스 파라미터로 넣고 취소해주는 함수 호출
                    }
                }
            }
        }
        //if문에 안걸렸을때 >> 예매 내역이 없다.
        return "예매된 내역이 없습니다...ㅜㅜ\n번호를 다시한번 확인해주세요!!\n\n";
    }

    // 좌석예매 취소 출력함수 >> row(행) col(열)을 파라미터로 받아와 처리
    private String cancleSeatResult(int row, int col){
        System.out.println("고객님이 예매하신 좌석은 "+(row+1)+"-"+(col+1)+"입니다.");
        System.out.println();
        System.out.println("예매를 취소하시겠습니까??");
        System.out.println("네(1), 아니오(2)중 하나를 입력해주세요.");

        int choice = scanner.nextInt();     //메뉴 선택 변수

        if(choice == 1) {                   //취소 할때
            seat2[row][col] = "null";       //예매번호가 있는 배열에 다시 null을 넣어준다.
            seat[row][col] = "0";           //좌석을 파악하는 배열에 다시 0을 넣어준다.
            System.out.println("예매가 취소되었습니다. 감사합니다.\n");
        }else if(choice == 2) {             //취소를 안할때
            System.out.println("취소를 하지 않겠습니다.\n\n");
            return "";           //함수 끝
        }
        return "";              //함수 끝
    }
}