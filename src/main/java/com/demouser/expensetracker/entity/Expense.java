package com.demouser.expensetracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id")
    private Long expenseId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "exp_date", nullable = false)
    private LocalDate expenseDate;

    @Column(name = "exp_creation", updatable = false, nullable = false)
    private Instant createdAt;

    @Column(name = "update_time", nullable = false)
    private Instant updatedAt;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Expense() {}

    public Expense(
        String title,
        String description,
        LocalDate expenseDate,
        BigDecimal amount,
        User user,
        Category category
    ) {
        this.title = title;
        this.description = description;
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.user = user;
        this.category = category;
    }

    @PrePersist
    public void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = Instant.now();
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;

        Expense other = (Expense) o;

        return expenseId != null && expenseId.equals(other.expenseId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(expenseId);
    }

    @Override
    public String toString() {
        return (
            " Expense Id" +
            expenseId +
            " title " +
            title +
            " description " +
            description +
            " Expense Date " +
            expenseDate +
            " Created At " +
            createdAt +
            " Updated At " +
            updatedAt +
            " Amount " +
            amount +
            " Category " +
            category
        );
    }
}
