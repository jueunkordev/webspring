package spring_learning;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface macbook_mapper {
	public int macbook_insert(macbook_DTO dto); // 신규 데이터 입력
	List<macbook_DTO> macbook_select(); // 전체데이터
	macbook_DTO macbook_one(String midx); // 하나의 데이터만 가져옴
	public int macbook_update(macbook_DTO dto);	//과정 수정
	public int macbook_delete(int midx); // 삭제
}