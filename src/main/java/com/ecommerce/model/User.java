package com.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    private Boolean newsletter;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus userStatus) {
        this.status = userStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User() {
        this.status = UserStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", newsletter=" + newsletter +
                '}';
    }

    public enum UserStatus{
        ACTIVE("Ativo"),
        INACTIVE("Inativo"),
        SUSPENDED("Suspenso"),
        BLOCKED("Bloqueado");

        private final String description;


        UserStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public UserStatus active(){
        this.setStatus(UserStatus.ACTIVE);
        return this.getStatus();
    }

    public UserStatus inactive(){
        this.setStatus(UserStatus.INACTIVE);
        return this.getStatus();
    }
    public UserStatus suspended(){
        this.setStatus(UserStatus.SUSPENDED);
        return this.getStatus();
    }

    public UserStatus blocked(){
        this.setStatus(UserStatus.BLOCKED);
        return this.getStatus();
    }



}
