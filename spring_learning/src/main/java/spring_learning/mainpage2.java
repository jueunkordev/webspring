package spring_learning;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;
import org.mybatis.logging.*;

@Controller
public class mainpage2 {
	// WEB-INF : Controller, Model이 접근할 수 있는 디렉토리
	// return 사용 시 WEB-INF/디렉토리명/파일명 형태로 구성하게 됩니다.
	// DTO로 Front-end의 값을 받을 수 있습니다. (lombok)
	// 별도의 값을 받아서 처리해야 할 경우는 Servlet 형태의 request로 받으면 됨
	// **** Front의 name값과 동일하게 DTO가 작성되어야 함
	// DTO 활용 : Front-end값 이관, Model에 값을 이관, Database에서 사용
	@GetMapping("/login.do")
	public String login(user_DTO dto, HttpServletRequest req, HttpServletResponse res, Model m) {
		String ck = req.getParameter("mcheck");
		System.out.println(ck);
		// model로 해당 jsp에 변수를 이관함 출력 jstl 변수선언으로 출력
		m.addAttribute("mid",dto.getMid());
		return "/admin/login";
		// Database + XML + Connection + Controller	
	}
	
	@Autowired
    BasicDataSource dbinfo;
	
	// Db Query문 작성 및 데이터를 가져오기 위한 interface
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
    //Database + XML + Connection + Controller
    @GetMapping("/event_list.do")
    public String event_list(HttpServletRequest req) {
       try {
    	   // db_config.xml에 있는 정보를 Connection으로 이관
    	   this.con = this.dbinfo.getConnection();
    	   
    	   String sql = "select * from event order by eidx desc";
    	   this.ps = this.con.prepareStatement(sql);
    	   this.rs = this.ps.executeQuery();
    	   req.setAttribute("rs", this.rs);
    	   // 단점 : this.ps, this.rs를 clse하지 못함
    	   
	} catch (Exception e) {
		
	}
       return null;
   } 
    // RequestMapping : GET,POST,PUT.... 모든 통신을 다 받을 수 있음 (기본)
    // method 속성 : 통신방법 (Front-end 데이터 이관 방법)
    // value : 가상의 경로
    @RequestMapping(value="/event_infook.do",method=RequestMethod.POST)
    public String eventok(event_DTO dto) {
//		System.out.println(dto.getEmail());
//		System.out.println(dto.getEtel());
		try {
			this.con = this.dbinfo.getConnection();
			String sql = "insert into event values ('0',?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dto.getEname());
			this.ps.setString(2, dto.getEtel());
			this.ps.setString(3, dto.getEmail());
			this.ps.setString(4, dto.getInfo1());
			this.ps.setString(5, dto.getInfo2());
			this.ps.setString(6, dto.getEmemo());
			int result = this.ps.executeUpdate();
			System.out.println(result);
			
		}catch (Exception e) {
			
		}finally {
			try {
				this.ps.close();
			}catch (Exception e) {
				
			}
		}
		return null;
	}
}












