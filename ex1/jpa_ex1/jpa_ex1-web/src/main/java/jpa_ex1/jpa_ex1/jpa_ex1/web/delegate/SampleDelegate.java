package jpa_ex1.jpa_ex1.jpa_ex1.web.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class SampleDelegate extends BaseDelegate {

    @Override
    public void processamento(DelegateExecution execution) {
        System.out.println("########################################################################");
        System.out.println("******  ATENCAO !!!! FALTA IMPLEMENTACAO DESSE DELEGATE !!!");
        System.out.println("########################################################################");

    }
}
