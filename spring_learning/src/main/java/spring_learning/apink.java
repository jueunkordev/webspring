package spring_learning;

import org.apache.ibatis.annotations.Mapper;
// @Mapper : mapper.xml에 있는 namespace 연동, 메소드를 실행시키는 id와 연동
// sql query문을 실행시킬 수 있도록 적용된 interface
@Mapper
public interface apink {
	public int macbook_insert(macbook_DTO dto);
}
