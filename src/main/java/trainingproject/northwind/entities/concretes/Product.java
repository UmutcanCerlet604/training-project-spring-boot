package trainingproject.northwind.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Id'yi sen oluşturmayacaksın. Postgre tarafında otomatik olarak oluşturulacak diyoruz.
    // Buna strategy denir. Veritabanları türüne göre değişiklik gösterir.
    // Oracle - sequence
    @Column(name = "product_id")
    private int id;

    //    Bunu tutmamıza gerek yok artık. (İlişkiyi kurduğumuz için)
    //    Bu yöntem farklı ORM lerde farklı davranabilir.
    //    Bu alan tekrarını entity frameworkte verebiliriz. Entity framework buna kızmaz.
    //    Veritabanında bu alan olduğu için bunu tanımlayabiliriz diye söyler.
    //    Hibernate bu alanı duplicate edilmiş alan olarak tanımlar.
    //
    //    @Column(name = "category_id")
    //    private int categoryId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "units_in_stock")
    private short unitsInStock;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

}

/*
    @Column(name = "category_id")
    private int categoryId;

    Bunu tutmamıza gerek yok artık. (İlişkiyi kurduğumuz için)
    Bu yöntem farklı ORM lerde farklı davranabilir.
    Bu alan tekrarını entity frameworkte verebiliriz. Entity framework buna kızmaz.
    Veritabanında bu alan olduğu için bunu tanımlayabiliriz diye izin verir.
    Hibernate bu alanı duplicate edilmiş alan olarak tanımlar.
    Burada category nesnesinin içinde categoryId zaten bulunduğu için tekrar ediyoruz diye algılar.

    Bunun nedeni;
    Hibernate arka planda product için bütün kolonları oluşturur.
    Extra olarak sanki product'ın kolonlarıymış gibi category'nin kolonlarını da yanına koyuyor.
    Bu yüzden biz category olarak verdiğimiz için aynı zamanda category'nin içinde categoryId bulunduğu için ayrıca bizim categoryId olarak yeni bir alan oluşturmamıza gerek kalmıyor.
    Bu ORM'ler ilişkilendirme yaptığımızda biz aksini belirtmediğimiz sürece primary key üzerinden ilişkilendirmeleri yaparlar.(Default'u budur.)

    Category için many to one ilişkisi kurduk.
    Neden ?
    Product tablosunu düşündüğümüz zaman tabloda birden çok kategori olduğunu görürüz.
    Bir kategori birden fazla kez tekrar ediyor.
    Many - product // one - category oluyor.
    Nasıl ilişkilendirecek ?
    category_id üzerinden join işlemi yapacak.
    Diğer tarafta yani kategorinin içinde ayrıca kolon olarak vermeme nedenim primary key olan category_id kullanmamdır.


*/

/*
    Burada da many to one ilişki vardır.
    Buradaki product join olmaya çalışıyor aslında.
    Category tablosunun product tablosu ile ilgili hiç bir bilgisi yoktur.
    Biz join işlemini product tablosundaki category_id ile category tablosundaki category_id eşitlendiği anda bunları maplemiş olacağız.
    Bu şekilde aslında biz bu product'ın categorysi nedir şeklinde tutuyoruz.
    Buradaki ilişkilendirmeyi doğru yaparsak JpaRepository ile işlemlerimizi daha kolay halledebileceğiz.
*/

/*
     @Table anatasyonu;
     - Uygulama ayağa kalktığında bu nesnenin aslında bir veritabanı tablosuna karşılık geldiğini ve o tablonun hangisi olduğunu söylüyor.
     - Postgres veritabanımızda product adında bir tablomuz var. Bu sınıftan üretilen nesne ona karşılık gelir.
     - Veritabanında isimlendirme yaparken çoğul isimler kullanırız. Çünkü tablo birden fazla ürünü tutar.
     - Bizim sınıfımızdan üretilen her nesne bir ürüne karşılık gelir. Kodu yazarken tekil yazmamızın sebebi biz bu sınıftan bir nesneyi new ile oluşturduğumuzda tek producta karşılık gelmesidir.

    @Data anatasyonu;
    Lombok bizim için getter ve setter işlemlerini yapar.

    Parametreli ve parametresiz constructorlarımızı sildik.

    @AllArgsConstructor;
    Lombok bizim adımıza @AllArgsConstructor anatasyonu ile otomatik olarak parametreli constructorları oluşturur.

    @NoArgsConstructor;
    Lombok bizim adımıza @NoArgsConstructor anatasyonu ile parametresiz constructorı da oluşturur.

*/



/*
    Lombok sayesinde constructor oluşur ve getter setter metotları da otomatik bir şekilde yapılır.
    @Entity - Hiç bir class çıplak kalmamalı
    Spring boot bunun için anatasyonları kullanır. @Entity, bir anatasyondur.
    @Entity anatasyonunu gördüğümüzde biz o sınıfın entity katmanında yer alan bir sınıf olduğunu anlıyorduk.
    Bu anatasyonlar bize ileride generic sınıfları oluştururken fayda sağlayacaktır.
    Spring framework bize bir sınıfın hangi katmana karşılık geldiğini sorar.
    Bunu anatasyon yöntemleriyle yapar.

    Anatasyon Nedir ?
    Bir sınıfın çalışma anında veya derleme anında o sınıfla ilgili bilgi toplamak için bir yapıdır. (Metotlar içinde uygulanabilir.)
    .Net - Attribute
    Angular - Decorator
    Farklı isimler ama aynı işleri yaparlar.
    Bu işlemi yaptığımızda product sınıfının bir entity olduğunu söylüyoruz.

    Java Communtiy'de bazı kalıplaşmış durumlar vardır. Bunlardan bazıları SOLID mimarisine uygun değildir.
    Anatasyon güdümlü programlamalar yapılıyor.
    @Entity - Veritabanı nesnesinin diyoruz.
    Veritabanında karşılık gelen tabloyu belirtmek için de aynı şekilde bir anatasyon kullanıyoruz.
    @Table(name= "Table Name")
    Her field'ın aslında veritabanında yer alan tabloların kolonlarına karşılık geldiğini anlamalıyız.
    Bu yüzden her field'ın başına @Column anatasyonunu kullanarak veritabanlarında karşılık geldiği sütunların isimilerini veriyoruz.
    Bu şekilde veritabanındaki product tablosu ile burada yazdığımız product sınıfı eşleşmiş olur.
    Ayrıca ileride yazacağımız sorguları buna göre şekillendireğimiz için primary key olan id fieldına @Id anatasyonunu eklememiz gerekiyor.
    Sorgular bu Id fieldına göre yapılandırılır.
    Northwind veritabındaki verilerin id'leri otomatik bir şekilde artar. Bu yüzden burada da @GeneratedValue anatasyonunu kullanmamız gerekir.
    Bazı veritabanlarında verilerin id'leri manuel bir şekilde elle arttırılıyor olabilir fakat bizim kullandığımız veritabanındaki verilerin id'si otomatik arttığı için anatasyonu kullandık.

    Solide uymama sebebi aslında bir sınıfta birden fazla iş yapmaya çalışmamızdır.
*/