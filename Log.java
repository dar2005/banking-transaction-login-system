

import java.util.Date;

public class Log {

    private final int id;
    private final String accNo;
    private final ActionType action;
    private final double amount;
    private final long time;
    private final Status status;

    public Log(int id, String accNo, ActionType action, double amount, Status status) {
        this.id = id;
        this.accNo = accNo;
        this.action = action;
        this.amount = amount;
        this.status = status;
        this.time = System.currentTimeMillis();
    }

    public int getId() { return id; }
    public String getAccNo() { return accNo; }
    public ActionType getAction() { return action; }
    public double getAmount() { return amount; }
    public long getTime() { return time; }
    public Status getStatus() { return status; }

    @Override
    public String toString() {
        return "ID=" + id +
                ", Account=" + accNo +
                ", Action=" + action +
                ", Amount=" + amount +
                ", Status=" + status +
                ", Time=" + new Date(time);
    }
}
