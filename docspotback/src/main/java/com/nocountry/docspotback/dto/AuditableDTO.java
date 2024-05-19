package com.nocountry.docspotback.dto;

public abstract class AuditableDTO {
    private String createdAt;
    private String updateAt;

    // Getters y setters
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}