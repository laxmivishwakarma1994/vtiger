package com.vtiger.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class leadsteps extends commonsteps {

    @When("user click on new lead")
    public void click_on_new_lead() {
       hp.ClickNewLead();

    }
    @Then("fill all mandatory fields and click on save")
    public void Create_lead() {
        ldp.createlead_with_mandatory_fields(dt.get(TCName).get("firstname"),dt.get(TCName).get("lastname"),dt.get(TCName).get("company"));

    }
    @Then("lead should be created successfully")
    public void verify_leadcreation() {
        ldp.verifyleadcreation(dt.get(TCName).get("firstname"),dt.get(TCName).get("lastname"),dt.get(TCName).get("company"));


    }


}
