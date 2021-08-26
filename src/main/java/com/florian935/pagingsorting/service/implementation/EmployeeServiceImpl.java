package com.florian935.pagingsorting.service.implementation;

import com.florian935.pagingsorting.domain.CustomOrder;
import com.florian935.pagingsorting.domain.Employee;
import com.florian935.pagingsorting.repository.EmployeeRepository;
import com.florian935.pagingsorting.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.domain.Sort.by;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {

        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmployeesWithSortingByField(String field, String direction) {

        final Direction sortingDirection = getDirectionFromString(direction);

        return employeeRepository.findAll(by(sortingDirection, field));
    }

    @Override
    public Page<Employee> findEmployeesWithPagination(int page, int pageSize) {

        return employeeRepository.findAll(of(page, pageSize));
    }

    @Override
    public Page<Employee> findEmployeesWithPaginationAndSorting(int page,
                                                                int pageSize,
                                                                String field,
                                                                String direction) {

        final Direction sortingDirection = getDirectionFromString(direction);

        return employeeRepository.findAll(of(page, pageSize).withSort(sortingDirection, field));
    }

    @Override
    public Page<Employee> findEmployeesWithPaginationAndMultipleSorting(int page,
                                                                        int pageSize,
                                                                        List<CustomOrder> customOrders) {

        final List<Order> orders = convertCustomOrderToOrder(customOrders);

        return employeeRepository.findAll(of(page, pageSize).withSort(by(orders)));
    }

    private Direction getDirectionFromString(String direction) {

        return direction.toLowerCase(Locale.ROOT).equals("asc") ? ASC : DESC;
    }

    private List<Order> convertCustomOrderToOrder(List<CustomOrder> customOrders) {

        return customOrders
                .stream()
                .map(order -> {
                    Direction direction =
                            order.getDirection().toUpperCase(Locale.ROOT).equals("ASC") ? ASC : DESC;

                    return new Order(direction, order.getProperty());
                })
                .toList();
    }
}
