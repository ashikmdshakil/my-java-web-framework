package org.brainstation.controller;

import org.brainstation.annotations.AppController;
import org.brainstation.annotations.Url;
import org.brainstation.constants.HttpMethod;

@AppController
public class UserController {

    @Url(value = "say-hello", httpMethod = HttpMethod.GET)
    public String sayHello() {
        return "Hello world!";
    }

    @Url(value = "say-project-description", httpMethod = HttpMethod.GET)
    public String sayProjectDescription() {
        return "This is my personal java web framework!";
    }
}
