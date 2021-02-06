import java.util.Random;
import java.util.Scanner;

public class Cinema_Reservation {
    static final int ROW = 4;
    static final int COL = 5;
    private String[][] seat;
    private String userChoice;
    private String[][] seat2;
    private Scanner scanner;
    
    Cinema_Reservation(){
        seat = new String[ROW][COL];
        seat2 = new String[ROW][COL];
        scanner = new Scanner(System.in);
        userChoice = new String();
    }
    
    // 배열 자리 만들기 >> 빈자리면 각 배열에 0이라고 넣어줌.
    private void seatMake() {
        for(int i = 0; i < seat.length; i++) {
            for(int j = 0; j < seat[i].length; j++) {
                this.seat[i][j] = "0";
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
    
    // 메뉴 1번
    public void presentSeat() {
        
        while(true) {
            System.out.println("*******좌석 현황*******");
            seatGetNumber();
            System.out.println("---------------------");
            System.out.println("좌석을 선택해주세요. 예)1-1");
            System.out.println("이미 예약된 좌석은 \"예매\"라고 표시됩니다.");
            
            userChoice = scanner.nextLine();
            
            userChoiceGetSeatNumber();
            break;
        }

    }
    
    // 예약 번호 들어오면 seat 배열 값 1로 바꿔주고 seat2 배열 값에 예약 번호 넣어줌.
    private void userChoiceGetSeatNumber() {
        for(int i = 0; i < seat.length; i++) {
            for(int j = 0; j < seat[i].length; j++) {
                String numEqul = (i+1) + "-" + (j+1); 
                if(seat[i][j].equals("1")&& numEqul.equals(userChoice) ) {
                    System.out.println("이미 예매 되었습니다.");
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
                        return;
                    }else if(choice == 2) {
                        System.out.println("취소 하였습니다. 다시 예약하세요.");
                        presentSeat();
                    }else if(choice == 0) {
                        return;
                    }
                    
                }
            }
        }
    }
    
    // 메뉴2번
    public void menu2() {
        System.out.println("예매번호를 입력해주세요.");
        String userChoiceTicket = scanner.nextLine();
        
        for(int i = 0; i < seat.length; i++) {
            for(int j = 0; j < seat.length; j++) {
                if(userChoiceTicket.equals(seat2[i][j])) {
                    System.out.printf("고객님이 예매하신 좌석은 %s-%s 입니다.",i+1,j+1);                   
                }else {
                    System.out.println("예매된 좌석이 없습니다. 예매번호를 확인해주세요.");
                    return;
                }

                
            }
        }
    }
    
    
    
    
    public void PrintTest() {
        seatMake();
        while(true) {
                System.out.println("1. 예약 2. 조회 3. 취소");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice == 1) {
                    presentSeat();
                }else if(choice == 2) {
                    selectSeatCheck();
                }else if(choice == 3) {
                    cancleSeatCheck();
                }
            
        }
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
            return "예매가 취소되었습니다. 감사합니다.";
        }else if(choice == 2) {
            return "취소 하였습니다.";
        }
        return "";
    }
    
    
    
    //좌석예매 취소 확인함수 >> 사용자가 입력한 예매번호가 존재하면 인덱스를 넘겨줌
    private String cancleSeatCheck(){
        String numEqul ="";

        System.out.println("예매번호를 입력해주세요.");

        numEqul = scanner.nextLine();
        

        for(int i = 0; i < seat2.length; i++) {
            for (int j = 0; j < seat2[i].length; j++) {
                if(numEqul.equals(seat2[i][j])){
                    cancleSeatResult(i, j);
                    return "";
                }
            }
        }
        //if문에 안걸렸을때 >> 예매 내역이 없다.
        return "예매된 내역이 없습니다...ㅜㅜ\n다시한번 확인해주세요!!\n\n";

    }

    //에매조회 출력 함수
    private void selectSeatResult(int row, int col) {
        System.out.println("고객님이 예매하신 좌석은 "+(row+1)+"-"+(col+1)+"입니다.");
    }

    //예매번화 확인 함수
    private String selectSeatCheck(){

        System.out.println("예매번호를 입력해주세요.");
        String numEqul = scanner.nextLine();
        

        for(int i = 0; i < seat2.length; i++) {
            for (int j = 0; j < seat2[i].length; j++) {
                if(numEqul.equals(seat2[i][j])){
                    selectSeatResult(i, j);
                    return "";
                }
            }
        }
        //if문에 안걸렸을때 >> 예매 내역이 없다.
        return "예매된 내역이 없습니다...ㅜㅜ\n다시한번 확인해주세요!!\n\n";
    }
    
    
}