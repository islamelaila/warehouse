package com.item.model;

import java.sql.Date;

public class ItemDetails {

	private Long detail_Id;
    private Date issueDate;
    private Date expireDate;
    private String description;
    private Long id; 
    
    public ItemDetails() {}

    public ItemDetails(Long detailId, Date issueDate, Date expireDate, String description, Long id) {
        this.detail_Id = detailId;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
        this.description = description;
        this.id = id;
    }
    
    public ItemDetails( Date issueDate, Date expireDate, String description) {
        this.issueDate = issueDate;
        this.expireDate = expireDate;
        this.description = description;
    }
    
    
    public ItemDetails(Long id, Date issueDate, Date expireDate, String description) {
        this.id = id;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
        this.description = description;
    }

   

    public Long getDetailId() {
        return detail_Id;
    }

    public void setDetailId(Long detailId) {
        this.detail_Id = detailId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
