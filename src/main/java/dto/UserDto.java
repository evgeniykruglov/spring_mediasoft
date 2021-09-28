package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Random;
import java.util.UUID;


@Getter
public class UserDto {
    @JsonProperty("uuid")
    private final UUID uuid;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("info")
    private final String info;


    @JsonCreator
    public UserDto(@JsonProperty("uuid") UUID uuid, @JsonProperty("name") String name, @JsonProperty("info") String info) {
        this.uuid = uuid;
        this.name = name;
        this.info = info;
    }
}
