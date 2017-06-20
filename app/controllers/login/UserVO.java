package controllers.login;


import com.fasterxml.jackson.annotation.JsonProperty;
import controllers.base.BaseVO;

class UserVO extends BaseVO{
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    @JsonProperty("verified")
    private boolean isVerified;


    public String getId() {
        return id;
    }

    public UserVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserVO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserVO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserVO setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public UserVO setVerified(boolean verified) {
        isVerified = verified;
        return this;
    }
}
