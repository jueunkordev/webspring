package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("banner_DAO")
public class banner_DAO {
	
	@Resource(name="template")
	public SqlSessionTemplate st;
	
	// 배너명으로 검색된 데이터를 가져오는 메소드 (DAO)
	public List<banner_DTO> search_banner(String search){
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_search",search);
		return all;
	}
	
	// 배너 전체 데이터
	public List<banner_DTO> all_banner(){
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_all");
		return all;
	}
	
	// 신규 배너 등록 메소드
	public int new_banner(banner_DTO dto) {
		int result = this.st.insert("macbook_user.banner_new",dto);
		return result;
	}
}
