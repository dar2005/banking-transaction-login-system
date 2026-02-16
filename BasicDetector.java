package banking;

import java.util.List;

public class BasicDetector implements Detector {

    @Override
    public boolean isSuspicious(Log log, List<Log> accountLogs) {

        if (log.getAction() == ActionType.WITHDRAW && log.getAmount() > 50000) {
            return true;
        }

        if (log.getAction() == ActionType.FAILED_LOGIN) {
            int count = 0;
            int size = accountLogs.size();

            for (int i = Math.max(0, size - 5); i < size; i++) {
                if (accountLogs.get(i).getAction() == ActionType.FAILED_LOGIN) {
                    count++;
                }
            }

            return count > 3;
        }

        return false;
    }
}
