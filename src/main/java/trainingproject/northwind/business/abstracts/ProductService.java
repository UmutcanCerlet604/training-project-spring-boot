package trainingproject.northwind.business.abstracts;

import org.springframework.data.jpa.repository.Query;
import trainingproject.northwind.core.utilities.results.DataResult;
import trainingproject.northwind.core.utilities.results.Result;
import trainingproject.northwind.entities.concretes.Product;

import java.util.List;

public interface ProductService {

    DataResult<List<Product>> getAll();
    DataResult<List<Product>> getAll(int pageNo, int pageSize);
    DataResult<List<Product>> getAllSorted();
    Result add(Product product);

    DataResult<Product> getByProductName(String productName);
    DataResult<Product> getByProductNameAndCategory_CategoryId(String productName, int categoryId);
    DataResult<List<Product>> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
    DataResult<List<Product>> getByCategory_CategoryIdIn(List<Integer> categories);
    DataResult<List<Product>> getByProductNameContains(String productName);
    DataResult<List<Product>> getByProductNameStartsWith(String productName);
    DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId);



}

/*
    Buradaki işlemi yaptıktan sonra herşey çok basit.
    ProductManager'da ProductDao'yu kullanacağız.
    ProductDao'yu SuccessDataResult'ın içine sarmallayıp sarmallayıp döndüreceğiz.
    Varsa iş kuralımız üstüne ekleyeceğiz.
*/

/*
    Business katmanında, abstract tarafında productService altında bizim controllerımızın kullanacağı operasyonlarımız yazdık.
    Bu katman bizim iş katmanımızdır. Burada sadece iş kodları yazılır. Kurallar burada yazılır.
    Mesela hepsiburada'dan örnek verelim. Kurum bir satıcının 100 tane ürün ekleyip yayınlayabileceğini söylesin.
    İşte bu kuralları yazdığımız yer business katmanıdır.
    Biz burada basit bir getAll() fonksiyonu yazdık.
    Bizim ürünlerimizin tamamını listeleyen operasyonun interface'dir.
    Bunu doldurmak gerekiyor. Bunu da ProductManager sınıfında yapıyoruz.

*/