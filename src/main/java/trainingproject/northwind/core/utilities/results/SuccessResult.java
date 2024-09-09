package trainingproject.northwind.core.utilities.results;

public class SuccessResult extends Result{

    public SuccessResult(){
        super(true);
    }

    public SuccessResult(String message){
        super(true,message);
    }

}

/*
    Altyapıyı oluşturanlar olarak işimizi biraz daha kolaylaştırıyoruz.
    Result değeriyle ilgilenmeden sonuç success ise direkt SuccessResult sınıfının çalışmasını sağlayacağız.
    Yaptığımız iş tamamen işimizi daha da kolaylaştırmak.
    Aynı SuccessResult sınıfı gibi ErrorResult sınıfını da oluşturduk.
    İki tip durum vardır. İşlem sonucu ve mesaj döndürürüz. Bu bizim için bir resulttur.
    Result'ında iki türü var. Biri başarılı biri hatalıdır.
    this() - O an bulunduğumuz sınıftaki constructorı çalıştırır.
    super() - Inherit ettiğimiz yani base sınıfın constructorını çalıştırır.
*/