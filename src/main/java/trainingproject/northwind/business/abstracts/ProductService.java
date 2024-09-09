package trainingproject.northwind.business.abstracts;

import trainingproject.northwind.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
}

/*
    Business katmanında, abstract tarafında productService altında bizim controllerımızın kullanacağı operasyonlarımız yazdık.
    Bu katman bizim iş katmanımızdır. Burada sadece iş kodları yazılır. Kurallar burada yazılır.
    Mesela hepsiburada'dan örnek verelim. Kurum bir satıcının 100 tane ürün ekleyip yayınlayabileceğini söylesin.
    İşte bu kuralları yazdığımız yer business katmanıdır.
    Biz burada basit bir getAll() fonksiyonu yazdık.
    Bizim ürünlerimizin tamamını listeleyen operasyonun interface'dir.
    Bunu doldurmak gerekiyor. Bunu da ProductManager sınıfında yapıyoruz.

*/