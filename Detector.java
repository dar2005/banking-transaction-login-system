package banking;

import java.util.List;

public interface Detector {
    boolean isSuspicious(Log log, List<Log> accountLogs);
}
