package Controller;

import DAO.NewUserDB;
import Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/sqlGateway")
public class SQLGatewayServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            // =========================
            // INSERT
            // =========================
            if ("insert".equals(action)) {

                User user = new User();

                user.setFirstName(request.getParameter("firstName"));
                user.setLastName(request.getParameter("lastName"));
                user.setEmail(request.getParameter("email"));

                NewUserDB.insert(user);

                request.setAttribute("message",
                        "User inserted successfully. ID = " + user.getUserId());
            }

            // =========================
            // FIND
            // =========================
            else if ("find".equals(action)) {

                long userId = Long.parseLong(
                        request.getParameter("userId")
                );

                User user = NewUserDB.getUser(userId);

                request.setAttribute("foundUser", user);

                if (user == null) {
                    request.setAttribute("message",
                            "User not found.");
                }
            }

            // =========================
            // UPDATE
            // =========================
            else if ("update".equals(action)) {

                long userId = Long.parseLong(
                        request.getParameter("userId")
                );

                User user = NewUserDB.getUser(userId);

                if (user != null) {

                    user.setFirstName(
                            request.getParameter("firstName")
                    );

                    user.setLastName(
                            request.getParameter("lastName")
                    );

                    user.setEmail(
                            request.getParameter("email")
                    );

                    NewUserDB.update(user);

                    request.setAttribute("message",
                            "User updated successfully.");
                } else {
                    request.setAttribute("message",
                            "User not found.");
                }
            }

            // =========================
            // DELETE
            // =========================
            else if ("delete".equals(action)) {

                long userId = Long.parseLong(
                        request.getParameter("userId")
                );

                User user = NewUserDB.getUser(userId);

                if (user != null) {
                    NewUserDB.delete(userId);
                    request.setAttribute("message", "User deleted successfully.");
                } else {
                    request.setAttribute("message", "User not found.");
                }
            }

            // =========================
            // GET ALL
            // =========================
            else if ("getAll".equals(action)) {

                List<User> users = NewUserDB.getUsers();

                request.setAttribute("users", users);
            }

        } catch (Exception ex) {

            request.setAttribute(
                    "error",
                    ex.getMessage()
            );
        }

        getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }
}