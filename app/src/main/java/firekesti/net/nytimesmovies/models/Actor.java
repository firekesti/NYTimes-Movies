package firekesti.net.nytimesmovies.models;

import com.squareup.moshi.Json;

public class Actor {

    @Json(name = "actor")
    private String actor;
    @Json(name = "character")
    private String character;

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

}
