package trainingproject.northwind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NorthwindApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthwindApplication.class, args);
	}


}

/*
	@Bean - Bu yapılarda aslında bir java sınıfıdır.
	Daha çok configürasyon işlemlerinde karşımıza çıkar.
	Bizim uygulamamız başladığı anda spring boot @Bean anatasyonu ile başlayan birşey gördüğü zaman onu belleğe yerleştirir.
	Docket isimli bir nesne ile controllerlarımızı ve requestcontrollerlarımızı bulup bir dökümantasyon haline getirir.
	Docket bizim uygulamamız içindeki bütün apileri tarayıp buluyor. Sonrasında bizim test edebileceğimiz bir konuma getirir.
	Gerekli bütün paketleri import ettik.

	@EnableSwagger2 anatasyonu ne yapar ?
	Bu anatasyon swaggerı başlatan anatasyondur.
	Bu nesnede Docket üzerindeki api() metoduyla bütün controllerımızı tarayıp apilerimizi buluyor ve dökümante edilebilir bir forma getiriyor.
	Bilinen bir ürünün spring boot için configürasyonunu hazırlamış olduk.
	Springfox ile uyumsuzluk çıktığından dolayı Springdoc OpenAPI üzerinden swaggerı ekledim.

*/
