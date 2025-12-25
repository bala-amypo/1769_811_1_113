package com.example.demo.servlet;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BasicServlet extends HttpServlet {

@Override
protected void doGet(
HttpServletRequest request,
HttpServletResponse response
) throws IOException {
response.setStatus(HttpServletResponse.SC_OK);
}

@Override
protected void doPost(
HttpServletRequest request,
HttpServletResponse response
) throws IOException {
response.setStatus(HttpServletResponse.SC_OK);
}
}
