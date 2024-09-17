import com.example.shopdemo.dto.response.RespProduct;
import com.example.shopdemo.dto.response.Response;
import com.example.shopdemo.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;



import java.util.List;

public class ProductServiceTest {


    @Test
    public void testGetProduct(){
        ProductServiceImpl productService=new ProductServiceImpl();
        Response<List<RespProduct>> response= productService.getProductList(null);
       Assertions.assertEquals(2,response.getT().size(),"user size must be 2");
    }
}
