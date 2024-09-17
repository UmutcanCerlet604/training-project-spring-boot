package trainingproject.northwind.core.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    @Email
    @NotBlank // Boş geçilmesini istemediğimi belirttim.
    @NotNull  // Null geçilmesini istemediğimi belirttim.
    private String email;

    @Column(name = "password")
    @NotBlank
    @NotNull
    private String password;
}


/*
    Validation Nedir ? (Doğrulama)

    Doğrulama ile business'ı birbirine karıştırmamamız gerekiyor.
    Örneğin bir ürün, kategori veya personel eklendiği zaman kullanıcı tarafından gönderilen verinin formata uygun olup olmadığı ile ilgilenildiği süreçtir.
    Örneğin bir sisteme kayıt oluyoruz diyelim. Şifremiz 6 haneden az olamaz diye bir kural var. Biz de 5 haneli bir şifre belirledik. Burada sistem bizim şifremizi kabul etmez. Yeni bir şifre oluşturmamızı bekler. Bu süreç validatioın sürecidir.

    Başka bir örnek; Bir kişiye trafik ehliyeti vereceğimizi düşünelim.
    İlk yardımdan belge almış mı ?
    Trafik şube müdürlüğünden bu belgeyi almış mı ?
    gibi kriterlerle uygun olup olmaması iş kuralları ile ilgili bir durumdur.

    Bu süreci sistemimizde ilerleyen dönemde kullanabilmemiz için başka bir nesne üzerinden yapacağız. Sistemize email ve parolasını girerek kayıt olmak isteyen bir kullanıcıyı simüle edeceğiz. Bu noktada email'in zorunlu olması, email'in email formatında olması ve parolanın ilgili kurallara uygun olması ve zorunlu olması gibi yapıları validation ile yapacağız.

    Bu işlemler için communtiy'nin sürekli kullandığı Spring'in validation paketinden yararlanacağız.(.Net - anotation)

    Kullanıcıyı düşünelim;
    Kullanıcı yapısını birçok projede kullanabiliriz. Bu yüzden core package içerisinde sınıflarımızı oluşturacağız.

    Normalde user ile ilgili süreçleri yapacağımız class'ı entity package altında değerlendirebilirdik. Fakat biz burada diğer projelere referans olması için core package içerisinde değerlendireceğiz. Burada core altında entities package oluşturarak yapıyı bozmadan ilerliyoruz.

    Veritabanımızda user ile ilgili herhangi bir tablo yok şu anda.
    Burada illa veritabanının hazır olması gerekmiyor.
    Code first yaklaşımıyla veritabanını kendimizde oluşturabiliriz.

    Email adlı field'da @Email anotasyonunu kullandık.
    Bunu kullanabilmek için de pom dosyasına spring boot starter validation bağımlılığını ekledik.

    @NotBlank - Blank demek "" veri var yani ama boş
    @NotNull - Hiç birşey gelmedi demek

    Bu yapı SOLID'in S harfindeki prensibe ters bir harekettir.

*/