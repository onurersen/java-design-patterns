package com.onurersen.javadesignpatterns.singleton;

public class ImageResizerTest {

    public static void main(String[] args){

        ImageResizer resizer1 = ImageResizer.getInstance();
        ImageResizer resizer2 = ImageResizer.getInstance();

        // calling resize() method directly after getting instance
        resizer1.resize(234,21);
        resizer2.resize(234,21);

        boolean hashCodesAreEqual = (resizer1.hashCode() == resizer2.hashCode()) ? true : false;

        System.out.println("\nChecking object equality:");
        System.out.println("Are hashcodes of both objects are equal? : " + hashCodesAreEqual + "\n");
        System.out.println("HashCodes:");
        System.out.println(resizer1.hashCode());
        System.out.println(resizer2.hashCode());

    }

}
