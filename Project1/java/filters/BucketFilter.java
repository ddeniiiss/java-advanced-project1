package lesson10.filters;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import lesson10.domain.UserRole;
import lesson10.shared.FilterService;

@WebFilter("/bucket.jsp")
public class BucketFilter implements Filter {
	FilterService filterService = FilterService.getFilterService();

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		filterService.doFilterValidation(request, response, chain, Arrays.asList(UserRole.USER));
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
