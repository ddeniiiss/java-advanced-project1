package lesson10.shared;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lesson10.domain.UserRole;

public class FilterService {
	private static FilterService filterService;

	private FilterService() {
	}

	public static FilterService getFilterService() {
		if (filterService == null) {
			filterService = new FilterService();
		}
		return filterService;
	}

	public void doFilterValidation(ServletRequest request, ServletResponse response, FilterChain chain,
			List<UserRole> userRole) throws IOException, ServletException{
		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			UserRole role = UserRole.valueOf((String) session.getAttribute("role"));
			
			if(role != null && userRole.contains(role)) {
				chain.doFilter(request, response);
			}else {
				((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);
			}
		}catch (Exception e){
			((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}