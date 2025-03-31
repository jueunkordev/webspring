package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {
	List<String> listdata = null;
	Map<String, String> mapdata = null;
	PrintWriter pw = null;
	String result = null;
	int callback = 0;
	ModelAndView mv = null;
	
	@Resource(name="banner_DTO")
	banner_DTO dto;
	
	@Resource(name="banner_DAO")
	banner_DAO dao;
	
	@Resource(name="file_rename")
	file_rename fname; // 파일명을 개발자가 원하는 형태로 변경
	
	@PostMapping("/banner/bannerdel")
	public String bannerdel(@RequestParam(defaultValue = "", required = false) String ckdel, Model m) {
		this.callback = 0;
		String msg = "";
		if(ckdel.equals("")) {
			msg="alert('올바른 접근이 아닙니다.'); location.href='./bannerlist';";
		}
		else {
			String  no[] = ckdel.split(",");
			int w = 0;
			while (w < no.length) { // Front-end에 체크된 값만큼 반복
				int result = this.dao.banner_del(no[w]);
				if(result > 0) {
					this.callback++;
				}
				w++;
			}
			// -1 을 사용하는 이유 : 반복문에 조건이 없으므로 +1이 작동될 수 있음
			if(no.length == this.callback) {
				msg="alert('정상적으로 삭제되었습니다'); location.href='./bannerlist';";
			}
			else{
				msg="alert('비정상적인 데이터가 확인되었습니다'); location.href='./bannerlist';";
				System.out.println(no.length);
				System.out.println(this.callback);
			}
			
		}
		m.addAttribute("msg",msg);
		return "load";
	}
	
	
	// @ModelAttribute: 1:1 매칭 => name과 DTO 자료형 변수 이름이 같은 것이 있으면 setter로 값을 자동으로 넣어줌
	@PostMapping("/banner/bannerok")
	public String bannerok(@ModelAttribute(name = "dto") banner_DTO dto, MultipartFile bfile, HttpServletRequest req) throws Exception {
		
		// String file_new = '';
		//if(bfile.getOriginalFilename() != "") { // 파일명 여부로 검토
		
		String file_new = null; 
		if(bfile.getSize() > 0) { // 용량으로 검토
			
			// D:\webspring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_learning\
			
			// 웹 디렉토리 개발자가 생성한 파일명으로 저장하는 코드
			String url = req.getServletContext().getRealPath("/upload/");
			file_new = this.fname.rename(bfile.getOriginalFilename());
			FileCopyUtils.copy(bfile.getBytes(),new File(url + file_new));
			
			// 필드에 있는 DTO와 매개변수로 받은 DTO는 서로 다른 객체입니다
			// this.dto => 필드에 있는 DTO, dto => 매개변수로 받은 DTO
			
			dto.setFile_ori(bfile.getOriginalFilename()); // 원본 파일 명
			dto.setFile_new(file_new); // 변경한 파일명
			dto.setFile_url("/upload/"+file_new); // 웹 디렉터리 경로 + 파일명
			
		}
		this.callback = this.dao.new_banner(dto);
		return "banner/bannerlist";
	}
	
	//search 검색에 관련사항은 필수조건은 아니며, 또한 null처리가 되었을 경우 defaultValue
	   @GetMapping("/banner/bannerlist")
	   public String bannerlist(Model m,
	         @RequestParam(name = "search", defaultValue = "", required = false)String search,
	         @RequestParam(defaultValue = "1", required = false)Integer pageno
	         ) {
		   
		   // 데이터 총 갯수 확인 코드
		   int total = this.dao.banner_total();
		   // 데이터 총 갯수 확인 코드 끝
		   
		   int userpage = 0; // 사용자가 클릭한 페이지 번호에 맞는 순차번호 계산값
		   
		   if(pageno == 1) {
			   userpage = 0;
		   }
		   else { // 1외에 페이지 번호를 클릭 시
			   userpage = (pageno - 1) * 5;
		   }
		   
		   // 해당 일련번호를 계산하여 jsp에 전달
		   m.addAttribute("userpage",userpage);
		   
	      
	      //검색기능
	      List<banner_DTO> all = null;
	      //System.out.println(search);
	      if(search.intern() == "") { //검색어가 없을 경우
	         all = this.dao.all_banner( pageno); // 인자값 : 사용자가 페이지 번호를 클릭한 값을 전달
	      }else {//검색어가 있을경우
	         all = this.dao.search_banner(search);
	      }
	      
	      m.addAttribute("total",total); // 데이터 전체 갯수
	      m.addAttribute("search",search); // 검색어를 전달
	      m.addAttribute("all",all);
	      
	      return null;
	   }   
	
}
