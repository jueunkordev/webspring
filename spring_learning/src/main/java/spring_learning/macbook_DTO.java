package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Repository("macbook_DTO")
public class macbook_DTO {
	int midx, class_day, class_price, class_sales;
	String class_part, class_cate, class_name, class_info, class_teacher, class_object, class_use, today;
}

//setter, getter 어노테이션, 임포트 잘하기
//int String 구분잘하기 => timestamp, enum 모두 String 