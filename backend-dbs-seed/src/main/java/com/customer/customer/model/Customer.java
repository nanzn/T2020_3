package com.customer.customer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("lastlogIn")
    private Timestamp lastlogIn;
    @JsonProperty("dateOfBirth")
    private Timestamp dateOfBirth;
    @JsonProperty("riskLevel")
    private String riskLevel;
    private String username;
    private String password;

    public Customer(String customerId, String gender, String firstName, String lastName, Timestamp lastlogIn, Timestamp dateOfBirth, String riskLevel) {
        this.customerId = customerId;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastlogIn = lastlogIn;
        this.dateOfBirth = dateOfBirth;
        this.riskLevel  = riskLevel;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Timestamp getLastlogIn() {
        return lastlogIn;
    }

    public void setLastlogIn(Timestamp lastlogIn) {
        this.lastlogIn = lastlogIn;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
