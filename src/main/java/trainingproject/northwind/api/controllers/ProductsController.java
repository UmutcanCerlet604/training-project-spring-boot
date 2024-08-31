package trainingproject.northwind.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trainingproject.northwind.business.abstracts.ProductService;
import trainingproject.northwind.entities.concretes.Product;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall")
    public List<Product> getAll(){
        return this.productService.getAll();
    }
}


/*
    Api isimlendirme kuralları - ProductsController
    Controller ne demek ?
    İki farklı dilde yazılmış uygulamanın anlaşabilmesi için API'ye ihtiyaçları var.
    Adı üstünde kontrolördür. Bir istek gelir. Controller isteği alır ve ne yapacağına karar verir.
    Bizim sistemimizin dış dünyayla iletişim kurduğu yerdir.

    Hepsiburadadan örnek verecek olursak;
    Ürün yönetimi için bir controller,
    Kategori yönetimi için bir controller,
    Sipariş yönetimi için bir controller,
    Sepet yönetimi için bir controller olabilir.

    Bunu @RequestMapping anatasyonu ile yaparız.
    // kodlama.io  -  Domain
    // kodlama.io/api/products
    Yukarıdaki gibi bir adresten bir istek geldi diyelim bunu karşılayacak olan ProductController'dır.
    Controller gelen isteği alır ve bir karar verir.
    Controller içinde istekleri karşılayacak bir metot desteği vermiş olduk.

    İstekler temel olarak iki türlüdür.
    1- Bana veriyi ver. (Get Request)
    2- Bu veriyi değiştir.

    Bunlara HTTP requestleri (istekleri) denir.
    Ben kodlama.io/api/products/getall türünde bir istek yollarsam buradaki metot çalışır.
    Şu an bir sistemin backendini yazıyoruz. Hepsiburada ve trendyolu düşünelim.
    Bu sistemlerin backendi bizim bu istekleri yapabilmemiz sağlayan araçlardan başka hiç birşey değil.
    Bir kere backend yapıyoruz. Birden fazla önyüz kullanabilir bizim backendimizi.

    @Autowired anatasyonu bütün projeyi tarar. Kim productService'i implemente etmiş onu buluyor.
    ProductService'i ProductManager'ın implemente ettiğini buluyor.
    Spring arka planda;
    ProductManager p = new ProductManager( new ProductDao());
    Newlenmiş p'yi geliyor buradaki productService'e yerleştiriyor.
    Bizim new() kullanmamıza gerek kalmıyor. Bu işlem IoC adı verilen bir yapılandırma ile kullanılıyor.
    Birden fazla somut sınıf kullanıldığında @Autowired patlar. SOLID zaafiyeti bu kısımda vardır.

    Postgre confirügasyonu dışında herhangi bir sorunumuz kalmadı.
    PostgreSQL configurasyonlarını yaptık ve başarılı bir şekilde bağlantı kurduk.
    javax.persistence yerine artık jakarta.persistence kullanılıyor.
    Import ettiğim paketleri tekrar değiştirdim ve sorunsuz bir şekilde projeyi ayağa kaldırdım.

    Bu sınıf isteği karşılayan yerdir.
    Bu sınıfa sen bir RestController'sın dedik.
    Yani hem android hem de ios sana istekte bulunabilir.
    Yani restful çalışacaksın demektir bu ifade.
    Domainin sonuna (diyelim ki kodlama.io) /api/products adresinden bir istek gelirse sen karşıla dedik.
    Aynı autowired çözümlemesini burada da yaptık. /api/products/getall isteği geldiğinde bu metot çalışacaktır.

    Bir sonraki derste standart bir servis dönüşümü (result mekanizması) kuruyor olacağız.
*/