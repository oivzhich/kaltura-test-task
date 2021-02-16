package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.log4j.Logger;
import types.UserSerializer;
import utils.RandomDataHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Data
@JsonSerialize(using = UserSerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private static final Logger LOG = Logger.getLogger(User.class);
    private String objectType;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private Integer countryId;
    private String externalId;
    private Country country;
    private Integer createDate;
    private Integer failedLoginCount;
    private Boolean householdId;
    private String roleIds;
    private String suspensionState;
    private String userState;



    public User() {
        setFields(Collections.emptyMap());
    }

    //setting default values for User
    private void setFields(Map parameters) {
        this.objectType = (String) parameters.getOrDefault("objectType", "KalturaOTTUser");
        this.username = (String) parameters.getOrDefault("username", RandomDataHelper.getUserName());
        this.firstName = (String) parameters.getOrDefault("firstName", RandomDataHelper.getFirstName());
        this.lastName = (String) parameters.getOrDefault("lastName", RandomDataHelper.getLastName());
        this.email = (String) parameters.getOrDefault("email", RandomDataHelper.getEmail());
        this.address = (String) parameters.getOrDefault("address", RandomDataHelper.getAddress());
        this.city = (String) parameters.getOrDefault("city", RandomDataHelper.getCity());
        this.countryId = (Integer) parameters.getOrDefault("countryId", 5);
        this.externalId = (String) parameters.getOrDefault("externalId", RandomDataHelper.getExternalId());
        this.country = (Country) parameters.getOrDefault("country", new Country());
        this.createDate = (Integer) parameters.getOrDefault("createDate", 0);
        this.failedLoginCount = (Integer) parameters.getOrDefault("failedLoginCount", 0);
        this.householdId = (Boolean) parameters.getOrDefault("householdId", false);
        this.roleIds = (String) parameters.getOrDefault("roleIds", "1");
        this.suspensionState = (String) parameters.getOrDefault("suspensionState", "NOT_SUSPENDED");
        this.userState = (String) parameters.getOrDefault("userState", "user_with_no_household");
    }
}
