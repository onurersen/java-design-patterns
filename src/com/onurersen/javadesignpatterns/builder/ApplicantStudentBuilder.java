package com.onurersen.javadesignpatterns.builder;

// Abstract ApplicantBuilder
public interface ApplicantStudentBuilder {
    //methods to build "parts" of product at a time
    ApplicantStudentBuilder withFirstName(String fistName);

    ApplicantStudentBuilder withLastName(String lastName);

    ApplicantStudentBuilder withNumber(int number);

    ApplicantStudentBuilder withParentName(String parentName);

    // implement build method to create a Student
    Student build();

}

