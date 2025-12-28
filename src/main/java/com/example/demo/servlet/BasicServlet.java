package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/basic")
public class BasicServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
throws IOException {

resp.setStatus(HttpServletResponse.SC_OK);

PrintWriter writer = resp.getWriter();
writer.write("Servlet is running");
writer.flush();
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
throws IOException {

resp.setStatus(HttpServletResponse.SC_CREATED);

PrintWriter writer = resp.getWriter();
writer.write("Servlet POST handled");
writer.flush();
}
}
