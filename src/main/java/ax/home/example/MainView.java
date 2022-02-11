package ax.home.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.AbstractStringToNumberConverter;
import com.vaadin.flow.router.Route;

import javax.swing.*;

@Route("")
public class MainView extends VerticalLayout{
    public MainView() {
        Parser test = new Parser();
        try {
            test.parse("/prospects.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        HorizontalLayout form = new HorizontalLayout();

        TextField name = new TextField();
        name.setLabel("Name");
        name.setRequired(true);

        NumberField amount = new NumberField();
        amount.setLabel("Loan amount");
        amount.setRequiredIndicatorVisible(true);
        amount.setMin(1);
        Div euroSuffix = new Div();
        euroSuffix.setText("€");
        amount.setSuffixComponent(euroSuffix);

        IntegerField years = new IntegerField();
        years.setLabel("Years");
        years.setMin(1);

        NumberField interest = new NumberField();
        interest.setLabel("Interest %");
        interest.setMin(0.1);
        interest.setStep(0.1);
        interest.setHasControls(true);
        Div percentSuffix = new Div();
        percentSuffix.setText("%");
        interest.setSuffixComponent(percentSuffix);

        Button addButton = new Button("Add prospect");

        TextArea textArea = new TextArea();
        textArea.setValue(test.getPrintString());
        textArea.setReadOnly(true);
        textArea.setWidth("50%");

        FormLayout formLayout = new FormLayout();
        formLayout.add(name, amount, years, interest, addButton);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2)
        );

        addButton.addClickListener(click -> {
            if (!name.getValue().isEmpty() && !amount.isEmpty() && !years.isEmpty() && !interest.isEmpty()) {
                Customer newCustomer = new Customer(name.getValue(), amount.getValue(), years.getValue(), interest.getValue());
                test.addCustomer(newCustomer);
                textArea.setValue(test.getPrintString());
            }
        });

        form.add(name, amount, years, interest);
        addButton.addClickShortcut(Key.ENTER);
        add(new H1("Mortgage Plan"), form, addButton, textArea);
    }

}
