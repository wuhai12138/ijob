package model;

/**
 * Created by Administrator on 2016/8/24.
 */
public class GroupMember {
    private String[] usernames ;

    public GroupMember(String[] usernames){
        this.usernames = usernames;
    }

    public String[] getUsernames() {
        return usernames;
    }

    public void setUsernames(String[] usernames) {
        this.usernames = usernames;
    }
}
