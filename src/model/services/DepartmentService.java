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
	
 // MÉTODO 
	public void saveOrUpdate(Department obj) {
		if(obj.getId() == null) {								// se qdo buscar o número ID, este número ñ existir, ou seja, igual a zero
			dao.insert(obj);									// então o programa irá criar/insrir um número ID para este "obj"
		}
		else {
			dao.update(obj);									// caso contrário, se o ID existir, será apenas feito o update
		}
	}
	
 	public void remove (Department obj) {						// MÉTODO do botão "remove" da janela do depto.
 		dao.deleteById(obj.getId());
 	}
	
}