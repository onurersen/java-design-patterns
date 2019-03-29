package com.onurersen.javadesignpatterns.builder;

// Concrete Builder class for ApplicantStudentBuilder
public class ApplicantStudentBuilderImpl implements ApplicantStudentBuilder {

    private String firstName;
    private String lastName;
    private int number;
    private String parentName;
    private Student dto;

    @Override
    public ApplicantStudentBuilder withFirstName(String name) {
        firstName = name;
        return this;
    }

    @Override
    public ApplicantStudentBuilder withLastName(String name) {
        lastName = name;
        return this;
    }

    @Override
    public ApplicantStudentBuilder withNumber(int no) {
        number = no;
        return this;
    }

    @Override
    public ApplicantStudentBuilder withParentName(String pname) {
        this.parentName = pname + " " + lastName;
        return this;
    }

    @Override
    public Student build() {
        dto = new StudentImpl(firstName + " " + lastName, parentName, number);
        return dto;
    }

}
