package com.onurersen.javadesignpatterns.singleton;

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