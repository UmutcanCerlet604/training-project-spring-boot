package trainingproject.northwind.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import trainingproject.northwind.business.abstracts.UserService;
import trainingproject.northwind.core.entities.User;
import trainingproject.northwind.core.utilities.results.ErrorDataResult;
import trainingproject.northwind.core.utilities.results.Result;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

    private UserService userService ;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.add(user));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)  // Bütün metotlarımızı bu handler'dan geçireceğiz.
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Bu metot default olarak bad request olarak dönsün. (500 hata kodu döner)
    public ErrorDataResult<Object> handleValidationException
    (MethodArgumentNotValidException exceptions){
        Map<String, String> validationErrors = new HashMap<String, String>();
        // User'daki alanlarda oluşan tüm hataları oku. (Liste döner)
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama Hataları");

        return errors;
    }
}

/*
    Burada bazı iyileştirmeler yapacağız.
    UserController sınıfımızı oluşturduk.
    Anotasyonlarımızı ekledik.
    Biz normalde map fonksiyonlarında geri dönüş tipi olarak hep result döndürdük.
    Bu da her türlü biz extra http response yani yanıt değeri vermediğimiz için hepsini 200 olarak döndürür.
    Bu response değerleri önemlidir.

    200
    300
    400
    500

    Bu http kodları frontend geliştiricileri için önemlidir.
    Geliştirici işlemin türüne göre fikir sahibi olur.
    Get işlemleri için 200 kodunu kullanıyoruz.
    Add işlemleri için 201 kodunu kullanıyoruz.
    Genellikle 200, 400, 500 kodlarını kullanıyoruz.
    400 ve 500 http kodları genellikle hatayı anlatır.
    Bu durumlar için spring'in getirdiği ResponseEntity<> adlı bir yapısı bulunmaktadır.
    ResponseEntity statik bir yapıdır.
    ResponseEntity.ok() - işlem tamam demek. Yani http kodu 200 demek.

    Ama burada bir eksik var. Bu işlem illa başarılı olacaktır diye birşey yoktur.
    Hata da verebilir. (Exception da verilebilir.)
    Burada işlem sonucuna ben karar vermek istemiyorum diyebilirim.
    İşlem sonucu ne ise ona göre sonucu döndür diyebilmeliyim.
    Bu yüzden ResponseEntity<Result> yerine ResponseEntity<?> kullanırsak işlem başarılıysa başarılı döner. İşlem başarısızsa başarısız döner.
    Bunu add işlemi için yaptık.

    Biz bu işlemi yapıyor olacağız ama bu işlem sonucunda validationlar hata da verebilir.
    Bu yüzden bir global hata yakalama işlemi gerçekleştireceğiz.
    Hata yakalamayı try/catch tek tek elden geçirebiliriz.
    Büyük yazılımlarda bunu bu yöntemle yapmak mantıklı değildir.
    Burada AOP diye tanımlanan bir mantık devreye giriyor.

    Biz bütün operasyonlarımızın geçeceği global bir handler exception yazacağız.

    AOP Nedir ?
    Bizim bütün metotlarımızı bir hata almamıza karşın sarmalıyoruz.(Global ex. handler)
    Aslında bütün operasyonlarımız global exception handler'dan geçer.
    Bizim add metodumuz bu handler içinde çalışır aslında.
    Dolayısıyla bütün operasyonlarımıza try/catch yazmak yerine bir tane yazarız ve bütün operasyonlarımızı oraya yollarız.
    Bu yapı da yine hazır bir şekilde spring boot tarafından sunuluyor.

    Exception için bir metot yazacağız.
    Metot ne dönecek ? Bir hata durumu dönecek.
    ErrorDataResult dönecek. Fakat dönecek yapının veri türü herşey olabilir.
    Bu hata sistemsel hata olabilir veya validation kurallarına uygunsuzluktan doğan hatalar olabilir.
    Bu hatanın dönüş tipine genel veri tipi olarak object yazıyoruz.
    Bütün classların herşeyin temeli objecttir.
    Primitive tipler dahil. (integer, boolean) base obejct. (Java, C# dahil)
    handleValidationException() - Validasyon hatalarımızı test edecek.
    Bu metotu nasıl handler edeceğiz ?
    Burada AOP devreye girer. Burada bir anotasyonla halledeceğiz.
    Bu sistemde şu türde hata olursa bu metotu devreye sok diyoruz.

    @ExceptionHandler(MethodArgumentNotValidException.class) ;
    Spring bütün validation hatalarını spring validation yakalayacak.
    Doğrulama hataları, not null, not blank hataları gibi...
    Burada tipi vermek için .class ifadesini kullanıyoruz.

    Hangi alanda hata oldu ? Hata ne ?
    Bu sorulara odaklanacağız.
    - Email formatına uygun değil.
    - Email not null formatına uymamış boş gelmiş.

    Burada iki alanı vereceğim için map yapısından yararlanacağız.(Diğer dillerde dictionary)
    Anahtar değerim hangi alanda olduğunu belirtecek.
    İkinci değerim hatanın kendisini belirtecek.
    HashMap kullandık. HashMap bir Map olduğu için new ile oluşturabildik.

    Bu yapı spring boot dökümantasyonunda bulunur.
    Oluşan bir hata olursa hatayı metot parametresi olarak geçiyor.

    Buradaki mantık;
    Sistemde bir exception oluşursa bu metotu çağırır. (Anotasyonun görevi)
    Metota hataları parametre olarak gönderdik.
    Basit bir şekilde foreach ile döndük.
    Bu hataların her birini listeye ekleyip listeyi döndürdük.

    Default Message - (Bu alan null olamaz.)
    Farklı dillerde desteği vardır.
    Kullanıcıya göre yada sistemin o anki diline göre değişir.
    Bu mesajı manipüle edeceğiz.

    Metodu AOP ile sarmallama işlemine interceptor denir.

    Kullanıcıyı validasyon kurallarına göre doğru bir şekilde kaydedebildik.
    Fakat burada bir sorun var. Veritabanını açtığımızda şifreler olduğu gibi gözüküyor.
    Bu şifreleri hashlememiz gerekiyor.
    Password Salting ile daha güçlü bir hale getireceğiz.

    JPA Repository code first yaklaşımı ile çalışır.
    Veritabanında ilgili tablo varsa ona göre oluşur.
    Mesela Product entity'e yeni bir field eklersek JPA Repository veritabanına o yeni alanı kolon olarak ekler.
    Veritabanını silsek şu an;
    Veritabanını yeniden oluşturur. Burada bulunan entitylere göre tabloları oluşturur.
    Tek fark elimizde veri olmaz. İlk zamanlar yaklaşımımız code first with database first
    Şu an yaptığımız ise direkt code first yaklaşımıdır.

*/