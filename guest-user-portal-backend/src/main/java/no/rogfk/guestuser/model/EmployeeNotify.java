package no.rogfk.guestuser.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top", "brfkInfo"})
public final class EmployeeNotify {

    @Id
    @ApiModelProperty(value = "This will be automatically constructed", hidden = true)
    private Name dn;

    @Attribute(name = "fullname")
    private String fullname;

    @Attribute(name = "mobile")
    private String mobile;

    public String getFullname() {
        return fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDn() {
        return dn.toString();
    }
}
