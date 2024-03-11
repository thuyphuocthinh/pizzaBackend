package com.website.orderPizza.entity;

import jakarta.persistence.*;
import org.hibernate.mapping.Join;

@Entity(name = "bills")
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "created_date")
    private String createdDate;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
