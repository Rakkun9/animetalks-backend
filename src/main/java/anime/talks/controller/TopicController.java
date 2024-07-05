package anime.talks.controller;

import anime.talks.domain.topic.DataRegisterTopic;
import anime.talks.domain.topic.DataUpdateTopic;
import anime.talks.domain.topic.Topic;
import anime.talks.domain.topic.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity registerTopic(@RequestBody @Valid DataRegisterTopic dataRegisterTopic){
        Topic topic = topicRepository.save(new Topic(dataRegisterTopic));
        return ResponseEntity.ok(topic);
    }
    @GetMapping
    public ResponseEntity listTopics(){
        return ResponseEntity.ok(topicRepository.findAll());
    }
    @PutMapping
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid DataUpdateTopic dataUpdateTopic){
        Topic topic = topicRepository.getReferenceById(dataUpdateTopic.id());
        topic.updateData(dataUpdateTopic);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topicRepository.delete(topic);
        return ResponseEntity.noContent().build();
    }

}
