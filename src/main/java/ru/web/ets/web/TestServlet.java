package ru.web.ets.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.web.ets.model.*;
import ru.web.ets.web.answer.AnswerRestController;
import ru.web.ets.web.question.QuestionRestController;
import ru.web.ets.web.test.TestRestController;

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
    private ConfigurableApplicationContext springContext;
    private TestRestController testRestController;
    private QuestionRestController questionRestController;
    private AnswerRestController answerRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        testRestController = springContext.getBean(TestRestController.class);
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
        String testid = Objects.requireNonNull(request.getParameter("testid"));

        Test test;
        if (testid.isEmpty() || "0".equals(testid)) {
                test = new Test(null, request.getParameter("testtext"), null);
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
                    q = new Question(null, request.getParameter("textquestion"), null);
                    q = questionRestController.create(q);
                    test.addQuestion(q);
                }
                else
                {
                    q = test.getQuestion(getId(request, "qid"));
                   // q = questionRestController.get(getId(request, "qid"));
                    q.setText(request.getParameter("textquestion"));

                    List<TeacherAnswer> listAnswer = new ArrayList<>();
                    q.getAnswersList().
                            forEach(x -> {
                                x.getAnswer().setText(request.getParameter("text" + x.getId()));
                                x.setRight(request.getParameterValues("chbox" + x.getId())!=null && request.getParameterValues("chbox" + x.getId()).length != 0);
                                listAnswer.add(x);
                            });

                    // add answer
                    String textAnswer = request.getParameter("addAnswerText");
                    if (textAnswer != null && textAnswer.length() > 0)
                        listAnswer.add(new TeacherAnswer(new Answer(null, textAnswer, null)));

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
                Question question = "createQuestion".equals(action) ? new Question(0, "", null) : questionRestController.get(getId(request, "qid"));

//                questionRestController.create(question);
//                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
                request.setAttribute("question", question);
                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);

                break;
//            case "createAnswer":
//            case "updateAnswer":
//                Test test3 = testRestController.get(Integer.parseInt(testid));
//                request.setAttribute("test", test3);
//                Answer answer = "createAnswer".equals(action) ? new Answer(0, "", null) : answerRestController.get(getId(request, "idAnsS"));
//
//                request.setAttribute("answer", answer);
//                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
//
//                break;

//            case "deleteAns":
//                /*Question questionUdpAnswer = questionRestController.get(getId(request));
//                questionUdpAnswer*/
//                questionRestController.deleteAnswer(getId(request, "id"), getId(request, "idAns"));
//                request.setAttribute("question", questionRestController.get(getId(request, "id")));
//                request.getRequestDispatcher("/questionForm.jsp").forward(request, response);
//                break;
            case "create":
            case "update":
                final Test test =
                        "create".equals(action) ? new Test(0, "", null) :
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
