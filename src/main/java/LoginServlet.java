import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebInitParam;


@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam( name = "user", value ="Shubham"),
                @WebInitParam(name ="password", value ="Shubham123@")
        }
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get request parameter for User ID and Password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        //get servlet configuration param
        String userID = getServletConfig().getInitParameter("user");
        boolean regexUser = Pattern.compile("^([A-Z]{1,}[a-z]{2,})").matcher(user).matches();
        String password = getServletConfig().getInitParameter("password");
        boolean regexPass = Pattern.compile("^([A-Z]{1,}[a-z]{1,}[0-9]{1,}[@#$%&*]{1})").matcher(user).matches();

        if(userID.equals(user) && regexUser == true && password.equals(pwd) && regexPass == true) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color =red> Either username or password is incorrect!</font>");
            rd.include(request, response);
        }
    }
}
