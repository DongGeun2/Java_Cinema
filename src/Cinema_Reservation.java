import java.util.Scanner;

public class Cinema_Reservation {
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
        int menu=0;
        while(true){
            try {
                menu =  scanner.nextInt();
                scanner.nextLine();
                if (menu < 1 || menu > 3) {
                    System.out.println("1~3의 번호만 입력해주세요.");
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("번호만 입력해 주세요.");
                scanner.nextLine();
            }
        }
        return menu;

    }
    // 최종 메뉴 함수
    public void menuCase(){
        seatMake();
        while(true) {
            switch (menuChoice()) {
                case 1: {
                     presentSeat();
                    break;
                }
                case 2: {
                    System.out.println(selectSeatCheck());
                    break;
                }
                case 3: {
                    System.out.println(cancleSeatCheck());
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
    //예매변화 확인 함수
    private String selectSeatCheck(){
        System.out.println("예매번호를 입력해주세요.");
        String numEqul=scanner.nextLine();

        for(int i = 0; i < seat2.length; i++) {
            for (int j = 0; j < seat2[i].length; j++) {
                if(numEqul.equals(seat2[i][j])){
                    return  selectSeatResult(i, j);
                }
            }
        }
        //if문에 안걸렸을때 >> 예매 내역이 없다.
        return "예매된 내역이 없습니다...ㅜㅜ\n번호를 다시한번 확인해주세요!!\n\n";
    }

    //예매조회 출력 함수
    private String selectSeatResult(int row, int col) {
        return "고객님이 예매하신 좌석은 "+(row+1)+"-"+(col+1)+"입니다.\n\n";
    }

    //메뉴 3번
    //좌석예매 취소 확인함수 >> 사용자가 입력한 예매번호가 존재하면 인덱스를 넘겨줌
    private String cancleSeatCheck(){
        String numEqul ="";

        System.out.println("예매번호를 입력해주세요.");

        numEqul = scanner.nextLine();


        for(int i = 0; i < seat2.length; i++) {
            for (int j = 0; j < seat2[i].length; j++) {
                if(numEqul.equals(seat2[i][j])){
                    return cancleSeatResult(i, j);
                }
            }
        }
        //if문에 안걸렸을때 >> 예매 내역이 없다.
        return "예매된 내역이 없습니다...ㅜㅜ\n다시한번 확인해주세요!!\n\n";
    }

    // 좌석예매 취소 출력함수 >> row(행) col(열)을 파라미터로 받아와 처리
    private String cancleSeatResult(int row, int col){
        System.out.println("고객님이 예매하신 좌석은 "+(row+1)+"-"+(col+1)+"입니다.");
        System.out.println();
        System.out.println("예매를 취소하시겠습니까??");
        System.out.println("네(1), 아니오(2)중 하나를 입력해주세요.");

        int choice = scanner.nextInt();


        if(choice == 1) {
            seat2[row][col] = "null";
            seat[row][col] = "0";
            System.out.println("예매가 취소되었습니다. 감사합니다.\n");
        }else if(choice == 2) {
            System.out.println("취소 하였습니다.\n\n");
            return "";
        }
        return "";
    }
}