package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll() {							// M�TODO que vai retornar uma lista de depto
		return dao.findAll();									// este vai buscar os dados no SQL 
	}
	
 // M�TODO 
	public void saveOrUpdate(Department obj) {
		if(obj.getId() == null) {								// se qdo buscar o n�mero ID, este n�mero � existir, ou seja, igual a zero
			dao.insert(obj);									// ent�o o programa ir� criar/insrir um n�mero ID para este "obj"
		}
		else {
			dao.update(obj);									// caso contr�rio, se o ID existir, ser� apenas feito o update
		}
	}
	
 	public void remove (Department obj) {						// M�TODO do bot�o "remove" da janela do depto.
 		dao.deleteById(obj.getId());
 	}
	
}