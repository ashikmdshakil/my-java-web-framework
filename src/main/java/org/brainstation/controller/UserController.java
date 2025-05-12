package org.brainstation.controller;

import org.brainstation.annotations.AppController;

@AppController()
public class UserController {
    public String sayHello() {
        return "Hello world!";
    }
}
