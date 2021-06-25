package controller.member;

import model.DAO.MemberDAO;
import model.DTO.MemberDTO;

import javax.servlet.http.HttpServletRequest;

public class IdSearchPage {
    public void idFind(HttpServletRequest request){
        MemberDTO dto = new MemberDTO();
        dto.setMemAccount(request.getParameter("memAddress"));
        dto.setMemEmail(request.getParameter("memEmail"));
        dto.setMemPhone(request.getParameter("memPhone"));
        MemberDAO dao = new MemberDAO();
        dao.idFind(dto);

        if(dto.getMemId() == null){
            request.setAttribute("idFail","정보가 일치하지 않습니다.");
        }else{
            request.setAttribute("dto",dto);
        }
    }
}
