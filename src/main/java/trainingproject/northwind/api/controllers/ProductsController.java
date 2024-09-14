package trainingproject.northwind.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trainingproject.northwind.business.abstracts.ProductService;
import trainingproject.northwind.core.utilities.results.DataResult;
import trainingproject.northwind.core.utilities.results.Result;
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
    public DataResult<List<Product>> getAll(){
        return this.productService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Product product){
        return this.productService.add(product);
    }

    // Kullanıcıdan gelen bir istektir.
    // Endpointe bir istek gelecek ve bu istekte ürün ismi olacak.
    // @RequestParam; Yapılan istekte productName adında olan parametreyi okur ve atamasını yapar.
    @GetMapping("/getByProductName")
    public DataResult<Product> getByProductName(@RequestParam String productName){
        return this.productService.getByProductName(productName);
    }

    @GetMapping("/getByProductNameAndCategoryId")
    public DataResult<Product> getByProductNameAndCategoryId(@RequestParam String productName, @RequestParam int categoryId){
        return this.productService.getByProductNameAndCategory_CategoryId(productName,categoryId);
    }

    @GetMapping("/getByProductNameOrCategoryId")
    public DataResult<List<Product>> getByProductNameOrCategoryId(@RequestParam String productName, @RequestParam int categoryId){
        return this.productService.getByProductNameOrCategory_CategoryId(productName, categoryId);
    }

    @GetMapping("/getByCategoryCategoryIdIn")
    public DataResult<List<Product>> getByCategoryCategoryIdIn(@RequestParam List<Integer> categoryId){
        return this.productService.getByCategory_CategoryIdIn(categoryId);
    }

    @GetMapping("/getByProductNameContains")
    public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
        return this.productService.getByProductNameContains(productName);
    }

    @GetMapping("/getByProductNameStartsWith")
    public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName){
        return this.productService.getByProductNameStartsWith(productName);
    }

    @GetMapping("getByNameAndCategory")
    public DataResult<List<Product>> getByNameAndCategory(@RequestParam String productName, @RequestParam int categoryId){
        return this.productService.getByNameAndCategory(productName, categoryId);
    }

    @GetMapping("getAllByPage")
    public DataResult<List<Product>> getAll(int pageNo, int pageSize){
        return this.productService.getAll(pageNo, pageSize);
    }

    @GetMapping("/getAllDesc")
    public DataResult<List<Product>> getAllSorted(){
        return this.productService.getAllSorted();
    }

}
/*
    Bize veri başka ortamlarda yazılmış platformlardan gelir.(Örn; React)
    Post işlemi ile bir parametre aldığımızda aslında gönderen platform bizim Product datamızı (entityimizi) bilmez.
    React veriyi bize Json objesi şeklinde gönderir.
    Bu gelen veri aslında bizim için request body'dir.
    Bu şekilde gelen bir post işleminde bir parametre alırsak (genelde alırız) bunun başına @RequestBody anatasyonunu eklememiz gerekiyor.
    Metota aslında gelen requestin bir body'si var diyoruz. Peki o ne demek ?
    Post işlemi olduğu için swagger üzerinden execute dediğimizde product nesnesinin attributeleri mesaj gövdesinde istekle birlikte gelir.
    Bu işleme request body denir. Hem istek atıyor hem de ilgili bilgileri gönderiyor.
    Controller gelen datayı ilgili alanlarına göre ayırıp product sınıfının yardımıyla map eder.
    Bizim için bir product oluşturdu. İlgili alanları da map eder. Map etme yani eşleştirme yaptı.
    Bu eşleştirmeyi @RequestBody notasyonu yapar. (.Net tarafında FromBody'e karşılık gelir.)
    Şu an product nesnesi ile çalışıyoruz. İleride DTO nesnesi ile çalışacağız.
*/

/*
    Api katmanında controller bulunur.
    Api katmanı dış dünyayla iletişim kurmamızı sağlar.
    Bir mobil uygulamayla, farklı bir web uygulamasıyla, masaüstü uygulamasıyla iletişimi kuracak ortak iletişim yöntemini kuruyoruz.
    Ortak iletişimi sağlayan faktör json gibi standart haline gelmiş veri tipidir. Bu veri tipini kullanarak iki farklı ortamdaki yazılım birbiri ile anlaşabilir.
    ProductController burada basit bir getAll() fonksiyonu içerir.
    Onun bir restcontroller olabilmesi için (yani javada olmayanlarda beni tanısın diye) ve rest mimarisi için @RestController anatasyonunu ekliyoruz.
    Devamında api için bir mapping yapıyoruz. domain - kodlama.io/api/getall
    Eğer birisi bizim domainimizin sonuna /getall ifadesiyle birlikte istek atarsa bizim getAll() fonksiyonumuz çalışır ve ürünlerimizi getirir.

    Api sadece datayı göndermez. İşlemin başarılı olup olmadığı bilgisini de gönderir.
    Datanın üstünde işlemin başarılı olup olmadığına dair bilgileri de göndermeliyiz.
    Uygulamamızı biraz daha profosyonelleştireceğiz.
    Requestlere response verirken standardize ediyor olacağız.

    İki tip istek vardır;
    - Ben bir data istiyorum.
    - Ben bir data gönderiyorum bunu kaydet.
    Ürünlerin listelenmesi - get request

    Üç tane bilgi olması gerekir;
    - Data
    - İşlem sonucu (başarılı mı ? başarısız mı ?)
    - Mesaj bilgisi

    Bu üç bilgiden ikisi zorunludur.
    İşlem sonucu ve mesaj bilgisi kesinlikle olmalıdır.
    Fakat data zorunlu değildir.
    Bunu sadece burada değil bütün projelerimizde bunu kullanabiliriz.

    Bizim bunu bütün projelerimizde kullanabilmemiz için bir katman oluşturmuştuk.
    core - bütün projelerimde kullanacağım ortak kodları bu package altına yerleştireceğim anlamına geliyor.

*/


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

    Swagger'ı implemente etmemiz gerekiyor.
    Swagger birçok ortamda çalışabilen, hızlı bir biçimde test etmemizi sağlayan bir arayüz ortamı sağlıyor.
    Dökümantasyon süreci içinde oldukça yararlıdır.
    Postman veya tarayıcı kullanmadan hızlı bir biçimde test etmemizi sağlıyor.
*/