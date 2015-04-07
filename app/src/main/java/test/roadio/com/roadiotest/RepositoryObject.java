package test.roadio.com.roadiotest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RepositoryObject
{
    @SerializedName("name")
    @Expose
    private String name;

    public String getName()
    {
        return name;
    }

    @SerializedName("total_count")
    @Expose
    private int total_count;

    public int getTotal_count()
    {
        return total_count;
    }

    @SerializedName("items")
    @Expose
    private ArrayList<SearchItems> items;

    public ArrayList<SearchItems> getItems()
    {
        return items;
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