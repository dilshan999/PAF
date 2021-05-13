package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ProjectsAPI
 */
@WebServlet("/ProjectsAPI")
public class ProjectsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  Projects projectObj = new Projects();
  
    public ProjectsAPI() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = projectObj.addProjects(request.getParameter("projectName"),
				request.getParameter("researcherID"),
				request.getParameter("description"),
				request.getParameter("price"),
				request.getParameter("email"),
				request.getParameter("phone"));
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
		
		String output = projectObj.updateProjects(paras.get("hidProjectIDSave").toString(),
		paras.get("projectName").toString(),
		paras.get("researcherID").toString(),
		paras.get("description").toString(),
		paras.get("price").toString(),
		paras.get("email").toString(),
		paras.get("phone").toString());

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = projectObj.deleteProjects(paras.get("projectID").toString());
		
		response.getWriter().write(output);
	}

}
