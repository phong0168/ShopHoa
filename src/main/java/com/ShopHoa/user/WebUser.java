package com.ShopHoa.user;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WebUser {
    @Pattern(regexp="^[a-zA-Z0-9_-]{3,16}$", message="tên người dùng phải bao gồm 3 đến 16 ký tự chữ cái, số, dấu gạch dưới hoặc gạch ngang, không được chứa khoảng trắng.")
    private String userName;

    @Size(min = 6, message = "mật khẩu phải có ít nhất 6 ký tự")
    private String password;

    @Size(min = 1, message = "is required")
    private String firstName;

    @Size(min = 1, message = "is required")
    private String lastName;

    @Size(min = 1, message = "is required")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    public WebUser() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
