package com.project.matchingapp3.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class User {
    private int id;
    private String loginid;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String position;
    private String location;
    private String image;
    private String role; // 권한
    private Timestamp joindate;
    private Team teams;
    //private List<Party> partys;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeams() {
        return teams;
    }

    public void setTeams(Team teams) {
        this.teams = teams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getJoindate() {
        return joindate;
    }

    public void setJoindate(Timestamp joindate) {
        this.joindate = joindate;
    }

    public String getUrlImage(){
        return "http://172.30.1.58:8000/image/"+image;  //집
        //return "http://10.100.102.15:8000/image/"+image;//학원
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginid='" + loginid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", location='" + location + '\'' +
                ", image='" + image + '\'' +
                ", role='" + role + '\'' +
                ", joindate=" + joindate +
                ", teams=" + teams +
                '}';
    }

    //joindate 출력양식
    public String getDate() {
        Timestamp time = this.getJoindate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time);
    }
}
