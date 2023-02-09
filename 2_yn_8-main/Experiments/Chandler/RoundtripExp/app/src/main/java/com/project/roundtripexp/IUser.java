package com.project.roundtripexp;

public interface IUser {
     public void setUser(String s);
     public void setId(String s);
     public void setDescription(String s);
     public void setImage(int i);

    public String getUser();
    public String getId();
    public String getDescription();
    public int getImage();
}
