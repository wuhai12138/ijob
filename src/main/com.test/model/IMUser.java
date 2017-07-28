package model;

/**
 * Created by Administrator on 2016/8/23.
 */
public class IMUser {
    private String username;
    private String password;
    private String nickname;

    public IMUser(){
    }

    public IMUser(String username,String password,String nickname){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
