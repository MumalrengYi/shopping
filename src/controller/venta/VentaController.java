package controller.venta;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class VentaController extends HttpServlet implements Servlet {
    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI(); //주소를 알기 위해서는 request.URI가 있어야함
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length()); // URI를 contextPath의 길이만큼 잘라낸다.
//      ////      ////      ////      ////      ////      ////      ////      ////      ////      ////      //
    if(command.equals("/venta.vnt")){

        ClientSalesPage action = new ClientSalesPage();
        action.clientSales(request);
        // ▼▼▼▼▼로 request를 받아서 전달 ▼▼▼▼▼▼▼
        RequestDispatcher dispatcher = request.getRequestDispatcher("sales/venta.jsp");
        dispatcher.forward(request, response);
    }







    } //doProcess
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }
}
