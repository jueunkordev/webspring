package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//java개발자는 DAO, DTO(setter,getter), VO(getter) 필수 

//DAO : 데이터를 Access를 하는 역할 
@Repository("macbook_DAO") // @Repository : Model을 Controller에 호출시킴
public class macbook_DAO implements macbook_mapper { // @Mapper을 interface로드하여 DAO 작성

	// mybatis로 db연결
	@Resource(name = "template")
	public SqlSessionTemplate st;

	// 데이터 수정 메소드
	@Override
	public int macbook_update(macbook_DTO dto) {
		int result = this.st.update("macbook_update", dto);
		return result;
	}

	// 하나의 데이터만 가져오는 메소드
	@Override
	public macbook_DTO macbook_one(String midx) {
		// setter 형태로 DB에 있는 데이터를 이관
		// selectOne("mapper.xml에서 사용하는 id명", 매개변수)
		macbook_DTO onedata = this.st.selectOne("macbook_one", midx); // mapper.xml 의 id
		return onedata;
	}

	@Override
	public List<macbook_DTO> macbook_select() {
		// selectOne : 데이터 한개만 가져올 때 (List배열, ArrayList, Map, DTO 등등 다 가능)
		// selectList : 데이터 여러개를 가져올 때 (List배열로 가져옴)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");

		return classList;
	}
	
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = this.st.insert("macbook_insert", dto); // st. 컨스페 > sqlsesstion template용 선택
		return result;
	}

	@Override
	public int macbook_delete(int midx) {
		int result = this.st.delete("macbook_delete",midx);
		return result;
	}
}