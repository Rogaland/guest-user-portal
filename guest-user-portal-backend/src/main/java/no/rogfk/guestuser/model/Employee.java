package main.java.no.rogfk.guestuser.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top", "brfkInfo"})
public final class Employee {

    @Id
    @ApiModelProperty(value = "This will be automatically constructed", hidden = true)
    private Name dn;

    @Attribute(name = "fullname")
    private String fullname;

    @Attribute(name = "brfkDisplayNameLocation")
    private String department;

    @Attribute(name = "mobile")
    private String mobile;

    public String getDn() {
        return dn.toString();
    }
}
