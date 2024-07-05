package anime.talks.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "topic")
@Entity (name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private String author;
    private String course;
    private boolean active;
    private String status; // Default value

    public Topic (DataRegisterTopic dataRegisterTopic){
        this.title = dataRegisterTopic.title();
        this.message = dataRegisterTopic.message();
        this.author = dataRegisterTopic.author();
        this.course = dataRegisterTopic.course();
        this.status = dataRegisterTopic.status();
    }
    public void updateData (DataUpdateTopic dataUpdateTopic){
        if (dataUpdateTopic.title() != null){
            this.title = dataUpdateTopic.title();
        }
        if (dataUpdateTopic.message() != null){
            this.message = dataUpdateTopic.message();
        }
        if (dataUpdateTopic.course() != null){
            this.course = dataUpdateTopic.course();
        }
    }
    public void disableTopic(){
        this.active = false;
    }

}

