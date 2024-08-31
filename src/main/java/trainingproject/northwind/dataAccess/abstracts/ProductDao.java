package trainingproject.northwind.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import trainingproject.northwind.entities.concretes.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
}

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