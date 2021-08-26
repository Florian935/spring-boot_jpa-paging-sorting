package com.florian935.pagingsorting;

import com.florian935.pagingsorting.domain.Employee;
import com.florian935.pagingsorting.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static lombok.AccessLevel.PRIVATE;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PagingSortingApplication {

	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(PagingSortingApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	void initDb() {
//
//		final List<Employee> employees = generateRandomEmployee();
//
//		employeeRepository.saveAll(employees);
//	}
//
//	private List<Employee> generateRandomEmployee() {
//
//		final List<Employee> employees = new ArrayList<>();
//		final Random random = new Random();
//
//		for (int i = 1; i <= 200; i++) {
//
//			employees.add(new Employee(i, employeesName().get(random.nextInt(8)), random.nextInt(40000)));
//		}
//
//		return employees;
//	}
//
//	private List<String> employeesName() {
//
//		return List.of("Sam", "Flo", "Toto", "Lindsay", "Stephane", "Olivier", "Jean", "Paul");
//	}
}
