package com.mathworks.ci;

public class RestAPIURL {
    String base_url = GetTestData.getPropValues("action.rest.api.baseURL");;
    String owner;
    String repo;
    String complete_url;

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String common_url(){
        return base_url + "/" + owner + "/" + repo + "/";
    }

    public String getComplete_url(String action_url) {
        complete_url = base_url + "/" + owner + "/" + repo + "/" + action_url;
        return complete_url;
    }
}

