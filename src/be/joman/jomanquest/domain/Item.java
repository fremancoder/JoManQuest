package be.joman.jomanquest.domain;

import be.joman.jomanquest.domain.action.ActionRule;
import be.joman.jomanquest.domain.action.ActionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
    private boolean isLocked;

    private List<Item> items = new ArrayList<>();

    private List<ActionRule> actionRules = new ArrayList<>();

    public Item(String name, String description, String info, boolean isCollectible, boolean isMovable) {
        this(name, description, info, isCollectible, isMovable, false, false);
    }

    public Item(String name, String description, String info, boolean isCollectible, boolean isMovable, boolean isHidden, boolean isLocked) {
        this.name = name;
        this.description = description;
        this.info = info;
        this.isCollectible = isCollectible;
        this.isMovable = isMovable;
        this.isHidden  = isHidden;
        this.isLocked = isLocked;

        addActionRules(new ActionRule(ActionType.INSPECT, this ));
        addActionRules(new ActionRule(ActionType.TIP, this ));

        if(isCollectible) addActionRules(new ActionRule(ActionType.TAKE, this ));
    }

    public String getName() {
        return name;
    }

    public String getLowerCaseName() { return name.toLowerCase(); }

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

    public void unHide() {
        isHidden = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unLock() {
        System.out.println(getName() + " has been unlocked.");
        isLocked = false;
    }

    public void lock() {
        System.out.println(getName() + " has been locked.");
        isLocked = true;
    }

    public List<Item> getItems() {
        if(items == null){
            items = new ArrayList<>();
        }
        return items;
    }

    public void addActionRules(ActionRule actionRule) {
        if(actionRule.getDirectObject().equals(this)){
            actionRules.add(actionRule);
        } else {
            System.out.println("WARNING: ActionRule not added direct objects do not match");
        }
    }

    public Item findItem(String itemName){
        //Use getItems instead of items to avoid nullpointer
        for (Item item : getItems()) {
            if(item.getLowerCaseName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    public ActionRule findActionRule(ActionType actionType, List<Item> objects){
        for (ActionRule actionRule : actionRules) {
            if(actionRule.getActionType().equals(actionType) && indirectObjectsMatch(actionRule, objects)){
                return actionRule;
            }
        }
        return null;
    }

    private boolean indirectObjectsMatch(ActionRule actionRule, List<Item> objects) {
        boolean allIndirectObjectsFound = true;
        for (Item item : actionRule.getIndirectObjects() ) {
            if(!item.isHidden() && !objects.contains(item)){
                allIndirectObjectsFound = false;
            }
        }
        return allIndirectObjectsFound;
    }

    public void inspect(){
        System.out.println(name + ": " + description);
        inspectItems();
    }

    public void inspectItems() {
        //Use getItems instead of items to avoid nullpointer
        for (Item item : getItems()) {
            if(!item.isHidden()) {
                item.inspect();
            }
        }
    }

    public void info() {
        System.out.println(info);
    }

    public void removeItem(Item directObject) {
        if(items != null && !items.isEmpty()){
            Iterator<Item> itr = items.iterator();
            while (itr.hasNext()) {
                Item item = itr.next();
                if(item.equals(directObject)){
                    itr.remove();
                } else {
                    if(!item.isLocked) item.removeItem(directObject);
                }
            }
        }
    }

    public void move(List<Item> indirectObjects) {
        System.out.println("The " + name + " has been moved, who knows what has happened?");
        for (Item item : indirectObjects) {
            if(item.isHidden()) item.unHide();
        }
    }

    public List<Item> findObjects(final List<String> words) {
        List<Item> objects = new ArrayList<>();
        //Use getItems instead of items to avoid nullpointer
        for (Item item : getItems()) {
            if(!item.isHidden()){
                if(words.contains(item.getLowerCaseName())) objects.add(item);
                if(!item.isLocked()) objects.addAll(item.findObjects(words));
            }
        }
        return objects;
    }

}
