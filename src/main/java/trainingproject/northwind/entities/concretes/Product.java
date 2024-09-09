package trainingproject.northwind.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int id;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "units_in_stock")
    private short unitsInStock;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    public Product(){}

    public Product(int id, int categoryId, String productName, double unitPrice, short unitsInStock, String quantityPerUnit) {
        this.id = id;
        this.categoryId = categoryId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.quantityPerUnit = quantityPerUnit;
    }
}

/*
     @Table anatasyonu;
     - Uygulama ayağa kalktığında bu nesnenin aslında bir veritabanı tablosuna karşılık geldiğini ve o tablonun hangisi olduğunu söylüyor.
     - Postgres veritabanımızda product adında bir tablomuz var. Bu sınıftan üretilen nesne ona karşılık gelir.
     - Veritabanında isimlendirme yaparken çoğul isimler kullanırız. Çünkü tablo birden fazla ürünü tutar.
     - Bizim sınıfımızdan üretilen her nesne bir ürüne karşılık gelir. Kodu yazarken tekil yazmamızın sebebi biz bu sınıftan bir nesneyi new ile oluşturduğumuzda tek producta karşılık gelmesidir.

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