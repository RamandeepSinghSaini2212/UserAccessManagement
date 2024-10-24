package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/pendingRequests")
public class ManagerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            String query = "SELECT requests.id, users.username, software.name, requests.reason, requests.status " +
                           "FROM requests " +
                           "JOIN users ON requests.user_id = users.id " +
                           "JOIN software ON requests.software_id = software.id " +
                           "WHERE requests.status = 'Pending'";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            request.setAttribute("pendingRequests", rs);
            request.getRequestDispatcher("/pendingRequests.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int requestId = Integer.parseInt(request.getParameter("requestId"));

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            String status = "Rejected";  // Default status to rejected
            if ("approve".equals(action)) {
                status = "Approved";
            }

            String query = "UPDATE requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();

            response.sendRedirect("pendingRequests");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to update request.");
        }
    }
}
