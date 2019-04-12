package org.flowable.flowablespringboot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Transactional
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonRepository personRepository;

    public void startProcess(String startMessage) {

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("strSN", "123ABC");
        ProcessInstance pi = runtimeService.startProcessInstanceByMessage(startMessage, variables);
        
        System.out.println("pid:" + pi.getId());
    }

    public List<Task> getTasks(String assignee) {
//        return taskService.createTaskQuery().taskAssignee(assignee).list();
        return taskService.createTaskQuery().list();
    }

    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }
    }
    
}
