package ir.mctab.java32.projects.scholarshipmanagement.model;

import java.sql.Date;

public class Log {
    int id;
    String action;
    Date date;
    Long action_by;
    Long action_for;
    String desc;

    public Log(int id, String action, Date date, Long action_by, Long action_for,String desc) {
        this.id = id;
        this.action = action;
        this.date = date;
        this.action_by = action_by;
        this.action_for=action_for;
        this.desc = desc;
    }

    public Log(String action, Date date, Long action_by, Long action_for,String desc) {
        this.action = action;
        this.date = date;
        this.action_by = action_by;
        this.action_for = action_for;
        this.desc = desc;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAction_by() {
        return action_by;
    }

    public void setAction_by(Long action_by) {
        this.action_by = action_by;
    }

    public Long getAction_for() {
        return action_for;
    }

    public void setAction_for(Long action_for) {
        this.action_for = action_for;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", action_by=" + action_by +
                ", action_for=" + action_for +
                ", desc='" + desc + '\'' +
                '}';
    }
}
