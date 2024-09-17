package trainingproject.northwind.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithCategoryDTO {
    private int id;
    private String productName;
    private String categoryName;
}


/*
    DTO Nedir?

    İlişkisel veritabanlarında veriyi tekrar etmemek için normalizasyon yapıyoruz.
    Yani ilişikiler kuruyoruz. (One to one, One to many)

    Ben bu yapıyı JSON objesi şeklinde giderek yapabilir. Başka bir yolla yani bir objede hayata geçirmek isteyebilirim.

    DTO - Data Transformation Object
    Veri transfer edilen obje.
    Bu DTO'ları sadece ilişkili tablolarda değil tek tablolarda kullanmalıyız.
    Neden ?
    Bazı sistemlerde bir tabloda 100 tane kolon oluyor. Biz sadece birkaç tane kolon ile çalışıyor olabiliriz. Yapılan isteğe göre bir DTO oluşturup ona aktarıp transfer edebiliriz.
    Bunu da business katmanında bir mapping yöntemiyle çözebiliriz.

    İki tabloya da join atarak bu işlemi yapacağız.







*/