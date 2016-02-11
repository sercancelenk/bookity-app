package sr.api.Util.enums;

/**
 * Created by sercan on 10/02/16.
 */
public enum RoleEnums {
    USER(1), ADMIN(2);

    private int value;

    private RoleEnums(int val){
        this.value = val;
    }

    public int getValue(){
        return this.value;
    }
}
