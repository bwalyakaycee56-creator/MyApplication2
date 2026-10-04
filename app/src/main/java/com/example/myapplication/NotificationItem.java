package com.example.myapplication;

public class NotificationItem {

    public enum Type {
        REGISTRATION, GROUP_MEETING, SYSTEM, GROUP_CHANGE, ANNOUNCEMENT, GROUP_FULL
    }

    private final Type type;
    private final String title;
    private final String subtitle;
    private final String time;
    private final boolean unread;
    private final boolean important;

    public NotificationItem(Type type, String title, String subtitle, String time,
                            boolean unread, boolean important) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
        this.time = time;
        this.unread = unread;
        this.important = important;
    }

    public Type getType() { return type; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getTime() { return time; }
    public boolean isUnread() { return unread; }
    public boolean isImportant() { return important; }
}