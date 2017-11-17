package ru.web.ets.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.web.ets.model.Question;
import ru.web.ets.web.question.QuestionRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class QuestionServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private QuestionRestController questionRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        questionRestController = springContext.getBean(QuestionRestController.class);
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

        Question question;
        if(id.isEmpty() || "0".equals(id)) {
            question = new Question(null,
                    request.getParameter("text"),
                    null, null, -1);
            questionRestController.create(question);
        }
        else
        {
            question = new Question(Integer.valueOf(id),
                request.getParameter("text"),
                    null,
                    questionRestController.get(Integer.valueOf(id)).getAnswerList(),
                    -1);
            questionRestController.update(question, question.getId());
        }

        response.sendRedirect("questions");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                questionRestController.delete(id);
                response.sendRedirect("questions");
                break;
            case "create":
            case "update":
                final Question meal = "create".equals(action) ?
                        new Question(0, "", null, null, -1) :
                        questionRestController.get(getId(request));
                request.setAttribute("question", meal);
                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("questions",
                        questionRestController.getAll());
                request.getRequestDispatcher("/questions.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
