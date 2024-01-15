package org.example.activemqproducer101.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Queue;
import org.example.activemqproducer101.dto.Student;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produce")
public class Producer {
    private JmsTemplate jmsTemplate;
    private Queue queue;

    public Producer(JmsTemplate jmsTemplate, Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    @GetMapping
    public String ok() {
        System.out.println("OK");
        return "OK";
    }
    @PostMapping("/message")
    public Student sendMessage(@RequestBody Student student) {
        System.out.println(student);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String studentAsJson = mapper.writeValueAsString(student);

            jmsTemplate.convertAndSend(queue, studentAsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
}
