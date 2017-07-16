package be.joman.jomanquest.domain;

import be.joman.jomanquest.domain.action.*;

import java.io.Serializable;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Gateway extends Item implements Serializable{

    private boolean locked;

    private Room initialRoom;
    private Room secondaryRoom;

    public Gateway(String name, String description, String tip, Room initialRoom, Room secondaryRoom, boolean locked) {
        super(name, description, tip, false, false, false);
        this.initialRoom = initialRoom;
        this.secondaryRoom = secondaryRoom;
        this.locked = locked;
    }

    public Room getInitialRoom() {
        return initialRoom;
    }

    public void setInitialRoom(Room initialRoom) {
        this.initialRoom = initialRoom;
    }

    public Room getSecondaryRoom() {
        return secondaryRoom;
    }

    public void setSecondaryRoom(Room secondaryRoom) {
        this.secondaryRoom = secondaryRoom;
    }

    public boolean isLocked() {
        return locked;
    }

    public void unLock() {
        System.out.println(getName() + " has been unlocked, it is open now, you can travers the gateway");
        locked = false;
    }

    public void lock() {
        System.out.println(getName() + " has been locked, no-one can enter or leave");
        locked = true;
    }

}
