package test.roadio.com.roadiotest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchItems
{
    @SerializedName("name")
    @Expose
    private String name;

    public String getName()
    {
        return name;
    }

    @SerializedName("full_name")
    @Expose
    private String full_name;

    public String getFullName()
    {
        return full_name;
    }

    @SerializedName("owner")
    @Expose
    private Owner owner;

    public Owner getOwner()
    {
        return owner;
    }

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription()
    {
        return description;
    }

    @SerializedName("watchers_count")
    @Expose
    private String watchers_count;

    public String getWatchers_count()
    {
        return watchers_count;
    }

    @SerializedName("forks_count")
    @Expose
    private String forks_count;

    public String getForks_count()
    {
        return forks_count;
    }
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