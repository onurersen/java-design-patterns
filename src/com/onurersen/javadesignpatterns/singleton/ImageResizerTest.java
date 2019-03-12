package com.onurersen.javadesignpatterns.singleton;

public class ImageResizerTest {

    public static void main(String[] args){

        ImageResizer resizer1 = ImageResizer.getInstance();
        ImageResizer resizer2 = ImageResizer.getInstance();

        boolean hashCodesAreEqual = (resizer1.hashCode() == resizer2.hashCode()) ? true : false;

        System.out.println("Are hashcodes of both objects are equal? : " + hashCodesAreEqual + "\n");
        System.out.println(resizer1.hashCode());
        System.out.println(resizer2.hashCode());

    }

}
