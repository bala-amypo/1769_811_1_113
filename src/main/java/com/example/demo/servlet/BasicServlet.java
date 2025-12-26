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
response.setContentType("text/plain");
response.setCharacterEncoding("UTF-8");

PrintWriter out = response.getWriter();
out.write("Ok");
out.flush();
}

@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws IOException {

response.setStatus(HttpServletResponse.SC_CREATED);
response.setContentType("text/plain");
response.setCharacterEncoding("UTF-8");

PrintWriter out = response.getWriter();
out.write("Created");
out.flush();
}
}
