package be.joman.jomanquest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Player extends Item implements Serializable{

    public Player() {
        super("Bob", "You are a beautiful person, but even more patient than beautiful", "", false, true, false);
    }

}
