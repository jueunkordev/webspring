package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Repository("macbook_member_DTO")
public class macbook_member_DTO {
	int midx;
	String mid, mname, memail;
}
