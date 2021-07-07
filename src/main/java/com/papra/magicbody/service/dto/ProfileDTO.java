package com.papra.magicbody.service.dto;

import com.papra.magicbody.domain.City;
import com.papra.magicbody.domain.Gender;

public class ProfileDTO {

    private Long accountDaysLeft = 10l;

    private Long accountDays = 30l;

    private Long startFrom;

    private Gender gender = Gender.MALE;

    private String phoneNumber = "09014350543";

    private City city = City.TEHRAN;

    private Long dateOfBirth;

    private int height;

    private String sportExperience;

    private String firstName;

    private String lastName;

    private String email;

    private String imageUrl;

    public Long getAccountDaysLeft() {
        return accountDaysLeft;
    }

    public ProfileDTO setAccountDaysLeft(Long accountDaysLeft) {
        this.accountDaysLeft = accountDaysLeft;
        return this;
    }

    public Long getAccountDays() {
        return accountDays;
    }

    public ProfileDTO setAccountDays(Long accountDays) {
        this.accountDays = accountDays;
        return this;
    }

    public Long getStartFrom() {
        return startFrom;
    }

    public ProfileDTO setStartFrom(Long startFrom) {
        this.startFrom = startFrom;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public ProfileDTO setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public City getCity() {
        return city;
    }

    public ProfileDTO setCity(City city) {
        this.city = city;
        return this;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public ProfileDTO setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public ProfileDTO setHeight(int height) {
        this.height = height;
        return this;
    }

    public String getSportExperience() {
        return sportExperience;
    }

    public ProfileDTO setSportExperience(String sportExperience) {
        this.sportExperience = sportExperience;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProfileDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
