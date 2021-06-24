package controller.venta;

import model.DAO.SalesDAO;
import model.DTO.ClientSalesDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientSalesPage {
    public void clientSales(HttpServletRequest request){
        String memId = request.getParameter("memId");
        SalesDAO dao = new SalesDAO();
        List<ClientSalesDTO> list = dao.salesList();
        request.setAttribute("list", list);
    }
}
