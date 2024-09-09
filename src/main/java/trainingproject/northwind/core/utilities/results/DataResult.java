package trainingproject.northwind.core.utilities.results;

public class DataResult<T> extends Result {

    private T data;
    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public T getData(){
        return this.data;
    }
}

/*
    Data Result'ı oluşturabilmek için result'ın iki constructorını da çağırmam gerekiyor.
    Çağırmazsam parametreleri gönderemem.
    Birden fazla veri tipiyle çalışmamın mümkün olacağı durumlarda generic çalışırız.
    super() - base sınıfın constructorlarını çalıştırmaya yarıyor.
    this - o anki sınıf
    super - base sınıf
    İki tür iiçinde constructorlar oluşturduk.
    - Veri, başarılı mı ve mesaj bilgisi
    - Veri, başarılı mı bilgisi
    Altyapı geliştiricileri sadece core package içerisinde çalışırlar. Burada bir yapı kurmaya çalışırlar.
*/