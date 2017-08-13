package be.joman.jomanquest.domain;

import be.joman.jomanquest.domain.action.*;

import java.io.Serializable;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Gateway extends Item implements Serializable{

    private Room initialRoom;
    private Room secondaryRoom;

    public Gateway(String name, String description, String tip, Room initialRoom, Room secondaryRoom, boolean locked) {
        super(name, description, tip, false, false, false, locked);
        this.initialRoom = initialRoom;
        this.secondaryRoom = secondaryRoom;
        addActionRules(new ActionRule(ActionType.OPEN, this ));
    }

    //TODO add to C++
    public Gateway(String name, String description, String tip, Room initialRoom, Room secondaryRoom, boolean locked, boolean hidden) {
        super(name, description, tip, false, false, hidden, locked);
        this.initialRoom = initialRoom;
        this.secondaryRoom = secondaryRoom;
        addActionRules(new ActionRule(ActionType.OPEN, this ));
    }

    public Room getInitialRoom() {
        return initialRoom;
    }

    public Room getSecondaryRoom() {
        return secondaryRoom;
    }

    @Override
    public void inspect(){
        //TODO add to C++
        if(!isHidden()){
            if(isLocked()){
                System.out.println(getName() + ": " + getDescription());
            } else {
                super.inspect();
            }
        }
    }

}
