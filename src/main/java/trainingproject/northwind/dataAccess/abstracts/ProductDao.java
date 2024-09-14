package trainingproject.northwind.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trainingproject.northwind.entities.concretes.Product;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Product getByProductName(String productName);
    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
    List<Product> getByCategory_CategoryIdIn(List<Integer> categories);
    List<Product> getByProductNameContains(String productName);
    List<Product> getByProductNameStartsWith(String productName);

    // JPQL
    @Query("From Product where productName=:productName and category.categoryId=:categoryId")
    List<Product> getByNameAndCategory(String productName, int categoryId);

}

/*
    getBy JPARepository'e özgü bir durumdur. getBy kullandığımızda where koşulu yazması gerektiğini bilir.
    Sonra kolonu yazmamız gerekiyor. CamelCase yöntemiyle yazıyoruz.
    Burada hata almamızın nedeni Product içerisinde category_id şeklinde bir alan bulunmamasıdır.
    Category'nin içinde bulunan category_id'ye göre yap dedik.
    Kodu çalıştırdığımızda yani compile edildiğinde hibernate JPARepository'nin içindeki isimlere bakarak query oluşturuyor.
    Biz yayına aldığımızda ilk etapta arka planda bu queryleri oluşturmaya çalışıyor. Oluşturamazsa hata kodu veriyor.
    Özetle;
    JPARepository'nin ilgilendiği nokta biz programı yayına aldığımızda metotlara bakıp querylerini yazmaya çalışıyor.
    ProductManager'da niye hata almadık. Buradaki kod derlenmiyor. JPARepositroy içerisindeki kod derlendiği için sorun gözüküyor.
    ProductManager'da biz metotun ismini farklı birşey de verebilirdik. Doğrusu aynı isimlendirme kullandırmaktır.
    Category_CategoryId yerine Category ismini de kullansak metotta bir sorun yaşamazdık.

*/

/*
    JpaRepository bize birçok hazır metot sağlar.
    (getById(), findById(), save(), findAll()) bu metotlarla sıralama, pagination, filtreleme gibi işlemler yapabiliyoruz.
    Fakat biz burada bir alana göre datayı getirmeye çalışıyoruz.
    Bu yüzden bunu kendimiz yapmamız gerekecek.
    Bu nasıl çalışır ?
    Jpa implemantasyonunda herhangi bir implemantasyon yapmamıza gerek yok.
    Jpa Repository getBy ifadesini gördüğünde tabloya bakıyor ve where koşulu ekliyor.
    Hazır bir şekilde geliyor.
    Burada en önemli nokta getBy veya findBy ile başlamaktır.
    İkinci metotta and operatörü olan bir where koşulu yazıyor.
    Üçüncü metotta or operatörü olan bir sorgu yazmış olduk.
    Buradaki fark iki koşuldan birini sağlaması yeterli oluyor.
    Yukarıda ise and operatörü kullandığımız için iki koşulu da sağlaması gerekiyor.
    Burada sorguların doğru bir şekilde çalışabilmesi için isimlendirmeyi doğru yapmamız gerekiyor.
    Bütün işlemler JpaRepostiroy tarafından halledilecektir.

     select * from products where product_name = abc
     ismi abc olan ürünü getir.

     select * from products where product_name = abc and category_id=1
     select * from products where product_name = abc or category_id=1
     Yukarıda yazılan sorguların sql'de karşılıkları bunlardır.

     select * from products where category_id in(1,2,3,4)
     Category_id'si 1,2,3,4 olan ürünleri getir.
     Böyle bir sorgu yazmak istediğimizde de in keywordünü kullanacağız.

     Ürünün isminde geçen contente bağlı aramayı contains kullanarak yaptık.

     List olması zorunlu değil. Array de olabilirdi. Dönülebilecek her yapıyı burada kullanabilirdik.

    JPQL Nedir ?
    String formatta yazılan bir yapıdır.
    select * from products where category_id in(1,2,3,4)
    Bizim böyle bir sorgu yazmamız gerektiğini düşünelim.
    Burada bu sorguyu objeler arasında yapmamız denebilir.(JPQL)
    Spring arkada bu sorguyu sql formuna dönüştürebilir.

    // JPQL
    @Query("From Product where productName=:productName and categoryId=:categoryId")
    List<Product> getByNameAndCategory(String productName, int categoryId);
    select * from products where product_name=bisey and categoryId=bisey
    Burada dönüş tipini ne verirsek kendini ona göre ayarlar spring boot.
    Burada sql'de bulunan tabloyu unutuyoruz.
    Burada query yazarken veritabanı tablosunu unutuyoruz.
    Product burada entity'dir.
    Parametreyi iki nokta üst üste kullanarak vermiş olduk.
    Angular'da routing yaparken ? ifadesini kullanırız. Burada da : ifadesini kullanıyoruz.
    Buradaki alanlar product entity'si içerisinde yer alan alanlardır.
    Burada veritabanını unutuyoruz. Veritabanı sanki product entitysi gibi davranıyoruz.

*/

