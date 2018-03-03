package ru.web.ets.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
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

    private QuestionRestController questionRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        questionRestController = springContext.getBean(QuestionRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String textAnswer = request.getParameter("addAnswerText");

        /*Question question;
        if (id.isEmpty() || "0".equals(id)) {
            question = new Question(null,
                    request.getParameter("text"),
                    null, new ArrayList<Answer>());
            questionRestController.create(question);
        } else {
            // updating of answer list
            List<Answer> listAnswer = new ArrayList<>();
            questionRestController.get(Integer.valueOf(id)).getAnswerList().
                    forEach(x -> {
                        x.setText(request.getParameter("text" + x.getId()));
                        x.setCorrect(request.getParameterValues("chbox" + x.getId())!=null && request.getParameterValues("chbox" + x.getId()).length != 0);
                        listAnswer.add(x);
                    });

            // add answer
            if (textAnswer != null && textAnswer.length() > 0)
                listAnswer.add(new Answer(InMemoryQuestionRepositoryImpl.countAns.incrementAndGet(), textAnswer, null));

            // updating of question
            question = new Question(Integer.valueOf(id),
                    request.getParameter("text"),
                    null, listAnswer);
            questionRestController.update(question, question.getId());
        }

        if (!"0".equals(id) && (textAnswer != null && textAnswer.length() != 0)) {
            request.setAttribute("question", questionRestController.get(getId(request)));
            request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
        } else
            response.sendRedirect("questions");*/
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
                final Question question =
//                        "create".equals(action) ? new Question(0, "", null, new ArrayList<Answer>()) :
                        questionRestController.get(getId(request));
                request.setAttribute("question", question);
                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
                break;
            case "deleteAns":
                questionRestController.deleteAnswer(getId(request), getIdAns(request));
                request.setAttribute("question", questionRestController.get(getId(request)));
                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("questions",
                        questionRestController.getAll());
                request.getRequestDispatcher("/testandquestions.jsp").forward(request, response);
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
