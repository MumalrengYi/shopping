package controller.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.LoginDAO;
import model.DTO.AuthInfo;

public class LoginPage {
	public void login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		LoginDAO dao = new LoginDAO();
		AuthInfo authInfo = dao.login(userId);
		if(authInfo == null) {
			session.removeAttribute("pwFail");
			session.setAttribute("userFail", "아이디가 존재하지 않습니다.");
		}else {
			session.removeAttribute("userFail");
			if(userPw.equals(authInfo.getUserPw())) {
				session.removeAttribute("pwFail");
				/// 웹브라우저를 닫기 전까지 사용할 수 있도록 session에 저장
				session.setAttribute("authInfo", authInfo);

				//자동로그인에 대한 체크박스
				String autologin = request.getParameter("autologin");
				if(autologin != null && autologin.equals("auto")){
					Cookie cookie = new Cookie("autoLogin",userId);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);


				}

				//ID저장파트 추가
				//Cookie 만들어 달라 요청
				String idStore = request.getParameter("idStore");
				if(idStore != null && idStore.equals("store") ){ //굳이 왜 두개 다?
					//Cookie Making
					Cookie cookie = new Cookie("idStore",userId);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*365);

					// passing Cookies to WebBrowser(Client)
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("idStore",userId);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}else {
				session.setAttribute("pwFail", "비밀번호가 틀렸습니다.");
			}
		}
	}
}