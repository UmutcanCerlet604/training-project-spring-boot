package trainingproject.northwind.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingproject.northwind.business.abstracts.ProductService;
import trainingproject.northwind.dataAccess.abstracts.ProductDao;
import trainingproject.northwind.entities.concretes.Product;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        super();
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return this.productDao.findAll();
    }
}

/*
    Burada ne yaptık ?
    - ProductDao injectionu yaptık.
    - Burada bir sınıf yok.
    - Generic olarak çalışır.
    - Bizim verdiğimiz bilgilere göre spring arka tarafta repository sınıfı oluşturur. (Hibernate gibi kısacası bir instance oluşturur.)
    - Bir instance oluşturdu vermek istiyor ama o instance'ı buraya vereceğine dair bir bilgi yok.
    - İşte bunu @Autowired anatasyonu ile sağlarız.
    - Autowired spring'den gelir. Javada bean görürsek proje classı demektir.
    - Factory - Dependency injection factory pattern tasarım deseni adlı bir yapıyı çalıştırır. Kısacası instance üretir.
    - Autowired kullandığımızda spring arka planda buna karşılık gelebilecek ProductDao'nun instance'ı olabilecek bir tane sınıfı üretir ve verir.
    - Java dünyasında çok popülerdir. Bu anatasyon genellikle bir bağımlılık oluşturur. O projede sadece tek bir instance'tan gidebiliriz.
    - @Autowired anatasyonu sayesinde spring projeyi tarar. Projede ProductDao'ya karşılık gelen bir sınıf varsa onu yerleştiriyor.
    - @Service - Bu anatasyonu kullandığımızda, kullandığımız sınıfın bu projede bir servis görevi göreceğinden bahsederek springe bilgi veriyoruz.

    - Autowired anatasyonunu constructor üzerine yazarak spring'in onun karşılığı olan sınıfı bulmasını sağlıyoruz.
    - Yani biz new ile kendimiz oluşturmadık.
*/