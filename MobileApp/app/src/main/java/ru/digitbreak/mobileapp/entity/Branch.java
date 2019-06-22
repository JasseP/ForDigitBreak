package ru.digitbreak.mobileapp.entity;

public class Branch {
    private long id;
    private String description;
    private boolean isActive;
    private String uid;
    public Branch(long id, String description, boolean isActive, String uid) {
        this.id = id;
        this.description = description;
        this.isActive = isActive;
        this.uid = uid;
    }
    public Branch() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean getIsActive() {
        return this.isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
}
