package trainingproject.northwind.core.utilities.results;

public class ErrorDataResult<T> extends DataResult<T>{

    public ErrorDataResult(T data, String message) {
        super(data, false, message);
    }

    public ErrorDataResult(T data){
        super(data, false);
    }

    public ErrorDataResult(String message){
        super(null, false, message);
    }

    public ErrorDataResult(){
        super(null, false);
    }
}

/*
    Bu kadar detaylı yazmayabilirdik ama o zaman da soyutlamadan tam anlamıyla yararlanmamız mümkün değildir.

*/