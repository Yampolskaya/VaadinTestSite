package com.history.view;


import com.history.HistoryApplicationUI;
import com.history.bean.Answer;
import com.history.bean.Task;
import com.history.design.TestFormDesign;
import com.vaadin.ui.CheckBox;


import java.util.ArrayList;
import java.util.List;

public class TestFormView extends TestFormDesign {

    private int currentNumb = 1;
    private List<Task> allTasks;
    

    public TestFormView(List<Task> allTasks, HistoryApplicationUI parentComponent){
        this.allTasks = allTasks;
        setContent(allTasks.get(0));
       
        
        back.addClickListener(event -> {
            currentNumb--;
            if (currentNumb == 1){
                back.setEnabled(false);
            }
            setContent(allTasks.get(currentNumb-1));
        });
        forvard.addClickListener(event -> {
            
            if(currentNumb == allTasks.size()){
                parentComponent.firstPageState();
            } else {
                currentNumb++;
                back.setEnabled(true);
                setContent(allTasks.get(currentNumb - 1));
            }
        });
    }

    public void setContent(Task task){
        question.setValue(task.getText());
        List<Answer> allAnswers = new ArrayList<>(task.getAnswerVariants());
        bindAnswer(allAnswers.get(0), ansver1);
        bindAnswer(allAnswers.get(1), ansver2);
        bindAnswer(allAnswers.get(2), ansver3);
        bindAnswer(allAnswers.get(3), ansver4);
    }

    private void bindAnswer(Answer answer, CheckBox checkBox) {
        checkBox.setCaption(answer.getTextAnswer());
        checkBox.setValue(answer.isCheck());
        checkBox.addValueChangeListener(valueChangeEvent -> answer.setCheck((boolean)valueChangeEvent.getProperty().getValue()));
    }


    public String getResults() {
        int correctAnswers = 0;
        for (Task eachTask : allTasks){
            if(eachTask.isWholeQuestionCorrect()){
                correctAnswers++;
            }
        }
        return ("Вірних відповідей: " + correctAnswers + " з " + allTasks.size());
    }
}
