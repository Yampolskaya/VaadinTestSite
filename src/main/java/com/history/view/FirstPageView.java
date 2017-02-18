package com.history.view;

import com.history.HistoryApplicationUI;
import com.history.bean.Answer;
import com.history.bean.Task;
import com.history.design.FirstPage;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Label;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by знаток on 29.01.2017.
 */
public class FirstPageView extends FirstPage {
    private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    public static final String MAIN_PAGE_TEXT = "/static/conspects/main.txt";
    public static final String HISTORY_TEXT = "/static/conspects/history.txt";
    public static final String HUMAN_TEXT = "/static/conspects/human.txt";
    public static final String JURI_TEXT = "/static/conspects/pravo.txt";
    public Map<String, Set<Answer>> historyQuest = readXmlTest("/static/tests/historyTest.xml");
    public Map<String, Set<Answer>> humanQuest = readXmlTest("/static/tests/humanTest.xml");
    public Map<String, Set<Answer>> juriQuest = readXmlTest("/static/tests/jurisTest.xml");
    private List<Task> results;
    private HistoryApplicationUI parentComponent;

    public Label getContent(){
        return content;
    }

    public FirstPageView(HistoryApplicationUI components) {
        parentComponent = components;
        content.setValue(getTextFromFile(MAIN_PAGE_TEXT));
        //content.setContentMode(Label.CONTENT_XHTML);
        foto1.setSource(new FileResource(new File(basepath + "/WEB-INF/images/foto1.jpg")));
        mainPage.addClickListener(event -> content.setValue(getTextFromFile(MAIN_PAGE_TEXT)));
        historyLink.addClickListener(event -> content.setValue(getTextFromFile(HISTORY_TEXT)));
        humanLink.addClickListener(event -> content.setValue(getTextFromFile(HUMAN_TEXT)));
        jurisprudenceLink.addClickListener(event -> content.setValue(getTextFromFile(JURI_TEXT)));


        historyTest.addClickListener(event -> beginTest(historyQuest));
        humanTest.addClickListener(event -> beginTest(humanQuest));
        jurisprudenceTest.addClickListener(event -> beginTest(juriQuest));
    }

    private void beginTest(Map<String, Set<Answer>> questions) {
        results = new ArrayList<>();
        for (Map.Entry<String, Set<Answer>> entry : questions.entrySet()) {
            results.add(new Task(entry.getKey(), entry.getValue()));
        }
        parentComponent.testPageState(new TestFormView(results, parentComponent));
    }

    private String getTextFromFile(String relativePath) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(basepath + relativePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Scanner(fis, "UTF-8").useDelimiter("\\A").next();
    }

    private Map<String, Set<Answer>> readXmlTest(String relativePath) {

        Map<String, Set<Answer>> quest = new HashMap<>();
        File fXmlFile = new File(basepath + relativePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            doc = dBuilder.parse(fXmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("question");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            Set<Answer> answersSet = new HashSet<>();

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                String question = eElement.getFirstChild().getNodeValue().trim();
                NodeList answers = eElement.getElementsByTagName("answer");
                quest.put(question, answersSet);
                for (int i = 0; i < answers.getLength(); i++) {
                    Element answer = (Element) answers.item(i);
                    String answerText = answer.getTextContent();
                    boolean correct = false;
                    if (answer.hasAttribute("correct") && Boolean.valueOf(answer.getAttribute("correct"))) {
                        correct = true;
                    }
                    Answer answObj = new Answer(answerText, correct);
                    answersSet.add(answObj);

                }
            }

        }
        return quest;
    }

//    Everybody needs a good hobby to make his life more saturated, interesting, engrossing.
//    One of my hobbies is horse riding. I really like these strong and smart animals. One of my acquaintances has his own farm in the countryside. My friends and I hang out there quite often, especially in summer. In this season many people living in a city have a lack of fresh air and dream about holidays out of the city. The nature is  incredible there. One of the advantages of such rest is the total freedom in a choice of a way. You can go anywhere: through the forest or around the lake. As a rule, we make barbecue and fondue after riding and have a good day.
//    In addition to riding, I like animals. I`m dreaming of visiting Africa and enjoy the journey through a safari park.
//---- 93
//    I`m also fond of yoga.
//    Yoga gives me the an opportunity to relax after a busy day. And this is a good way to improve my concentration. Besides, it is very beneficial to my  health. In a yoga class I work not only with my body, but also with breathing. After this practice, I feel physically energetic and cheerful.
//            ---- 144
//    I have one more hobby. This is time management. This science is really helpful for people who want to manage their time more effectively and be able to do more things in shorter periods and have more free time.
//    Time management has a big number of interesting methods. I use some of them in my daily life. For example, to solve quite a big task, you have to "cut an elephant on beefstakes".
//            ---- 200
}
