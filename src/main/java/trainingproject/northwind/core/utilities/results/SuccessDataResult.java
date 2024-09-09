package trainingproject.northwind.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T>{

    public SuccessDataResult(T data, String message) {
        super(data, true, message);
    }

    public SuccessDataResult(T data){
        super(data, true);
    }

    public SuccessDataResult(String message){
        super(null, true, message);
    }

    public SuccessDataResult(){
        super(null, true);
    }

}

/*
    İşimizi kolaylaştırmak için DataResult sınıfını da genişlettik.
    True sonuçlar için SuccessDataResult sınıfını oluşturduk.
    DataResult sınıfından türediği için bir constructorını oluşturduk.
    Bu sınıfı sadece true sonuçları karşılamasını istediğimiz için success'i direkt true olarak gönderdik.
    Çeşitli kombinasyonlar oluşturduk. Bu şekilde farklı imkanlar sağladık geliştiriciye.

*/