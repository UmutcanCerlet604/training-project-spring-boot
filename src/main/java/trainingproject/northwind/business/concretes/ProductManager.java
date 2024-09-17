package trainingproject.northwind.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import trainingproject.northwind.business.abstracts.ProductService;
import trainingproject.northwind.core.utilities.results.DataResult;
import trainingproject.northwind.core.utilities.results.Result;
import trainingproject.northwind.core.utilities.results.SuccessDataResult;
import trainingproject.northwind.core.utilities.results.SuccessResult;
import trainingproject.northwind.dataAccess.abstracts.ProductDao;
import trainingproject.northwind.entities.concretes.Product;
import trainingproject.northwind.entities.dtos.ProductWithCategoryDTO;


import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        super();
        this.productDao = productDao;
    }

    // JPA hazır metotlar
    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>
                (this.productDao.findAll(), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        Page<Product> productPage = this.productDao.findAll(pageable);
        List<Product> products = productPage.getContent();
        return new SuccessDataResult<List<Product>>(products);
    }

    @Override
    public DataResult<List<Product>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC, "productName");
        return new SuccessDataResult<List<Product>>
                (this.productDao.findAll(sort), "Başarılı");
    }

    // Sort.ASC - küçükten büyüğe artan demek (asending)
    // Sort.DESC - büyükten küçüğe azalan demek (desending)

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Ürün eklendi");
    }

    // İlişkili tablo ile ilgili metotlar
    @Override
    public DataResult<Product> getByProductName(String productName) {
        return new SuccessDataResult<Product>
                (this.productDao.getByProductName(productName), "Data Listelendi");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategory_CategoryId(String productName, int categoryId) {
        // ToDo - Business Codes
        return new SuccessDataResult<Product>
                (this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategory_CategoryId(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>
                (this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByCategory_CategoryIdIn(List<Integer> categories) {
        return new SuccessDataResult<List<Product>>
                (this.productDao.getByCategory_CategoryIdIn(categories), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        return new SuccessDataResult<List<Product>>
                (this.productDao.getByProductNameContains(productName), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
        return new SuccessDataResult<List<Product>>
                (this.productDao.getByProductNameStartsWith(productName), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>
                (this.productDao.getByNameAndCategory(productName, categoryId), "Data Listelendi");
    }

    @Override
    public DataResult<List<ProductWithCategoryDTO>> getProductWithCategoryDetails() {
        return new SuccessDataResult<List<ProductWithCategoryDTO>>
                (this.productDao.getProductWithCategoryDetails(), "Data Listelendi");
    }


    // ProductDao'ya implemente edilmiş JpaRepository yardımıyla save fonksiyonuyla kaydetme işlemini yaptık.
}

/*
    Pagination (Sayfalama) Nedir ?
    Örneğin bir sayfada 10 data olacaktır fakat bizim veritabanımızda 100 tane product datası vardır.
    Bu işlem sonucunda 10 tane sayfamız olacaktır. Diyelim ki 71 ile 79'uncu ürünleri göstermek isteyelim.
    Pageable nesnesini oluşturduk. Sonra Dao'daki JPARepository'nin içinde bulunan findall metodunu kullanarak ilgili ürünleri getirdik.
    Sonrasında pageable nesnesine bağlı getContent() metotunu kullanarak gelenleri List of product şeklinde dönüştürdük.
    Burada yanlış bir şekilde getirdi. Birinci sayfada bulunan productları getirmek yerine ikinci sayfada bulunan productları getirdi.
    Biz aslında birinci sayfada bulunan productları getirmesini istemiştik oysaki. Bu neden oldu ?
    Çünkü sayfalamayı sıfırdan başlayarak yapar. Bu yüzden biz managerda bulunan fonksiyonda pageNo-1 şeklinde yazmamız gerekir.


    Şarta göre bir sıralama yapmak istediğimiz için bir getAllSorted() metotu yazdık.
    ProductName'e göre alfabetik olarak aşağıdan yukarıya sıraladık.
    İlk gelen product z harfi ile başlamış oldu.
*/

/*
    ProductManager ProductService'i implemente ediyor.
    Daha sonra ProductManager constructorında ProductDao'yu parametre olarak verdik.
    @Autowired anatasyonuyla spring ProductDao'yu arar ve bulur.
    Biz daha önceden ProductService interface'inde JpaRepository içinde hazır bulunan save ve findAll fonksiyonları kullanarak burada metotlarımızı yazmıştık.
    Bu metotlardan daha farklı ilişili başka bir tablonun kolonuyla ile ilgili başka metotlarda yazdık.
    Bu metotları ProductService üzerinde yazdıktan sonra burada hata aldık.
    Bunun nedeni; ProductManager sınıfı ProductService interface'ini implemente eder. Orada yeni metotlar yazdıkça o metetları burada implemente etmemiz gerekir.
    İntellisense yardımıyla bütün metotları @Override anatasyonuyla buraya aldık.
    Şimdi her biri için yapmak istediğimiz iş kurallarını ve dönüş ifadelerini yazacağız.
    ORM'de sıkça karşılacağımız bir sorunu görmüş olduk. Şu an ürünü başarılı bir şekilde getirebiliyoruz fakat istek veriyi getirirken sonsuz döngüye girdi.
    Bunun nedeni;
    Bu datayı getirirken product'ın category'si var. Category'e gider. Sonrasında category'ninde productlarının olduğunu görür.
    O ürünleri getirirken her bir ürünün tekrardan categorysi oluşuyor. İşte burada sonsuz bir döngüye giriyor. Ona göre o dataı getirmeye çalışıyor.
    ORM'de çok basit bir ignore configürasyonu gerçekleştireceğiz. Bu işlem sonucunda sonsuz döngüye girmesini engelleyeceğiz.
    Recursive bir yapı oluşursa en temelde onu kes ve datayı getir demiş olacağız.
    Bunu bir çok ortamda (örnek olarak .Net - Entity Framework) göreceğiz.
    @JsonIgnoreProperties({"hibernateLazyInıtializer", "handler", "products"})
    Bu anatasyon ile ignore işlemini yapacağız.
    hibernateLazyInitializer - Lazy Loading yap.
    Hibernate'e benim söylediğim kadar mapping yap ve derinlere inme diyoruz.
    Hibernate o an ne yapacağını bilemediği için bunu belirtmeliyiz.
    Ürünün categorisine gidiyor. Orada tekrar ürünler var. Ürünlerin her birini gezerken hepsinin kategorisi var.
    Burada bu yüzden bir problem yaşıyoruz.
    Bizim sadece map ettiğimiz kadar getirsin diye, sorgu döngüye girmesin diye tembel yükleme mimarisini oluşturmamız gerekti.
*/

/*
    Injection mimarisi bağımlılıkların yönetiminde kullanılır.
    Injection mimarisini kullanamayız burada. Çünkü bir bağımlılık yok burada.
    AOP mimarisi de benzer. Biz aslında spring kullanarak AOP mimarisini kullanmış oluyoruz.
    SuccessDataResult aslında bize bir entity'nin dönüşünü anlatır.
    Entity'e özeldir. Bir entity'nin new ile oluşturulması kurumsal mimariyi bozmaz. Doğru bir kullanımdır.

    SuccessDataResult test için mi kullanılıyor ?
    Hayır test için kullanılmıyor. Gerçekten ben bunu döndürmek istediğim için kullanıyorum.

    Hata olsaydı ne yapacaktık ?
    Global hata yönetimi yani Aspect Oriented ile halledeceğiz.
    Bizim projelerimiz genel bir try/catch içine alınacak.
    Rapper denen bir mantıkla kodlarımızı başka bir pipeline'dan geçireceğiz.
    Çalışan bütün kodlar ortak bir mekanizmadan geçecek. O ortak mekanizmada biz bir kere try/catch yazacağız.
    Orada hata çıktığında farklı seçenekler belirleyeceğiz.

    Base class neden abstract bir class (yani interface) olmadı ?
    Java communtiy'si sevmediği için (IResult) bu şekilde yaptık.
    Burada obje işi direkt kendisi yaptığı için bir sınıf olarak oluşturduk.
    Burada Result sınıfı super type olarak geçiyor. Bu sınıfta interface gibi soyuttur.
    Fakat kendisi tek başına da bir anlam ifade eder.
    Java'ya özgü yazmış olduk.
    Result sınıfı çıplak bir sınıftır. Bunun nedeni;
    Bu class bizim için en soyut sınıftır. Tıpkı interface gibi görev yapar.
    Bu sınıfı geçişler için kullandık.
    SuccessDataResult dönmemize rağmen metotun başında geri dönüş değeri olarak DataResult kullandıık.
    Böyle yapmamızın nedeni bazı durumlarda logice bağlı error da döndürebilirim.
    Esneklik sağlayarak hata almamızı engelledik.
*/


/*
    ProductService sınıfı bizim soyut sınıfımızdır.
    ProductManager sınıfı ise asıl bizim işi yaptığımız sınıftır.
    Override kullanarak metotu ezip kendi yapmak istediğimiz işlemleri yapıyoruz.
    ProductService interface'ini implemente ediyoruz.
    Bunun içerisinde veri erişim katmanını kullanacağız.
    Burada constructorda parametre olarak veri erişim katmanındaki interface'i veriyoruz.
    Bu işlemi constructor injection yöntemiyle yapmış olduk.
    Constructor injection; Bir bağımlılığı constructor üzerinden enjekte etmektir.
    ProductDao kendi scope'u dışında hiçbir yerde kullanılamaz.
    En yukarıda geçici (sahte) bir sınıf oluşturduk.
    ProductDao bir interface'dir. Interfaceler new ile oluşturulamaz.
    Peki burada nasıl çalışıyor ?
    Bunu da autowired ile yaparız.
    Autowired, ProductDao'nun bir somut sınıfını arıyor. Bulduğu nesneyi new ile oluşturup bize verir.
    Biz bu noktada özellikle birşey yapmadık. JpaRepository Springin kendi içerisinde implementasyonu var.
    Onu vermiş olduğundan bizim extra birşey yapmamıza gerek yok.
*/

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