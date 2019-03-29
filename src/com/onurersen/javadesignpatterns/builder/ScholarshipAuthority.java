package com.onurersen.javadesignpatterns.builder;

// This class will also work as a "director"
public class ScholarshipAuthority {

    public static void main(String[] args) {

        ApplicantStudentBuilder builder = new ApplicantStudentBuilderImpl();

        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Jonathan");
        applicant1.setLastName("Randall");
        applicant1.setParentName("Edward");
        applicant1.setNumber(2151);
        Student dto1 = directBuild(builder, applicant1);
        System.out.println(dto1);

        System.out.println("-----");

        Applicant applicant2 = new Applicant();
        applicant2.setFirstName("Henry");
        applicant2.setLastName("McDonald");
        applicant2.setParentName("Jeffrey");
        applicant2.setNumber(352);
        Student dto2 = directBuild(builder, applicant2);
        System.out.println(dto2);

    }

    private static Student directBuild(ApplicantStudentBuilder builder, Applicant applicant) {
        return builder
                .withFirstName(applicant.getFirstName())
                .withLastName(applicant.getLastName())
                .withParentName(applicant.getParentName())
                .withNumber(applicant.getNumber())
                .build();
    }

}
