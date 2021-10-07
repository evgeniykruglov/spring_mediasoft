package app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("info")
    private String info;


    @JsonCreator
    public User(@JsonProperty("id") Long id,
                @JsonProperty("name") String name,
                @JsonProperty("info") String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public User() {

    }
}
