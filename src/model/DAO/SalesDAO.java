package model.DAO;

import model.DTO.ClientSalesDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO extends DataBaseInfo{

    public List<ClientSalesDTO> salesList(){
        List<ClientSalesDTO> list = new ArrayList<ClientSalesDTO>();

        sql=
        "SELECT M.MEM_ID, MEM_NAME, MEM_PHONE, PR.PROD_NUM, PROD_NAME, PU.PURCHASE_NUM, PURCHASE_DATE,"+
                " PURCHASE_ADDR, RECEIVER_NAME, RECEIVER_PHONE, PURCHASE_QTY, PURCHASE_PRICE"+
        " FROM MEMBER M,PURCHASE PU,PRODUCTS PR,PURCHASE_LIST PL"+
        " WHERE M.MEM_ID(+) = PU.MEM_ID"+
        " AND   PU.PURCHASE_NUM = PL.PURCHASE_NUM"+
        " AND   PL.PROD_NUM = PR.PROD_NUM";

        getConnect();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                ClientSalesDTO dto = new ClientSalesDTO();

                dto.setMemId(rs.getString("mem_id"));
                dto.setMemName(rs.getString("mem_name"));
                dto.setMemPhone(rs.getString("mem_phone"));
                dto.setProdName(rs.getString("prod_name"));
                dto.setProdNum(rs.getString("purchase_num"));
                dto.setPurchaseAddr(rs.getString("purchase_addr"));
                dto.setPurchaseDate(rs.getDate("purchase_date"));
                dto.setPurchasePrice(rs.getString("purchase_price"));
                dto.setPurchaseQty(rs.getString("purchase_qty"));
                dto.setReceiverName(rs.getString("receiver_name"));
                dto.setReceiverPhone(rs.getString("receiver_phone"));

                list.add(dto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
}
