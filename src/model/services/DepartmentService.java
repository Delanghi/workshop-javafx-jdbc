package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	public List<Department> findAll() {							// MÉTODO que vai retornar uma lista de depto
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Books"));					// dados "MOCK"; fake, cadastrados manualmente como estes ao lado
		list.add(new Department(2, "Computers"));
		list.add(new Department(3, "Electronics"));
		return list;
	}
}