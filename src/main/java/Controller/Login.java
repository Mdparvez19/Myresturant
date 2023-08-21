package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Mydao;
import DTO.CustomerSignup;

//mapping the url
@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Recieve values from front-end
		String email = req.getParameter("email");

		String password = req.getParameter("password");

		// verify if email exist
		Mydao dao = new Mydao();
		if (email.equals("admin@jsp.com") && password.equals("admin")) {
			resp.getWriter().print("<h1 style = 'color : red'>Login Successful</h1>");
			//logic to send to next page
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		} else {
		CustomerSignup customer = dao.fetchByEmail(email);
		if (customer == null) {
			resp.getWriter().print("<h1 style = 'color : red'> Invalid Email </h1> ");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			if (password.equals(customer.getPassword())) {
				resp.getWriter().print("<h1 style = 'color : green'> Login Successful </h1> ");
				req.getRequestDispatcher("Home.html").include(req, resp);

			} else {
				resp.getWriter().print("<h1 style = 'color : red'> Invalid Password </h1> ");
				req.getRequestDispatcher("Login.html").include(req, resp);


			}
		}
		}
	}
}
