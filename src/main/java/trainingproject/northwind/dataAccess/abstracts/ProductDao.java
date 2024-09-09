package trainingproject.northwind.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import trainingproject.northwind.entities.concretes.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
}

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