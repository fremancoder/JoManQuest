package be.joman.jomanquest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Room extends Item implements Serializable{

    private List<Gateway> gateways;

    public Room(String name, String description, String tip) {
        super(name, description, tip, false, false, false);
    }

    public List<Gateway> getGateways() {
        if(gateways == null){
            gateways = new ArrayList<>();
        }
        return gateways;
    }

}
