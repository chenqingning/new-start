package com.example.spring.handleException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    @GetMapping("/exception")
    public String getException() throws Exception {
        throw new Exception();
    }
}
