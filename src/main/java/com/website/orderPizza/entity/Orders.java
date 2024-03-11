package com.website.orderPizza.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "shipped_date")
    private Date shippedDate;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "orders")
    Set<Bills> billsSet;

    public Set<Bills> getBillsSet() {
        return billsSet;
    }

    public void setBillsSet(Set<Bills> billsSet) {
        this.billsSet = billsSet;
    }

    public int getId() {
        return id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
