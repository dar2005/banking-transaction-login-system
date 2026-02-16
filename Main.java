package banking;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LogManager manager = new LogManager(new BasicDetector());

        while (true) {

            System.out.println("\n1. Add Log");
            System.out.println("2. Get Logs by Account");
            System.out.println("3. Get Recent Logs");
            System.out.println("4. Detect Suspicious Activity");
            System.out.println("5. Search by Action Type");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNo = sc.next();

                    System.out.print("Enter Action (DEPOSIT/WITHDRAW/TRANSFER/LOGIN/FAILED_LOGIN): ");
                    ActionType action = ActionType.valueOf(sc.next().toUpperCase());

                    double amount = 0;
                    if (action == ActionType.DEPOSIT ||
                        action == ActionType.WITHDRAW ||
                        action == ActionType.TRANSFER) {

                        System.out.print("Enter Amount: ");
                        amount = sc.nextDouble();
                    }

                    System.out.print("Enter Status (SUCCESS/FAILED): ");
                    Status status = Status.valueOf(sc.next().toUpperCase());

                    Log log = manager.addLog(accNo, action, amount, status);
                    System.out.println("Log Added: " + log);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.next();
                    manager.getLogsByAccount(accNo).forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter N: ");
                    int n = sc.nextInt();
                    manager.getRecentLogs(n).forEach(System.out::println);
                    break;

                case 4:
                    List<Log> suspicious = manager.detectSuspicious();
                    if (suspicious.isEmpty())
                        System.out.println("No suspicious logs found.");
                    else
                        suspicious.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Enter Action Type: ");
                    action = ActionType.valueOf(sc.next().toUpperCase());
                    manager.searchByAction(action).forEach(System.out::println);
                    break;

                case 6:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
