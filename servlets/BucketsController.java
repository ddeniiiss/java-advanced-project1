package lesson10.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lesson10.domain.Bucket;
import lesson10.domain.Product;
import lesson10.dto.BucketDto;
import lesson10.service.BucketService;
import lesson10.service.ProductService;
import lesson10.service.impl.BucketServiceImpl;
import lesson10.service.impl.ProductServiceImpl;

@WebServlet("/buckets")
public class BucketsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BucketService bucketService = BucketServiceImpl.getBucketService();
	private ProductService productService = ProductServiceImpl.getProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("userId") == null) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
	        return;
	    }

	    Integer userId = (Integer) session.getAttribute("userId");

	    List<Bucket> buckets = bucketService.readByUserId(userId);
		Map<Integer, Product> idToProduct = productService.readAllMap();
		List<BucketDto> listOfBucketDtos = map(buckets, idToProduct);
		
		String json = new Gson().toJson(listOfBucketDtos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	public List<BucketDto> map(List<Bucket> buckets, Map<Integer, Product> idToProduct){
		return	buckets.stream().map(bucket->{
			BucketDto bucketDto = new BucketDto();
			bucketDto.bucketId = bucket.getId();
			bucketDto.purchaseDate = bucket.getPurchaseDate();
		   
			Product product = idToProduct.get(bucket.getProductId());
		    bucketDto.name = product.getName();
		    bucketDto.description = product.getDescription();
		    bucketDto.price = product.getPrice();
		    bucketDto.productId = product.getId();
			
			return bucketDto;
		}).collect(Collectors.toList());
	}
}
