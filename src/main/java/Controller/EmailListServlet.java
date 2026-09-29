package Controller;

import java.io.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import Model.User;
import DAO.NewUserDB;
import Controller.helper;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/EmailListServlet")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  
        }
        String url = "/index.jsp";
        if (action.equals("join")) {
            url = "index.jsp";
        } else if (action.equals("add")){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            
            User user = new User(firstName, lastName, email);
            NewUserDB.insert(user);
            request.setAttribute("user", user);
            
            //send email to user 
            String to = email;
            String from = "duy321855@gmail.com";
            String subject = "Welcome to our email list";
            String body = "Dear " + firstName + ",\n\n"
                + "Thanks for joining our email list. "
                + "We'll make sure to send "
                + "you announcements about new products "
                + "and promotions.\n"
                + "Have a great day and thanks again!\n\n"
                + "Kelly Slivkoff\n"
                + "Mike Murach & Associates";
            boolean isBodyHTML = false;
            try {
                helper.sendEmail(to, from, subject, body, isBodyHTML);
            }
            catch (MessagingException e){
                String errorMessage
                    = "ERROR: Unable to send email. "
                    + "Check Tomcat logs for details.<br>"
                    + "NOTE: You may need to configure your system "
                    + "as described in chapter 14.<br>"
                    + "ERROR MESSAGE: " + e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                    this.log(
                    "Unable to send email. \n"
                    + "Here is the email you tried to send: \n"
                    + "=====================================\n"
                    + "TO: " + email + "\n"
                    + "FROM: " + from + "\n"
                    + "SUBJECT: " + subject + "\n\n"
                    + body + "\n\n");
            }
            url = "/thanks.jsp";
        }
          getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);   
        }
}
        