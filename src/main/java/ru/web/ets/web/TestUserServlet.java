package ru.web.ets.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.web.ets.web.test.TestRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestUserServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private TestRestController testRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        testRestController = springContext.getBean(TestRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        int id = TestServlet.getId(request);
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            default:
                request.setAttribute("questions",
                        testRestController.get(id).getQuestionsList());
                request.getRequestDispatcher("/test.jsp").forward(request, response);
                break;
        }
    }
}
