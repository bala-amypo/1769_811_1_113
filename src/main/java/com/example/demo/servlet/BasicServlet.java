package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BasicServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws IOException {

response.setStatus(HttpServletResponse.SC_OK);
PrintWriter out = response.getWriter();
out.write("Servlet is running");
out.flush();
}

@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws IOException {

response.setStatus(HttpServletResponse.SC_CREATED);
PrintWriter out = response.getWriter();
out.write("Servlet POST handled");
out.flush();
}
}
