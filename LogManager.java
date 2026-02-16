

import java.util.*;

public class LogManager {

    private int nextId = 1;

    private final List<Log> allLogs = new ArrayList<>();
    private final Map<String, List<Log>> accountMap = new HashMap<>();
    private final Map<ActionType, List<Log>> actionMap = new HashMap<>();
    private final Deque<Log> recentLogs = new ArrayDeque<>();
    private final Detector detector;

    public LogManager(Detector detector) {
        this.detector = detector;
        for (ActionType type : ActionType.values()) {
            actionMap.put(type, new ArrayList<>());
        }
    }

    public Log addLog(String accNo, ActionType action, double amount, Status status) {

        Log log = new Log(nextId++, accNo, action, amount, status);

        allLogs.add(log);
        recentLogs.push(log);

        accountMap.computeIfAbsent(accNo, k -> new ArrayList<>()).add(log);
        actionMap.get(action).add(log);

        return log;
    }

    public List<Log> getLogsByAccount(String accNo) {
        return accountMap.getOrDefault(accNo, Collections.emptyList());
    }

    public List<Log> getRecentLogs(int n) {
        List<Log> result = new ArrayList<>();
        Iterator<Log> iterator = recentLogs.iterator();

        while (iterator.hasNext() && result.size() < n) {
            result.add(iterator.next());
        }

        return result;
    }

    public List<Log> searchByAction(ActionType action) {
        return actionMap.getOrDefault(action, Collections.emptyList());
    }

    public List<Log> detectSuspicious() {
        List<Log> suspiciousLogs = new ArrayList<>();

        for (Log log : allLogs) {
            List<Log> accLogs = accountMap.get(log.getAccNo());
            if (detector.isSuspicious(log, accLogs)) {
                suspiciousLogs.add(log);
            }
        }

        return suspiciousLogs;
    }
}
