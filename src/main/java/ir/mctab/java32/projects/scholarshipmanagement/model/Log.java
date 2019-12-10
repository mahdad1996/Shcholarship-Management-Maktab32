package ir.mctab.java32.projects.scholarshipmanagement.model;

public class Log {
    int id;
    String action;
    String date;
    Long action_by;

    public Log(int id, String action, String date, Long action_by) {
        this.id = id;
        this.action = action;
        this.date = date;
        this.action_by = action_by;
    }

    public Log(String action, String date, Long action_by) {
        this.action = action;
        this.date = date;
        this.action_by = action_by;
    }

    public Log() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAction_by() {
        return action_by;
    }

    public void setAction_by(Long action_by) {
        this.action_by = action_by;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", action_by=" + action_by +
                '}';
    }
}
