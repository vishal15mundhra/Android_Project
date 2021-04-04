package com.example.internshipproject;

public class Subjects {
    String name;
    String modules;
    String credit;
    String code;
    String type;
    String priority;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Subjects() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }


    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "name='" + name + '\'' +
                ", modules='" + modules + '\'' +
                ", credit='" + credit + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
