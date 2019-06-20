package ru.digitbreak.mobileapp.service.network;

public class TokenRequest {
    public String username;
    public String password;

    public TokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
