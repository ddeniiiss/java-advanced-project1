package lesson10.servlets;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson10.domain.Product;
import lesson10.service.ProductService;
import lesson10.service.impl.ProductServiceImpl;

@WebServlet("/products")
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = ProductServiceImpl.getProductService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = productService.readAll();
		
		String json = new Gson().toJson(products);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
