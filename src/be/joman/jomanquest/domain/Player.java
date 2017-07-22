package be.joman.jomanquest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Player extends Item implements Serializable{

    public Player() {
        super("Bob", "", "", false, true, false);
    }

    @Override
    public void inspect() {
        System.out.println("High my name is " + getName() + ", but you already knew that of course.");
        System.out.println("Of course you knew, because it is you.");
        System.out.println("But did you also know that you are carrying a huge backpack, containing all kinds of nifty stuff which could come in handy? ");
        System.out.println("Let's see what is already in there? ....");
        if(getItems().isEmpty()){
            System.out.println("Euhr .... it is empty, go and collect some stuff you will need it if you want to finish this game!");
        } else {
            System.out.println("Seems you already have: ");
            for (Item item : getItems()) {
                item.inspect();
            }
        }
    }
}
