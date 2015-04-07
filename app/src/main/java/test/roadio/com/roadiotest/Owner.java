package test.roadio.com.roadiotest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner
{
    @SerializedName("avatar_url")
    @Expose
    private String avatar_url;

    public String getAvatar()
    {
        return avatar_url;
    }

    /*@SerializedName("fullname")
    @Expose
    private String fullname;

    public String getFullName()
    {
        return fullname;
    }*/
    /*@SerializedName("surname")
    @Expose
    private String surname;

    public String getSurname()
    {
        return surname;
    }

    @SerializedName("address")
    @Expose
    private String address;

    public String getAddress()
    {
        return address;
    }

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    public String getPhoneNumber()
    {
        return phone_number;
    }

    @SerializedName("email")
    @Expose
    private String email;

    public String getEmail()
    {
        return email;
    }
    @SerializedName("id")
    @Expose
    private String id;

    public String getID()
    {
        return id;
    }

    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public String getCreatedAt()
    {
        return createdAt;
    }

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public String getUpdatedAt()
    {
        return updatedAt;
    }*/
}