package be.joman.jomanquest.domain;

import be.joman.jomanquest.domain.action.ActionRule;
import be.joman.jomanquest.domain.action.ActionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freddy on 15-7-2017.
 */
public class Item implements Serializable{

    private String name;
    private String description;
    private String info;

    private boolean isCollectible;
    private boolean isMovable;
    private boolean isHidden;

    private List<Item> items;

    private List<ActionRule> actionRules;

    public Item(String name, String description, String info, boolean isCollectible, boolean isMovable, boolean isHidden) {
        this.name = name;
        this.description = description;
        this.info = info;
        this.isCollectible = isCollectible;
        this.isMovable = isMovable;
        this.isHidden  = isHidden;

        getActionRules().add(new ActionRule(ActionType.INSPECT, this ));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isCollectible() {
        return isCollectible;
    }

    public void setCollectible(boolean collectible) {
        this.isCollectible = collectible;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setMovable(boolean movable) {
        this.isMovable = movable;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public List<Item> getItems() {
        if(items == null){
            items = new ArrayList<>();
        }
        return items;
    }

    public List<ActionRule> getActionRules() {
        if(actionRules == null){
            actionRules = new ArrayList<>();
        }
        return actionRules;
    }

    public Item findItem(String itemName){
        for (Item item : items) {
            if(item.getName().toLowerCase().equals(itemName.toLowerCase())){
                return item;
            }
        }
        return null;
    }

    public ActionRule findActionRule(ActionType actionType){
        for (ActionRule actionRule : actionRules) {
            if(actionRule.getActionType().equals(actionType)){
                return actionRule;
            }
        }
        return null;
    }

    public void inspect(){
        System.out.println(name + ": " + description);
        if(items != null){
            for (Item item : items) {
                if(!item.isHidden()) {
                    item.inspect();
                }
            }
        }
    }

}
