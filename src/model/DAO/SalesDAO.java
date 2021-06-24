package model.DAO;

import model.DTO.ClientSaleDTO;
import model.DTO.CustomerTotalDTO;
import model.DTO.DeliveryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO extends DataBaseInfo {


    public void deliveryCreate(DeliveryDTO dto){
        String delFree = "SELECT SUM(PROD_DEL_FEE) " +
                " FROM PURCHASE_LIST PL, PRODUCTS PR" +
                " WHERE PL.PROD_NUM = PR.PROD_NUM" +
                " AND PL.PURCHASE_NUM = ? ";

        sql = "MERGE INTO DELIVERY d " +
                "USING (SELECT PURCHASE_NUM FROM PURCHASE " +
                "WHERE d.PURCHASE_NUM = '?') p " +
                "ON (d.PURCHASE_NUM = p.PURCHASE_NUM) " +
                "when matched then update " +
                "set DELIVERY_COM = ? , DELIVERY_NUM = ? , DELIVERY_EXP_DATE = ? ," +
                " DELIVERY_DEL_FREE = ("+delFree+") " +
                "when not matched then " +
                "INSERT (DELIVERY_COM, DELIVERY_NUM, " +
                "DELIVERY_EXP_DATE, ARRIVAL_EXP_DATE, " +
                "DELIVERY_DEL_FREE,PURCHASE_NUM) VALUES ( ?,?,?,?,("+delFree+"),? )";

        getConnect();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dto.getPurchaseNum());
            pstmt.setString(2,dto.getDeliveryCom());
            pstmt.setString(3,dto.getDeliveryNum());
            pstmt.setString(4,dto.getDeliveryExpDate());
            pstmt.setString(5,dto.getArrivalExpDate());
            pstmt.setString(6,dto.getPurchaseNum());
            pstmt.setString(7,dto.getDeliveryCom());
            pstmt.setString(8,dto.getDeliveryNum());
            pstmt.setString(9,dto.getDeliveryExpDate());
            pstmt.setString(10,dto.getArrivalExpDate());
            pstmt.setString(11,dto.getPurchaseNum());
            pstmt.setString(12,dto.getPurchaseNum());
            int i = pstmt.executeUpdate();
            System.out.println(i+"개가 입력되었습니다. (deliveryCreate)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
    }
    public DeliveryDTO deliverySelect(String purchaseNum){
        DeliveryDTO dto = null;

        sql = "SELECT PURCHASE_NUM, DELIVERY_COM, DELIVERY_NUM, DELIVERY_EXP_DATE, ARRIVAL_EXP_DATE, DELIVERY_DEL_FREE " +
                "FROM DELIVERY " +
                "WHERE PURCHASE_NUM = ?";

        getConnect();

        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, purchaseNum);
            if(rs.next()){ //존재하는 값일수도 없는 값을수도 있기 때문에..
                //만약 값이 존재하면..
                dto = new DeliveryDTO();

                dto.setPurchaseNum(rs.getString(1));
                dto.setDeliveryCom(rs.getString(2));
                dto.setDeliveryNum(rs.getString(3));
                dto.setDeliveryExpDate(rs.getString(4));
                dto.setArrivalExpDate(rs.getString(5));
                dto.setDeliveryDelFree(rs.getString(6));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
    }

    public List<CustomerTotalDTO> customerTotal(){
        List<CustomerTotalDTO> list = new ArrayList<>();
        sql =" select  m.mem_Id, mem_name , sum(purchase_tot_price),"
                +"	count(*), avg(purchase_tot_price)"
                +"    from member m, purchase pu"
                +"    where m.mem_id = pu.mem_id"
                +"    group by m.mem_Id, m.mem_name";
        getConnect();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                CustomerTotalDTO dto = new CustomerTotalDTO();
                dto.setCount(rs.getString(4));
                dto.setMemId(rs.getString(1));
                dto.setMemName(rs.getString(2));
                dto.setSumPrice(rs.getString(3));
                dto.setAvg(rs.getString(5));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return list;
    }
    public List<ClientSaleDTO> salesList(String memId){
        System.out.println("memId: " + memId);
        List<ClientSaleDTO> list = new ArrayList<>();
        sql = "select m.mem_id, mem_name, mem_phone,"
                +"    pr.prod_num, prod_name,"
                +"    pu.purchase_num, purchase_date, PURCHASE_ADDR ,"
                +"    RECEIVER_NAME , RECEIVER_PHONE, "
                +"    PURCHASE_QTY ,  PURCHASE_PRICE, DELIVERY_NUM "
                +"  from member m, purchase pu, products pr,  "
                +"	     purchase_list pl, DELIVERY d"
                +"  where m.mem_id(+) = pu.mem_id "
                +"    and pu.purchase_num = pl.purchase_num"
                +"    and pl.prod_num = pr.prod_num"
                +" and PU.PURCHASE_NUM = D.PURCHASE_NUM(+)"; //D.PURCHASE_NUM(+) : 배송정보가 없을 때에는 NULL로 가져온다.

        if(memId != null) {
            sql += " and m.mem_id = ?";
        }
        System.out.println(sql);
        getConnect();
        try {
            pstmt = conn.prepareStatement(sql);
            if(memId != null) {
                pstmt.setString(1, memId);
            }
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ClientSaleDTO dto = new ClientSaleDTO();
                dto.setMemId(rs.getString("mem_Id"));
                dto.setMemName(rs.getString("mem_Name"));
                dto.setMemPhone(rs.getString("mem_Phone"));
                dto.setProdName(rs.getString("prod_Name"));
                dto.setProdNum(rs.getString("prod_Num"));
                dto.setPurchaseAddr(rs.getString("purchase_Addr"));
                dto.setPurchaseDate(rs.getDate("purchase_Date"));
                dto.setPurchaseNum(rs.getString("purchase_Num"));
                dto.setPurchasePrice(rs.getString("purchase_Price"));
                dto.setPurchaseQty(rs.getString("purchase_Qty"));
                dto.setReceiverName(rs.getString("receiver_Name"));
                dto.setReceiverPhone(rs.getString("receiver_Phone"));
                dto.setDeliveryNum(rs.getString("delivery_num")); // 추가
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return list;
    }
}