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
        seat = new String[COL][ROW];
        seat2 = new String[COL][ROW];
        scanner = new Scanner(System.in);
        userChoice = new String();
    }
    
    // 배열 자리 만들기 >> 빈자리면 각 배열에 0이라고 넣어줌.
    private void seatMake() {
        for(int i = 1; i < seat.length; i++) {
            for(int j = 0; j < seat[i].length; j++) {
                this.seat[i][j] = "0";
            }
        }
    }
    
    
    // 예약 번호 들어오면 seat 배열 값 1로 바꿔주고 seat2 배열 값에 예약 번호 넣어줌.
    private void userChoiceGetSeatNumber() {
        for(int i = 1; i < seat.length; i++) {
            for(int j = 0; j < seat[i].length; j++) {

                if(seat[i][j].equals("1")) {
                    System.out.println("이미 예매 되었습니다.");
                }else if (seat[i][j].equals("0")){
                    System.out.println("예매 가능합니다. 예매하시겠습니까?");
                    System.out.println("네(1), 아니요(2), 초기화면(0)중 하나를 입력해주세요.");
                    int choice = scanner.nextInt();
                    if(choice == 1) {
                        String numEqul = i + "-" + j;
                        this.seat[i][j] = numEqul.equals(this.userChoice)?"1":"0";
                        this.seat2[i][j] = numEqul.equals(this.userChoice)?""+(int)(Math.random()*99999999):"null";
                        return;
                    }else if(choice == 2) {
                        System.out.println("취소 하였습니다. 다시 예약하세요.");
                        break;
                    }else if(choice == 0) {
                        break;
                    }
                    
                }
            }
        }
    }
    
    
    // 배열의 번호가 0이면 빈자리 1이면 예약석.
    private void seatGetNumber() {
        for(int i = 1; i < seat.length; i++) {
            for(int j = 0; j< seat[i].length; j++) {
                System.out.printf("[%s]",this.seat[i][j].equals("0")?(i) + "-" + (j):"예매");
            }
            System.out.println();
        }
        
    }
    
    // 메뉴 1번
    public void presentSeat() {
        seatMake();
        while(true) {
            System.out.println("*******좌석 현황*******");
            seatGetNumber();
            System.out.println("---------------------");
            System.out.println("좌석을 선택해주세요. 예)1-1");
            System.out.println("이미 예약된 좌석은 \"예매\"라고 표시됩니다.");
            userChoice = scanner.nextLine();
            userChoiceGetSeatNumber();
        }

    }
    
    
    
    
    public void PrintTest() {
        seatMake();
        userChoiceGetSeatNumber();
        seatGetNumber();
        
        for(int i = 1; i < seat2.length; i++) {
            for(int j = 0; j< seat2[i].length; j++) {
                System.out.print(seat2[i][j]);
            }
            System.out.println();
        }
    }
    
    
}
