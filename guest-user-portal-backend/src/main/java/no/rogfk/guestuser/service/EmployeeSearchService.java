package no.rogfk.guestuser.service;

import no.rogfk.guestuser.model.EmployeeSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchControls;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class EmployeeSearchService {

    @Autowired
    LdapTemplate ldapTemplate;

    private SearchControls searchControls;


    public EmployeeSearchService() {
        searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
    }

    public List<EmployeeSearch> search(String searchString) {

        return ldapTemplate.find(query().base("ou=ansatt,ou=personer,o=rfk-meta")
                        .where("fullname")
                        .like(toLikeString(searchString))
                        .and("brfkActiveStatusLevel")
                        .is("ON")
                        .and("cn")
                        .not().like("*_*"),
                EmployeeSearch.class
        );
    }

    private String toLikeString(String string) {
        string = String.format("*%s*", string);
        string = string.replace(" ", "*");

        return string;
    }
}
