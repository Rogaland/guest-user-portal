package main.java.no.rogfk.guestuser.service;

import main.java.no.rogfk.guestuser.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchControls;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class EmployeeService {

    @Autowired
    LdapTemplate ldapTemplate;

    private SearchControls searchControls;


    public EmployeeService() {
        searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
    }

    public List<Employee> search(String searchString) {

        return ldapTemplate.find(query().base("ou=ansatt,ou=personer,o=rfk-meta")
                        .where("fullname")
                        .like(toLikeString(searchString))
                        .and("brfkActiveStatusLevel")
                        .is("ON")
                        .and("cn")
                        .not().like("*_*"),
                Employee.class
        );
    }

    private String toLikeString(String string) {
        string = String.format("*%s*", string);
        string = string.replace(" ", "*");

        return string;
    }
}
