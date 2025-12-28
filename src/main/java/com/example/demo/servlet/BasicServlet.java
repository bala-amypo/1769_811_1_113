package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/basic")
public class BasicServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
throws IOException {
resp.getWriter().write("Servlet is running");
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
throws IOException {
resp.getWriter().write("Servlet POST handled");
}
}
