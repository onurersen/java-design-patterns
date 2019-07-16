---
title: "A Pattern A Day #1 : Singleton"
author: "Berat Onur Ersen"
date: 2019-03-12
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will be *16 patterns* in total.

---

The very first pattern is **Singleton**.

Singleton, as its name refers, is a "single instance" pattern.

Single instance approach is used to **optimize usage of certain functionalities/classes** in runtime, such as classes for resizing image,
printing, logging, sending emails, pushing notifications etc. It would be **expensive** to create an instance for those functionalities every time
we need them. Let's apply a singleton approach on `ImageResizer` class that we will use in our image resizer app project.


```java
public class ImageResizer {

    public ImageResizer(){

    }

}
```

The first thing we do to implement singleton pattern is making our single class constructor to `private`.

Doing that, we have **a non-constructable** class.  
However, we need to create that "one object" to be used in runtime.  
So, we create an instance of `ImageResizer` object inside that single class as `private`.  
What we have is a-non constructable class with an instance of itself as a variable.

```java
public class ImageResizer {

    /** we need to create that "one object" to be re-used **/
    private ImageResizer imageResizer;

    /** non-constructable **/
    private ImageResizer(){

    }

}
```

Next, whenever we need to use that single class we need to access it directly.  
To achieve that we create a `getInstance()` method inside our single class.
This `getInstance()` will check the current instance of the object. If there is an object instantiated already, 
**it will return** otherwise **it will create a new instance** of our ImageResizer object.

```java
public class ImageResizer {

    /** we need to create that "one object" to be re-used **/
    private ImageResizer imageResizer;

    /** non-constructable **/
    private ImageResizer(){

    }

    /** get instance method to return or create the instance **/
    public ImageResizer getInstance(){
        if(imageResizer == null){
            imageResizer = new ImageResizer();
        }
        return imageResizer;
    }

}
```

Now we have a `getInstance()` method to return that single instance of `ImageResizer` but we **CAN NOT** that method. 

**Why?**

Because we need to have at least one instance of that object to use. 

Umm... cannot instantiate ImageResizer object manually then what will we do?

Making `getInstance()` method to `static` to make it callable without instantiating a new ImageResizer class.

That will not be enough, the code will not compile because of **non-static** `ImageResizer.java` class used in `static getInstance()` method. 
We make ImageResizer instance variable to `static` , too.

```java
public class ImageResizer {

    /** we need to create that "one object" to be re-used **/
    private static ImageResizer imageResizer;

    /** non-constructable **/
    private ImageResizer(){

    }

    /** get instance static method to return or create the instance without instantiating**/
    public static ImageResizer getInstance(){
        if(imageResizer == null){
            imageResizer = new ImageResizer();
        }
        return imageResizer;
    }

    public void resize(int dimensionX, int dimensionY){
        System.out.println("Resizing with dimensions X : " + dimensionX + " and dimension Y : " + dimensionY);
    }

}
```

Now executing `ImageResizer.getInstance()` from anywhere will return us a single instance of ImageResizer class.

---

If we'd like to test whether `getInstance()` is really returning the same object in memory, 
we can check `hashCode()` of two instances we create in runtime.

```java
public class ImageResizerTest {

    public static void main(String[] args){

        ImageResizer resizer1 = ImageResizer.getInstance();
        ImageResizer resizer2 = ImageResizer.getInstance();

        // calling resize() method directly after getting instance
        resizer1.resize(234,21);
        resizer2.resize(234,21);

        boolean hashCodesAreEqual = (resizer1.hashCode() == resizer2.hashCode()) ? true : false;

        System.out.println("Are hashcodes of both objects are equal? : " + hashCodesAreEqual + "\n");
        System.out.println(resizer1.hashCode());
        System.out.println(resizer2.hashCode());

    }

}
```

Execution will return such a result in the command line:

```
Resizing with dimensions X : 234 and dimension Y : 21
Resizing with dimensions X : 234 and dimension Y : 21

Checking object equality:
Are hashcodes of both objects are equal? : true

HashCodes:
460141958
460141958
```

---

Next design pattern is [Factory Pattern.](/post/2019-03-13-a-pattern-a-day-factory/) 

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns)