package app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @JsonIgnore
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
/*    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photo;*/


}
