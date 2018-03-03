package ru.web.ets.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.web.ets.AuthorizedUser;
import ru.web.ets.model.*;
import ru.web.ets.web.answer.AnswerRestController;
import ru.web.ets.web.question.QuestionRestController;
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

public class TestServlet extends HttpServlet {
    private TestRestController testRestController;
    private QuestionRestController questionRestController;
    private AdminRestController adminRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        testRestController = springContext.getBean(TestRestController.class);
        questionRestController = springContext.getBean(QuestionRestController.class);
        adminRestController = springContext.getBean(AdminRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String testid = Objects.requireNonNull(request.getParameter("testid"));
        User user = adminRestController.get(AuthorizedUser.id());

        Test test;
        if (testid.isEmpty() || "0".equals(testid)) {
                test = new Test(null, request.getParameter("testtext"), null, user);
                test = testRestController.create(test);
        } else {
            int tid = getId(request, "testid");
            test = testRestController.get(tid);
            if(request.getParameter("testtext") != null &&
                    !"".equals(request.getParameter("testtext"))) {
                test.setText(request.getParameter("testtext"));
            }

            if(request.getParameter("textquestion") != null &&
                    !"".equals(request.getParameter("textquestion")))
            {
                String qid = Objects.requireNonNull(request.getParameter("qid"));
                Question q;
                if (qid.isEmpty() || "0".equals(qid)) {
                    q = new Question(null, request.getParameter("textquestion"), null, user);
              //      q = questionRestController.create(q);
                    test.addQuestion(q);
                }
                else
                {
                    q = test.getQuestion(getId(request, "qid"));
                   // q = questionRestController.get(getId(request, "qid"));
                    q.setText(request.getParameter("textquestion"));

                    q.getAnswersList().
                            forEach(x -> {
                                x.getAnswer().setText(request.getParameter("text" + x.getId()));
                                x.setRight(request.getParameterValues("chbox" + x.getId())!=null && request.getParameterValues("chbox" + x.getId()).length != 0);
                            });

                    // add answer
                    String textAnswer = request.getParameter("addAnswerText");
                    if (textAnswer != null && textAnswer.length() > 0)
                        q.addAnswer(new Answer(null, textAnswer, null, user));

                    //q = questionRestController.update(q, tid);
                }
            }
                test = testRestController.save(test);
        }

        request.setAttribute("test", test);
        request.getRequestDispatcher("/testandquestions.jsp").forward(request, response);


/*

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
*/
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String testid = request.getParameter("testid");

/*        switch (action == null ? "all" : action) {
            default:
                request.setAttribute("questions",
                        testRestController.get(1).getQuestionList());
                request.getRequestDispatcher("/test.jsp").forward(request, response);
                break;
        }*/


        switch (action == null ? "all" : action) {
            case "createQuestion":
            case "updateQuestion":
                Test test2 = testRestController.get(getId(request,"testid"));
                request.setAttribute("test", test2);
                Question question = "createQuestion".equals(action) ? new Question(0, "", null, adminRestController.get(AuthorizedUser.id())) : questionRestController.get(getId(request, "qid"));

//                questionRestController.create(question);
//                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
                request.setAttribute("question", question);
                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);

                break;
            case "deleteAns":
                questionRestController.deleteAnswer(getId(request, "qid"), getId(request, "idAns"));
                Test test5 = testRestController.get(getId(request,"testid"));
                Question question2 = questionRestController.get(getId(request, "qid"));
                request.setAttribute("test", test5);
                request.setAttribute("question", question2);
                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
                break;
            case "deleteQuestion":
                Test test4 = testRestController.deleteQuestion(getId(request, "testid"), getId(request, "qid"));
                request.setAttribute("test", test4);
                request.getRequestDispatcher("/testandquestions.jsp").forward(request, response);
                break;
            case "delete":
                testRestController.delete(getId(request, "testid"));
                request.setAttribute("tests",
                        testRestController.getAll());
                request.getRequestDispatcher("/tests.jsp").forward(request, response);
                break;
            case "create":
            case "update":
                final Test test =
                        "create".equals(action) ? new Test(0, "", null, adminRestController.get(AuthorizedUser.id())) :
                                testRestController.get(getId(request, "testid"));
                request.setAttribute("test", test);
                request.getRequestDispatcher("/testandquestions.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("tests",
                        testRestController.getAll());
                request.getRequestDispatcher("/tests.jsp").forward(request, response);
                break;
        }
    }

    public static int getId(HttpServletRequest request, String nameParameter) {
        String paramId = Objects.requireNonNull(request.getParameter(nameParameter));
        return Integer.valueOf(paramId);
    }
}
