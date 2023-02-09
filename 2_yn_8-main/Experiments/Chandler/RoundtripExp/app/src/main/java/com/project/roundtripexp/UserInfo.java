package com.project.roundtripexp;

public class UserInfo implements IUser {
    String username;
    String id;
    String description;
    int imageId;



    @Override
    public void setUser(String s) {
        username = s;
    }

    @Override
    public void setId(String s) {
        id = s;
    }

    @Override
    public void setDescription(String s) {
        description = s;
    }

    @Override
    public void setImage(int i) {
        imageId = i;
    }

    @Override
    public String getUser() {
        return username;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getImage() {
        return imageId;
    }
}
