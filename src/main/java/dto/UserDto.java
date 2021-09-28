package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.Random;


@Getter
public class UserDto {
    @JsonProperty("id")
    private final long id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("info")
    private final String info;

    @JsonCreator
    public UserDto(String name, String info) {
        this.id = Math.abs(new Random().nextLong());
        this.name = name;
        this.info = info;
    }

    @JsonCreator
    public UserDto(long id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }



  
}
