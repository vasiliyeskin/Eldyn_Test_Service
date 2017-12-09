package ru.web.ets.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.web.ets.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private AdminRestController adminRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        adminRestController = springContext.getBean(AdminRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String textAnswer = request.getParameter("addAnswerText");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "all":
            default:
                request.setAttribute("users",
                        adminRestController.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }
    }

}
