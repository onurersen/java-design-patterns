---
title: "A Pattern A Day #14 : Builder"
author: "Berat Onur Ersen"
date: 2019-03-28
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The fourteenth pattern is **Builder**.

For some reason, in our project classes can be designed as immutable which prevents client code to change them after construction.  
In such situations, we can be using a specific building pattern.

In general, classes with constructors produce the same output once used. However, when we need to construct objects that are flexible and vary in different implementations, we require to have an effective way of constructing those objects.

In the Builder pattern, the object responsible for invoking different building methods for construction is called **Director**. Clients can use Director to 
create required in a different manner.

Our analogy for builder pattern will be for students who are applying for the scholarship to a science institute.  
All applying students will provide three basic information during their initial application; **name**, **parent name** and **number**.

Let's create a `Student` interface having those fields.

```java
public interface Student {

    String getName();

    String getParentName();

    int getNumber();
}
```

Our pattern will build a `StudentImpl` which is an implementation of `Student` in the end - who applies for the scholarship.

```java
// Final output of builder pattern
public class StudentImpl implements Student {

    private String name;

    private String parentName;

    private int number;

    public StudentImpl(String name, String parentName, int number) {
        this.name = name;
        this.parentName = parentName;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parentName;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Applying student name is " + name +
                        "\nApplying student number is "
                            + number + "\nApplying student\'s parent name is "
                                + parentName;
    }

}
```

To apply the Builder pattern, we need these `StudentImpl` classes to be built via a Builder class. For that purpose, we will have `ApplicantStudentBuilder` 
interface.

```java
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
```

Naturally, a concrete `ApplicantStudentBuilderImpl` class  will implement `ApplicantStudentBuilder` interface. This class will be executing some additional things like; creating a full parent name for the parent from `lastName` and `parentName`. In our further implementations 
using this pattern, we may be executing more complex things.

Each method in this class will assign parameters to its instance variables - which means Builder class takes those methods to itself once executing `build()`, 
it will create the desired output class.

```java
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
```

Next, for as a helper class, we will create a basic DTO class named `Applicant` to carry information as input to `ApplicantStudentBuilderImpl` class. (This class was created just to hold some data, just for testing purpose)

```java
public class Applicant {

    private String firstName;

    private String lastName;

    private String parentName;

    private int number;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
```

Our last move will be creating a `ScholarshipAuthority` class to test our Builder pattern. This class will first create a 
`ApplicantStudentBuilderImpl` class and then it will create two `Applicant` classes.  
Finally, we will tell our builder class **"take this information stored in Applicant class and build a Student for me"**.  
As you see in the code it will build Student classes for us **using method chaining (with(...).with(...).with(...)) **. This class also acts as a **director** to 
build `Student` classes.

```java
// This class will also work as a "director"
public class ScholarshipAuthority {

    public static void main(String[] args) {

        ApplicantStudentBuilder builder = new ApplicantStudentBuilderImpl();

        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Jonathan");
        applicant1.setLastName("Randall");
        applicant1.setParentName("Edward");
        applicant1.setNumber(2151);
        Student dto1 = directBuild(builder,applicant1);
        System.out.println(dto1);

        System.out.println("-----");

        Applicant applicant2 = new Applicant();
        applicant2.setFirstName("Henry");
        applicant2.setLastName("McDonald");
        applicant2.setParentName("Jeffrey");
        applicant2.setNumber(352);
        Student dto2 = directBuild(builder,applicant2);
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

}
```

The output of execution will be as shown below.

```
Applying student name is Jonathan Randall
Applying student number is 2151
Applying student's parent name is Edward Randall
-----
Applying student name is Henry McDonald
Applying student number is 352
Applying student's parent name is Jeffrey McDonald
```

Using Builder pattern, we delegated Applicant Student creation to our `ApplicantStudentBuilder`. 

This was a simple example to demonstrate how things work.

If we had a more complex implementation, we would use Builder pattern for creating more complex final objects, with varying specifications implicitly.

---

Next design pattern is Flyweight Pattern.

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 