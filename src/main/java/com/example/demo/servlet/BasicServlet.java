package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

try {
PrintWriter out = response.getWriter();
out.write("GET OK");
out.flush();
} catch (IOException e) {
response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}
}

@Override
protected void doPost(
HttpServletRequest request,
HttpServletResponse response
) throws IOException {

response.setStatus(HttpServletResponse.SC_CREATED);

try {
PrintWriter out = response.getWriter();
out.write("POST CREATED");
out.flush();
} catch (IOException e) {
response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}
}
}
