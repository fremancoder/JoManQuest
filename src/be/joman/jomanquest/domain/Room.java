package be.joman.jomanquest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Room extends Item implements Serializable{

    private List<Gateway> gateways = new ArrayList<>();

    //Moet final(const) in constructor?
    public Room(final String name, final String description, final String tip) {
        super(name, description, tip, false, false);
    }

    public List<Gateway> getGateways() {
        return gateways;
    }

    public void inspect(){
        System.out.println("You are now in the " + getName() );
        System.out.println("There seem to be a few exit points " );
        for (Gateway gateway : gateways ) {
            gateway.inspect();
        }
        if(!getItems().isEmpty()){
            System.out.println("and there is a lot of stuff lying around worthwhile investigating");
            super.inspectItems();
        }
    }

    public List<Item> findObjects(final List<String> words) {
        List<Item> objects = new ArrayList<>();

        objects.addAll(super.findObjects(words));
        objects.addAll(findGateways(words));

        return objects;
    }

    private List<Item> findGateways(final List<String> words) {
        List<Item> objects = new ArrayList<>();
        //Use getItems instead of items to avoid nullpointer
        for (final Item item : gateways) {
            if(!item.isHidden()){
                if(words.contains(item.getLowerCaseName())) objects.add(item);
                if(!item.isLocked()) objects.addAll(item.findObjects(words));
            }
        }
        return objects;
    }


}
