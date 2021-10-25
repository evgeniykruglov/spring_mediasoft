package app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @JsonIgnore
    private Long id;
    @JsonProperty("path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @JsonCreator
    public Photo(@JsonProperty("id") Long id,
                @JsonProperty("path") String path) {
        this.id = id;
        this.path = path;
    }

    public Photo() {

    }
}
