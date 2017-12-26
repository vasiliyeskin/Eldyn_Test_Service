package ru.web.ets.web;

import org.hibernate.Hibernate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.web.ets.AuthorizedUser;
import ru.web.ets.model.*;
import ru.web.ets.web.test.TestRestController;
import ru.web.ets.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestUserServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private TestRestController testRestController;
    private AdminRestController adminRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        testRestController = springContext.getBean(TestRestController.class);
        adminRestController = springContext.getBean(AdminRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(Objects.requireNonNull(request.getParameter("testid")));
        String textAnswer = request.getParameter("addAnswerText");
        Test test = testRestController.get(id);
//        Hibernate.initialize(test.getQuestionsList().forEach(x->x.getQuestion().initializeUserAnswersList()));

        User user = adminRestController.get(AuthorizedUser.id());
        // create of user's answer
        test.getQuestionsList().forEach(
                x -> x.getQuestion().getAnswersList().forEach(
                        y -> x.getQuestion().addUserAnswerToList(new UserAnswer(y.getAnswer().getId(), (request.getParameterValues("chbox" + y.getId()) != null && request.getParameterValues("chbox" + y.getId()).length != 0), "", user, x.getQuestion()))));

        test = testRestController.save(test);
        request.setAttribute("userTest", test);
        request.setAttribute("teacherTest", test);
        request.getRequestDispatcher("/testCorrectAnswer.jsp").forward(request, response);
   }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        int id = TestServlet.getId(request, "testid");
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            /*case "createQuestion":
                Test test = testRestController.get(1);
                request.setAttribute("test", test);
                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
                break;*/
            default:
                Test currentTest = testRestController.get(id);
                request.setAttribute("currenttest", currentTest);
                request.getRequestDispatcher("/test.jsp").forward(request, response);
                break;
        }
    }
}
