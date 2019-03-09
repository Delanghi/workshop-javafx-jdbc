package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll() {							// MÉTODO que vai retornar uma lista de depto
		return dao.findAll();									// este vai buscar os dados no SQL 
	}
}