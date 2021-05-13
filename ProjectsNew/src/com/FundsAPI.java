package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FundsAPI
 */
@WebServlet("/FundsAPI")
public class FundsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  Funds fundObj = new Funds();
  
    public FundsAPI() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = fundObj.addFund(request.getParameter("admin_ID"),
				request.getParameter("product_ID"),
				request.getParameter("funds"));
				response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			
			scanner.close();
			
			String[] params = queryString.split("&");
			
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e)
		{
		}
			return map;
		}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = fundObj.updateFunds(paras.get("hidFundIDSave").toString(),
		paras.get("admin_ID").toString(),
		paras.get("product_ID").toString(),
		paras.get("funds").toString());

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = fundObj.deleteFunds(paras.get("fund_ID").toString());
		
		response.getWriter().write(output);
	}

}

