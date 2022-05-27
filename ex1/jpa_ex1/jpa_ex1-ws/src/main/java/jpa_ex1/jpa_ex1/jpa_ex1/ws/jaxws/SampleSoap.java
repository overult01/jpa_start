package jpa_ex1.jpa_ex1.jpa_ex1.ws.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "SampleSoap")
public class SampleSoap {

    @WebMethod(operationName = "methodOne")
    public String methodOne() {
        return "Hello JARCH";
    }
}
