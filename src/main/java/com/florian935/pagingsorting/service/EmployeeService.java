package com.florian935.pagingsorting.service;

import com.florian935.pagingsorting.domain.CustomOrder;
import com.florian935.pagingsorting.domain.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    List<Employee> findEmployeesWithSortingByField(String field, String direction);

    Page<Employee> findEmployeesWithPagination(int page, int pageSize);

    Page<Employee> findEmployeesWithPaginationAndSorting(int page,
                                                         int pageSize,
                                                         String field,
                                                         String direction);

    Page<Employee> findEmployeesWithPaginationAndMultipleSorting(int page,
                                                                 int pageSize,
                                                                 List<CustomOrder> orders);
}
