package Grammar.Basic.Throwable;

import java.io.FileNotFoundException;

public class ErrorAndException {
    //Error
    private void throwError(){
        throw new StackOverflowError();
    }

    // RuntimeException
    private void throwRuntimeException(){
        throw new RuntimeException();
    }

    // CheckedException
    private void throwCheckedException(){
        try {
            throw new FileNotFoundException();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ErrorAndException ee = new ErrorAndException();

        ee.throwError();
        ee.throwCheckedException();
        ee.throwRuntimeException();
    }
}
