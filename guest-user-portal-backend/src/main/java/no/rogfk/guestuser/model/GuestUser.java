package no.rogfk.guestuser.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import no.rogfk.guestuser.utilities.LdapDateStringToDateDeserializer;
import no.rogfk.guestuser.utilities.LdapDateStringToDateSerializer;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;

@ApiModel
@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top", "brfkInfo", "brfkGuest"})
public class GuestUser {

    @Id
    @ApiModelProperty(value = "This will be automatically constructed", hidden = true)
    private Name dn;

    @Attribute(name = "cn")
    @ApiModelProperty(value = "This will be automatically generated", hidden = true)
    private String cn;

    @Attribute(name = "givenname")
    private String firstName;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "l")
    private String organization;

    @Attribute(name = "mobile")
    private String mobile;

    @Attribute(name = "mail")
    private String mail;

    @Attribute(name = "userPassword")
    private String password;

    @Attribute(name = "brfkOwner")
    private Name owner;

    @Attribute(name = "brfkGuestPhysicalVisitLocation")
    private String physicalVisitLocation;

    @ApiModelProperty(value = "This will be automatically set", hidden = true)
    @Attribute(name = "loginDisabled")
    private boolean loginDisabled;

    @Attribute(name = "brfkGuestDateOfVisit")
    @ApiModelProperty(value = "This will be automatically generated", hidden = true)
    @JsonSerialize(using = LdapDateStringToDateSerializer.class)
    @JsonDeserialize(using = LdapDateStringToDateDeserializer.class)
    private String dateOfVisit;

    public static GuestUser newInstance(GuestUser guestUser) {
        GuestUser newGuestUser = new GuestUser();

        newGuestUser.setDn(LdapNameBuilder.newInstance(guestUser.getDn()).build());
        newGuestUser.setCn(guestUser.getCn());
        newGuestUser.setLoginDisabled(guestUser.isLoginDisabled());
        newGuestUser.setDateOfVisit(guestUser.getDateOfVisit());
        newGuestUser.setFirstName(guestUser.getFirstName());
        newGuestUser.setLastName(guestUser.getLastName());
        newGuestUser.setMail(guestUser.getMail());
        newGuestUser.setMobile(guestUser.getMobile());
        newGuestUser.setOrganization(guestUser.getOrganization());
        newGuestUser.setOwner(guestUser.getOwner());
        newGuestUser.setPhysicalVisitLocation(guestUser.getPhysicalVisitLocation());

        return newGuestUser;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getDn() {
        return dn.toString();
    }

    public void setDn(Name dn) {
        this.dn = dn;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOwner() {
        if (owner != null) {
            return owner.toString();
        } else {
            return null;
        }
    }

    public void setOwner(String owner) {
        if (owner != null) {
            this.owner = LdapNameBuilder.newInstance(owner).build();
        } else {
            this.owner = null;
        }
    }

    public boolean isLoginDisabled() {
        return loginDisabled;
    }

    public void setLoginDisabled(boolean loginDisabled) {
        this.loginDisabled = loginDisabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhysicalVisitLocation() {
        return physicalVisitLocation;
    }

    public void setPhysicalVisitLocation(String physicalVisitLocation) {
        this.physicalVisitLocation = physicalVisitLocation;
    }
}
