package spring_learning;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

// I/O Controller
@Controller
public class mainpage3 {
	
	//jstl로 로드 후 값 전달
   @GetMapping("/jstl/jstl6.do")
   public String jstl6(Model m) {
	   // Model을 이용하여 jstl6.jsp로 값을 전달합니다.
	   // 출력 top.jsp에서 ${} 변수를 출력함
      String level = "일반수강생";
      m.addAttribute("level",level);
      String corp = "(주)중앙정보처리학원";
      String tel = "02-1234-5678";
      m.addAttribute("level",level);
      m.addAttribute("corp",corp);
      m.addAttribute("tel",tel);
      return null;
   }
	
	// MultipartFile : Spring
	@PostMapping("/fileok.do")
	public String fileupload(MultipartFile mfile) {
		if(mfile.getSize() > 2097152) {
			System.out.println("test");
		}
		System.out.println(mfile.getOriginalFilename());
		return "load";
	}
	// 여러개의 첨부파일을 받는 메소드
	/*
	MultipartFile[] : Interface로 파일을 Front-end에서 받을 경우
	반복문으로 처리시 multiple로 전송할 경우는 별도의 조건문 없이 저장이 가능
	단, 같은 name으로 여러개의 파일전송 속성을 사용하였을 경우 반복문 사용시
	조건문이 없을 경우 500 에러로 인하여 문제 발생함 
	FileCopyUtils.copy : 파일 전송 관련 I/O 이며, Spring, Spring-boot
	 */
	@PostMapping("/fileok2.do")
	public String fileupok(MultipartFile[] mfile, HttpServletRequest req) throws IOException {
		String url =req.getServletContext().getRealPath("/upload/");
		System.out.println(url);
		System.out.println(mfile[0].getOriginalFilename());
		System.out.println(mfile[1].getOriginalFilename());
		int w = 0;
		while (w < mfile.length) {
			FileCopyUtils.copy(mfile[w].getBytes(), new File(url + mfile[w].getOriginalFilename())); 
			w++;
			
			
		}
		return "load";
	}
	// 웹디렉토리에 있는 파일 리스트를 출력하는 Controller
	@GetMapping("/filelist.do")
	public String filelist(HttpServletRequest req) throws Exception{
		// 웹 디렉토리
		String url =req.getServletContext().getRealPath("/upload/");
		// 웹 디렉토리에 저장되어 있는 모든 파일명
		File f = new File(url);
		String f_list[] = f.list();
		ArrayList<String> filenm = new ArrayList<String>(Arrays.asList(f_list));
		req.setAttribute("filenm", filenm);
		System.out.println(filenm);
		return null;
	}
	
	// @RequestParam  :Front-end 전달된 값 request.getParameter()
	@PostMapping("/filedel.do")
	public String filedel(@RequestParam("fnm") String filenm, HttpServletRequest req, Model m) throws Exception {
		String url =req.getServletContext().getRealPath("/upload/");
		System.out.println(url);
		System.out.println(filenm);
		File f = new File(url+filenm);
		f.delete(); // 파일 삭제 메소드
		String msg = "alert('정상적으로 삭제 되었습니다.'); location.href='./filelist.do';";
		m.addAttribute("msg",msg);
		
		return "load";
	}
}






