package ru.web.ets.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.web.ets.dto.UserAnswer;
import ru.web.ets.dto.UserQuestion;
import ru.web.ets.web.test.TestRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class TestServlet extends HttpServlet {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String textAnswer = request.getParameter("addAnswerText");


        // copy of questions
        Collection<UserQuestion> userQuestions = new ArrayList<UserQuestion>();
        testRestController.get(1).getCopyQuestionList().forEach(x->userQuestions.add(new UserQuestion(x)));
        // copy of answers
        userQuestions.forEach(x -> x.setAnswerList(x.getCopyAnswerList()));
        userQuestions.forEach(x -> x.getAnswerList().
                forEach(y->{y = new UserAnswer(y);}));

        userQuestions.forEach(x -> x.getAnswerList().
                forEach(y -> {
                    y.setUserChoose(request.getParameterValues("chbox" + y.getId()) != null && request.getParameterValues("chbox" + y.getId()).length != 0);
                }));

   //     request.setAttribute("questions", testRestController.get(1).getQuestionList());
        request.setAttribute("userQuestions", userQuestions);
        request.getRequestDispatcher("/testCorrectAnswer.jsp").forward(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            default:
                request.setAttribute("questions",
                        testRestController.get(1).getQuestionList());
                request.getRequestDispatcher("/test.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private int getIdAns(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("idAns"));
        return Integer.valueOf(paramId);
    }
}
