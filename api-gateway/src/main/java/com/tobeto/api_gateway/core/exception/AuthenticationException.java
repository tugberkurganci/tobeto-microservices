package com.tobeto.api_gateway.core.exception;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException() {super("cant auth");}
}
