package br.com.fiap.apimypass.apimypass.auth;

public record Token(String token, String type, String username) {
}
