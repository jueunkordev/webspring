package spring_learning;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class macbook {
	/*
	 * //xml정보 끌고오는 애들 Autowired, Inject
	 * 
	 * @Autowired @Inject : 의존성 주입 XML=>Java, Java=>XML
	 * 
	 * @Autowired SqlSessionFactory sqlfact;
	 */

	/*
	 * // ibatis로 연결
	 * 
	 * @Inject SqlSessionFactory sqlfact;
	 */

	/*
	 * 컨트롤러의 리소스 네임과 DAO의 레포지토리 이름이 같으면 됨 !
	 */
	// @Resource : new 클래스명 호출과 동일하게 작동, @Repository의 이름을 가져오는 역할
	// @Resource와 @Repository는 영혼의 단짝친구
	@Resource(name = "macbook_DAO") // resource는 new의 역할
	private macbook_DAO dao; // 하나의 모델인데 new 안써도 됨

	PrintWriter pw = null;
	
	// 그럼 이렇게 DTO를 쓰면 this.를 이용해서 DTO를 사용 가능하게됨 !
	// 대신 매개변수와 변수 이름이 같으면 에러가 날 수도 있음 주의 !!
	@Resource(name = "macbook_DTO")
	private macbook_DTO mdto;

	// 과정 리스트 출력
	@GetMapping("/macbook_list.do")
	public String macbook_list(Model m) {
		// List<macbook_DTO> : DTO 형태의 배열로 생성하여, JSP로 전달
		List<macbook_DTO> classList = this.dao.macbook_select();
		// System.out.println(classList.get(0).class_name);
		m.addAttribute("ea", classList.size());
		m.addAttribute("classList", classList);
		return null;
	}

	// 개설된 과정을 삭제하는 메소드

	// 과정개설 메소드
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception {
		try {
			// dao : 쿼리문을 작동시키는 놈
			int result = this.dao.macbook_insert(dto);
			String msg = "";
			if (result > 0) {
				msg = "alert('과정이 올바르게 개설되었습니다.');" + "location.href='macbook_list.do';";
			}
			m.addAttribute("msg", msg);
		} catch (Exception e) {
		}
		return "load";
	}

	// 과정 수정 페이지(출력)
	@PostMapping("/macbook_modify.do")
	public String macbook_modify(@RequestParam("midx") String midx, Model m) {
		// System.out.println(midx);
		// DTO의 setter에 값을 이관한 상황
		macbook_DTO onedata = this.dao.macbook_one(midx); // DTO의 getter 메소드를 호출
		m.addAttribute("onedata", onedata); // JSTL로 값을 이관함
		// System.out.println(onedata.class_name); // DTO의 getter 메소드를 호출
		// System.out.println(onedata.get(0).class_name);
		return null;
	}

	// 수정된 값으로 업데이트
	@PostMapping("/macbook_modifyok.do")
	public String macbook_modifyok(macbook_DTO dto, Model m) {
		// insert, update, delete 무조건 결과를 int로 받음
		int result = this.dao.macbook_update(dto); // DAO로 값 전송
		String msg = "";
		System.out.println(result);
		if (result > 0) {
			msg = "alert('정상적으로 데이터가 수정되었습니다.'); location.href='./macbook_list.do';";
		}
		m.addAttribute("msg", msg);
		return "load";
	}
	
	// 개설된 과정을 삭제하는 메소드
		@PostMapping("/macbook_delete.do")
		public String macbook_delete(@RequestParam("midx") String midx, Model m, HttpServletResponse res) throws Exception {
			// Model과 HttpServletResponse은 함께 사용하지 못합니다 두 개의 interface의 역할이 같으므로 하나만 사용이 가능합니다.
			res.setContentType("text/html; charset=utf-8");
			this.pw = res.getWriter();
			int result = this.dao.macbook_delete(Integer.parseInt(midx));
			if(result > 0) {
				this.pw.print("<script>alert('올바르게 해당 과정을 삭제하였습니다.');location.href='./macbook_list.do';</script>");
			}
			this.pw.close();
			return null;
		}
	
}