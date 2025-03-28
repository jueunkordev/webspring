package spring_learning;

import java.util.List;

/*
Controller에서 호출해서 값을 받게하는 Interface
macbook_mapper.java 이 mapper를 그대로 싹 긁어오면 됨 
서비스를 컨트롤에서 로드하기 위해서 사용 
*/
public interface macbook_Service {

	public int macbook_insert(macbook_DTO dto);

	List<macbook_DTO> macbook_select();

	public int macbook_delete(int midx);
}