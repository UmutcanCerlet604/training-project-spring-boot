package trainingproject.northwind.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Category {

    @Id
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}

/*
    Northwind veritabanında product ile category tabloları birbiri ile ilişkilidir.
    Product tablosunda her ürün için bir category_id bulunur.
    Burada product ve category tabloları arasında foreign key bulunur.
    Product tablosunda category_id yerine category_name kullanmamız yanlış olur.
    Çünkü direkt kategori ismini kullandığımız zaman ürünün kategorisi değiştiğinde bütün verileri elle değiştirmemiz gerekecek.

    Veritabanı ilişki çeşitleri;
    - one to many relation;
    Ana tabloda bir kere geçebilir. Diğer tabloda birden çok kez geçebilir.
    - one to one relation;
    Ana tabloda bir kere geçebilir. Diğer tabloda da bir kere geçebilir.

    Burada ana tablo olarak category tablosu geçer.
    Çünkü category tablosu kategoriler için temel özellikleri içerir.
    Bir kategorinin birden fazla productu olabilir.
    Birden çok ürün bir kategoriye sahip olabilir.
    Burada hangi tabloyu düşünüyorsak yönü ilişkilendirdiğimiz tabloya göre ayarlamalıyız. (Gidişe göre ayarlamalıyız.)
*/



















