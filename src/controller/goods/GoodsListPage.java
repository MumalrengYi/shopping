package controller.goods;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.GoodsDAO;
import model.DAO.LoginDAO;
import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.ProductDTO;

public class GoodsListPage {
	public void goodsList(HttpServletRequest request) {
		GoodsDAO dao = new GoodsDAO();
		List<ProductDTO> list = dao.goodsList();
		request.setAttribute("lists", list);
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				//.startWith("id") -> 첫 글자가 "id"로 시작하는것. (첫글자만 비교)
				if(cookie.getName().equals("idStore")){
					request.setAttribute("isId",cookie.getValue());
				}
				if(cookie.getName().startsWith("au")){
					HttpSession session = request.getSession();
					LoginDAO ldao = new LoginDAO();
					String userId = cookie.getValue();
					AuthInfo authInfo = ldao.login(userId);
					session.setAttribute("authInfo",authInfo);

				}
			}
		}//if(cookies are exist)
	}
}
