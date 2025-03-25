import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String name;
    double price;
    int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}

public class Welcome_second {
    private static ArrayList<Item> shoppingCart = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String greeting = "Welcome to Shopping Moll";
        String tagline = "Welcome to Book Market";

        // User Information
        System.out.println("당신의 이름을 입력하세요: ");
        String userName = input.nextLine(); // Use nextLine to allow spaces in the name
        System.out.println("연락처를 입력하세요: ");
        String userMobile = input.nextLine(); // Use nextLine for phone number (to avoid input issues)

        // Welcome message
        System.out.println("**************************************************");
        System.out.println("\t" + greeting);
        System.out.println("\t" + tagline);
        System.out.println("**************************************************");

        // Menu
        int choice;
        while (true) {
            System.out.println("\n1. 고객 정보 확인하기 \t4. 바구니에 항목 추가하기");
            System.out.println("2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
            System.out.println("3. 장바구니 비우기 \t6. 장바구니 항목 삭제하기");
            System.out.println("7. 영수증 표시하기 \t8. 종료");
            System.out.println("**************************************************");

            try {
                System.out.println("메뉴 번호를 선택하시오: ");
                choice = Integer.parseInt(input.nextLine()); // Using nextLine() and parsing the input
                System.out.println(choice + "번을 선택했습니다");

                switch (choice) {
                    case 1:
                        showCustomerInfo(userName, userMobile);
                        break;
                    case 2:
                        viewShoppingCart();
                        break;
                    case 3:
                        clearCart();
                        break;
                    case 4:
                        addItemToCart();
                        break;
                    case 5:
                        reduceItemQuantity();
                        break;
                    case 6:
                        removeItemFromCart();
                        break;
                    case 7:
                        generateReceipt();
                        break;
                    case 8:
                        System.out.println("프로그램을 종료합니다.");
                        return; // Exit the program
                    default:
                        System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("유효한 숫자를 입력해 주세요.");
            }
        }
    }

    // Method to show customer information
    private static void showCustomerInfo(String name, String mobile) {
        System.out.println("고객 이름: " + name);
        System.out.println("연락처: " + mobile);
    }

    // Method to view the shopping cart
    private static void viewShoppingCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
        } else {
            System.out.println("장바구니 목록:");
            for (Item item : shoppingCart) {
                System.out.println(item.name + " - 가격: " + item.price + " 원, 수량: " + item.quantity);
            }
        }
    }

    // Method to clear the shopping cart
    private static void clearCart() {
        shoppingCart.clear();
        System.out.println("장바구니가 비었습니다.");
    }

    // Method to add an item to the cart
    private static void addItemToCart() {
        System.out.println("추가할 상품 이름을 입력하세요: ");
        String itemName = input.nextLine();
        System.out.println("상품 가격을 입력하세요: ");
        double itemPrice = input.nextDouble();
        System.out.println("상품 수량을 입력하세요: ");
        int itemQuantity = input.nextInt();
        input.nextLine(); // Consume the remaining newline character

        shoppingCart.add(new Item(itemName, itemPrice, itemQuantity));
        System.out.println(itemName + "이(가) 장바구니에 추가되었습니다.");
    }

    // Method to reduce the quantity of an item
    private static void reduceItemQuantity() {
        System.out.println("수량을 줄일 상품의 이름을 입력하세요: ");
        String itemName = input.nextLine();
        for (Item item : shoppingCart) {
            if (item.name.equals(itemName)) {
                System.out.println("현재 수량: " + item.quantity);
                System.out.println("몇 개를 줄일까요?");
                int reduceBy = input.nextInt();
                input.nextLine(); // Consume the newline character
                if (reduceBy < item.quantity) {
                    item.quantity -= reduceBy;
                    System.out.println(itemName + "의 수량이 " + reduceBy + "만큼 줄어들었습니다.");
                } else {
                    System.out.println("수량을 줄일 수 없습니다. 현재 수량보다 많습니다.");
                }
                return;
            }
        }
        System.out.println("상품이 장바구니에 없습니다.");
    }

    // Method to remove an item from the cart
    private static void removeItemFromCart() {
        System.out.println("삭제할 상품의 이름을 입력하세요: ");
        String itemName = input.nextLine();
        shoppingCart.removeIf(item -> item.name.equals(itemName));
        System.out.println(itemName + "이(가) 장바구니에서 삭제되었습니다.");
    }

    // Method to generate and display a receipt
    private static void generateReceipt() {
        if (shoppingCart.isEmpty()) {
            System.out.println("장바구니에 항목이 없습니다.");
            return;
        }

        double total = 0;
        System.out.println("********** 영수증 **********");
        for (Item item : shoppingCart) {
            double itemTotal = item.getTotalPrice();
            total += itemTotal;
            System.out.println(item.name + " - 가격: " + item.price + " 원, 수량: " + item.quantity + ", 총액: " + itemTotal + " 원");
        }
        System.out.println("****************************");
        System.out.println("총합: " + total + " 원");
        System.out.println("****************************");
    }
}
