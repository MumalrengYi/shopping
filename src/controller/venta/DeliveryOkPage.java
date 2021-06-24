package controller.venta;

import model.DAO.SalesDAO;
import model.DTO.DeliveryDTO;

import javax.servlet.http.HttpServletRequest;

public class DeliveryOkPage {
    public void execute(HttpServletRequest request) {
        DeliveryDTO dto = new DeliveryDTO();
        dto.setArrivalExpDate(request.getParameter("arrivalExpDate"));
        dto.setDeliveryCom(request.getParameter("deliveryCom"));
        dto.setDeliveryDelFree(request.getParameter("deliveryDelFree"));
        dto.setDeliveryExpDate(request.getParameter("deliveryExpDate"));
        dto.setDeliveryNum(request.getParameter("deliveryNum"));
        ;
        dto.setPurchaseNum(request.getParameter("purchaseNum"));
        SalesDAO dao = new SalesDAO();
        dao.deliveryCreate(dto);
    }
}
