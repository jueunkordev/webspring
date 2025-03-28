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
		System.out.println(this.callback);
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
		   System.out.println(total);
		   // 데이터 총 갯수 확인 코드 끝
	      
	      //검색기능
	      List<banner_DTO> all = null;
	      //System.out.println(search);
	      if(search.intern() == "") { //검색어가 없을 경우
	         all = this.dao.all_banner( pageno); // 인자값 : 사용자가 페이지 번호를 클릭한 값을 전달
	      }else {//검색어가 있을경우
	         all = this.dao.search_banner(search);
	      }
	      m.addAttribute("search",search);
	      m.addAttribute("all",all);
	      
	      return null;
	   }   
	
}
