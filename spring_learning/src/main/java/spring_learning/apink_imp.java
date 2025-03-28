package spring_learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Service : Controller에서 받은 값을 Mapper로 전달하고
// Mapper에서 작업된 값을 다시 받아서 Controller (xml과 java 사이?)
/*@Service
public class apink_imp implements apink_service {

	@Autowired
	public apink_service apink_service;
	
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = apink_service.macbook_insert(dto);
		return 0;
	}
}
*/