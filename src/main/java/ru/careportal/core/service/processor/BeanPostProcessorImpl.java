package ru.careportal.core.service.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.careportal.core.db.dao.AnketaDao;
import ru.careportal.core.db.dao.AnswerDao;
import ru.careportal.core.db.dao.QuestionDao;
import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.db.model.Answer;
import ru.careportal.core.db.model.Question;

@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {

    //private static final Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("anketaDaoImpl")) {
            AnketaDao anketaDao = ((AnketaDao) bean);
            Anketa anketa = new Anketa(1, "Anketa 1", anketaDao.getAnketaQuestionsById(1));
            anketaDao.addAnketa(anketa);
        }

        if (beanName.equals("questionDaoImpl")) {
            QuestionDao questionDao = ((QuestionDao) bean);
            for(int i = 1; i <= 5; i++) {
                Question question = new Question(i, "Question " + i, "Yes/No", null);
                question.setAnswerList(questionDao.getQuestionAnswersById(i));
                questionDao.addQuestion(question);
            }
        }

        if (beanName.equals("answerDaoImpl")) {
            AnswerDao answerDao = ((AnswerDao) bean);
            Answer answer = new Answer(1, "Yes");
            answerDao.addAnswer(answer);
            answer = new Answer(2, "No");
            answerDao.addAnswer(answer);
        }

        return bean;
    }
}