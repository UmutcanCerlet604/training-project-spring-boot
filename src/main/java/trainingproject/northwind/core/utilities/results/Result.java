package trainingproject.northwind.core.utilities.results;

public class Result {
    private boolean success;
    private String message;

    public Result(boolean success){
        this.success = success;
    }

    public Result(boolean success, String message){
        this(success);
        this.message = message;
    }

    public boolean isSuccess(){
        return this.success;
    }

    public String getMessage(){
        return this.message;
    }
}

/*
    Bütün requestlerde kullanabileceğimiz bir yapıdır.
    Her request için message yapısını kullanmamıza gerek yok.
    Bazı requestler için sadece true/false dönsün yeter diyebiliriz.
    Http kodu 200 döndüğünde işlemin başarılı olduğunu anlamış olduk.
    Response body içerisinde data yerine başarılı olup olmadığını belirten bir bilgi ve mesaj yer alıyor.
    Hem tek parametreli hem de çift parametreli constructorlarını oluşturduk.
    Result'ın Data Result tipinde olanı da olabilir.
    İşlem için başarılı ve mesaj bilgisi dışında bir de şu datayı göndermek istiyorum gibisinden bir result tipi de var.
    DataResult sınıfını oluşturduk. DataResult sınıfı Result sınıfını miras alır.

*/
