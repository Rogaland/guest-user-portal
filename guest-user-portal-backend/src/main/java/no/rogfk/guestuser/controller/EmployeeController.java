package no.rogfk.guestuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import no.rogfk.guestuser.exception.QueryStringTooShort;
import no.rogfk.guestuser.model.EmployeeSearch;
import no.rogfk.guestuser.service.ConfigService;
import no.rogfk.guestuser.service.EmployeeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "EmployeeSearch")
@CrossOrigin()
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeSearchService employeeSearchService;

    @Autowired
    ConfigService configService;

    @ApiOperation("Search for employees")
    @RequestMapping(method = RequestMethod.GET)
    public List<EmployeeSearch> searchEmployee(@RequestParam("q") String queryString) {
        log.info("queryString: {}", queryString);

        if (queryString.length() > configService.getMinQueryLength()) {
            return employeeSearchService.search(queryString);
        }
        throw new QueryStringTooShort(
                String.format("Query string must be at least %s characters long.", configService.getMinQueryLength())
        );
    }

    @ExceptionHandler(QueryStringTooShort.class)
    public ResponseEntity handleQueryStringTooShort(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