/*
    ProductDao tarafında JpaRepository'i implemente ettiğimiz için bize bazı hazır fonksiyonları sağlamış oldu.
    Örnek olarak; findAll(), findById()
    ORM kavramı nedir ?
    Şu an ilişkisel veritabanlarıyla uğraşıyoruz.
    Northwind veritabanında tablolar arasında ilişkiler var.
    Biz de bu ilişkileri spring boot tarafında da kurarak nesnel bir yapı oluşturacağız.
    Veritabanına gidip gelirken daha rahat edeceğiz.
    Bu yapıyı ilerleterek ORM konusunda ilerleyeceğiz.
    JPQL yazacağız ve paging işlemleri de yapacağız. (sayfalama / sıralama)
    Sayfalama, sıralama, artan fiyat ve azalan fiyat gibi işlemleri yapacağız.
    Binary data olarak image dosyalarını böyle saklamak günümüzde kullanılmıyor.
*/

/*
    - persistency - bir datayı kalıcı hale getirmek.
    - Veriyi kalıcı hale getirmek için veritabanı kullanıyoruz.
    - Veriye erişim ile ilgili işlemleri data access katmanında yapıyoruz.
    - Başka projelerde dataLayer, dataAccess ve persistincy gibi klasörleme yöntemleri de görebiliriz.
    - JpaRepository kullanarak product için bir tane data access object interface'i oluşturduk.
    - ProductDao, JpaRepository interface'ini extend ederek bizim için CRUD operasyonlarını yapmamızı sağlıyor.
    - Burada bir generic yapı vardır. JpaRepository her nesne için çalışacak bir yapıya sahip.
    - Biz burada diyoruz ki, ben burada product için çalışıyor olacağım ve aynı zamanda benim primar_key veri türüm integer diye belirttik.
    - Bunu belirttiğimizde spring sorgularımızı bu formatta yapılandırır.
*/

/*
    Bir interface bir interface'i extend edebilir.
    Jpa Repository ne yapar ?
    Verdiğin veri tipi için (yani @Entity anatasyonu ile süslenmiş nesne için kısaca product) sorguları buna göre hazırlayacak.
    Bizden parametre olarak entity sınıfını ve primary key'in veri tipini ister. (intelli sense hazırlanacak)
    Product sınıfında @Entity anatasyonunu kullanmasaydık burada problem yaşardık.
    Şu an product sınıfının crud operasyonları hazır.
    Diğer operasyonları hazırlamakta çok basitleşecek.
    Bazı projelerde ProductDao yerine ProductRepository isimlendirmesi kullanılır. İkisi de aynıdır.
    Burada jpa repository extension vasıtasıyla hangi entity'e (tabloya) hangi id veri tipiyle sorguların hazırlanması gerektiğini söylemiş olduk.

    Spring ile çalışıyorsak Hibernate'in Jpa repository'sini kullanarak herşeyi daha kolay bir hale getirebiliyoruz.
    ProductDao adında bir interface oluşturduk ve onu JpaRepository'e extend ettik.
    Bu repository hangi tablo için çalışacak ve o tablonun primary key'inin veri türü nedir bunları belirledik.
*/