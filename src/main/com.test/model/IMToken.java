package model;

/**
 * Created by Administrator on 2016/8/23.
 */
public class IMToken {
    private String grant_type = "client_credentials";
    private String client_id = "YXA6a43wcF93EeafrhmQfu6qqA";
    private String client_secret = "YXA6aPjoiRKInyJ8e7uGMdAYmX8BSEY";

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}
