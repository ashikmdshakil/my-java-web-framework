package org.brainstation.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/*")
public class FrontController extends HttpServlet {
    static {
        System.out.println("*************************************");
        System.out.println("THIS IS MY CUSTOM WEB FRAMEWORK");
        System.out.println("*************************************");
    }

    public void init() {
        System.out.println("Front Controller is initiated!");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Simple JSON string (you could also use a JSON library like Jackson or Gson)
        String json = """
                    {
                        "message": "Hello from JSON Servlet!",
                        "status": "success!"
                    }
                """;

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.print(json);
        out.flush();
    }
}
