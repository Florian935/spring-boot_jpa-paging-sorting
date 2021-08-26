package com.florian935.pagingsorting.controller;

import com.florian935.pagingsorting.domain.CustomOrder;
import com.florian935.pagingsorting.domain.Employee;
import com.florian935.pagingsorting.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/v1.0/employees")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<Employee> getAll() {

        return employeeService.getAll();
    }

    @GetMapping(path = "/{field}/{direction}", produces = APPLICATION_JSON_VALUE)
    List<Employee> getAllSortedBy(@PathVariable String field,
                                  @PathVariable String direction) {

        return employeeService.findEmployeesWithSortingByField(field, direction);
    }

    @GetMapping(path = "/pagination/{page}/{pageSize}")
    Page<Employee> getAllWithPagination(@PathVariable Integer page,
                                        @PathVariable Integer pageSize) {

        return employeeService.findEmployeesWithPagination(page - 1, pageSize);
    }

    @GetMapping(
            path = "pagination-and-sort/{page}/{pageSize}/{field}/{direction}",
            produces = APPLICATION_JSON_VALUE)
    Page<Employee> getAllWithPaginationAndSorting(@PathVariable Integer page,
                                                  @PathVariable Integer pageSize,
                                                  @PathVariable String field,
                                                  @PathVariable String direction) {

        return employeeService
                .findEmployeesWithPaginationAndSorting(page - 1, pageSize, field, direction);
    }

    @GetMapping(
            path = "pagination-and-multiple-sort/{page}/{pageSize}",
            produces = APPLICATION_JSON_VALUE)
    Page<Employee> getAllWithPaginationAndSorting(@PathVariable Integer page,
                                                  @PathVariable Integer pageSize,
                                                  @RequestBody List<CustomOrder> orders) {

        return employeeService
                .findEmployeesWithPaginationAndMultipleSorting(page - 1, pageSize, orders);
    }
}
